package com.harana.web.app

import com.google.common.base.Charsets
import com.harana.id.jwt.modules.jwt.JWT
import com.harana.id.jwt.{Layers => JWTLayers}
import com.harana.modules.{Layers => ModuleLayers}
import com.harana.modules.core.app.{App => CoreApp}
import com.harana.modules.core.mongo.Mongo
import com.harana.modules.core.{Layers => CoreLayers}
import com.harana.modules.vertx.Vertx
import com.harana.modules.vertx.models.{Response, Route}
import com.harana.modules.vertx.proxy.WSURI
import com.harana.sdk.shared.models.common.{PendingEvent, User => DesignerUser}
import com.harana.sdk.shared.models.jwt.HaranaClaims
import com.harana.web.modules.Layers
import com.harana.web.modules.crud.Crud
import com.harana.web.shared.JavaScriptError
import io.circe.jawn
import io.vertx.ext.web.RoutingContext
import org.jsoup.Jsoup
import org.jsoup.safety.Safelist
import upickle.default._
import zio.managed.ZManagedZIOSyntax
import zio.{Task, UIO, ZIO}

import java.net.URI
import java.time.Instant
import scala.io.Source
import scala.util.Try

abstract class WebApp extends CoreApp {

  private val properties = ZIO.attempt {
    val airbyte = Source.fromURL(getClass.getResource("/messages_airbyte_en")).bufferedReader()
    val harana = Source.fromURL(getClass.getResource("/messages_harana_en")).bufferedReader()
    val airByteLines = LazyList.continually(airbyte.readLine()).takeWhile(_ != null).map(Jsoup.clean(_, Safelist.simpleText))
    val haranaLines = LazyList.continually(harana.readLine()).takeWhile(_ != null).map(Jsoup.clean(_, Safelist.simpleText))
    new String(java.util.Base64.getEncoder.encode(write(airByteLines.toList ++ haranaLines.toList).getBytes(Charsets.UTF_8)))
  }

  def routes: List[Route]

  def proxyMapping: Option[RoutingContext => Task[Option[URI]]] = None

  def websocketProxyMapping: Option[Any => Task[WSURI]] = None

  def onboardUser: Option[Any => UIO[Unit]] = None

  def i18n(locale: String): Task[String] = properties

  def startup =
    for {
      domain  <- env("harana_domain")
      appName <- configStr("application.name")
      _       <- Vertx.startHttpServer(
                    s"$appName.$domain",
                    Some(s"$appName-proxy.$domain"),
                    routes,
                    proxyMapping = proxyMapping,
                    webSocketProxyMapping = websocketProxyMapping,
                    eventBusInbound = List(".*"),
                    eventBusOutbound = List(".*")
                  ).provideLayer(ModuleLayers.vertx).toManaged.useForever
    } yield ()


  def shutdown =
    for {
      _       <- Vertx.close.provideLayer(ModuleLayers.vertx)
    } yield ()


  def homePage(rc: RoutingContext, onboard: Boolean = false): Task[Response] =
    for {
      domain            <- env("harana_domain")
      gaMeasurementId   <- secret("google-analytics-measurement-id")
      i18nProperties    <- i18n("en")
      claims            <- claims(rc).option.map(_.flatten)
      response          <- claims match {
                            case Some(claims) =>
                              for {
                                _           <- Mongo.updateFields("Users", claims.userId, Map("lastSession" -> Instant.now().toString)).provideLayer(CoreLayers.mongo)
                                _           <- ZIO.when(onboard && onboardUser.nonEmpty)(onboardUser.get.apply())
                                                 parameters  = Map(
                                                    "authDomain"        -> s"id.$domain",
                                                    "debug"             -> "false",
                                                    "domain"            -> domain,
                                                    "gaMeasurementId"   -> gaMeasurementId,
                                                    "initialJwt"        -> rc.request.getCookie("jwt").getValue,
                                                    "i18nProperties"    -> i18nProperties,
                                                    "proxyDomain"       -> s"designer-proxy.$domain",
                                                    "userId"            -> claims.userId
                                                 )
                                template   = Response.Template("templates/index.html", parameters = parameters)
                              } yield template

                            case None =>
                              for {
                                _         <- ZIO.attempt(rc.response.removeCookie("jwt"))
                                redirect  = Response.Redirect(s"https://harana.com/login?id_domain=id.$domain")
                              } yield redirect
                          }
                        } yield response


  def claims(rc: RoutingContext): Task[Option[HaranaClaims]] =
    for {
      jwtValue    <- ZIO.from(Try(rc.request.getCookie("jwt").getValue))
      claims      <- JWT.claims[HaranaClaims](jwtValue).provideLayer(JWTLayers.jwt)
      user        <- Mongo.findOne[DesignerUser]("Users", Map("id" -> claims.userId)).onError(e => logError(e.prettyPrint)).option.provideLayer(CoreLayers.mongo)
      result      =  if (user.flatten.nonEmpty && claims.issued.isAfter(user.flatten.get.updated)) Some(claims) else None
    } yield result


  def content(rc: RoutingContext): Task[Response] =
    for {
      userId        <- Crud.userId(rc).provideLayer(Layers.crud).someOrFail(new Exception("User not found"))
      user          <- Mongo.findOne[DesignerUser]("Users", Map("id" -> userId)).provideLayer(CoreLayers.mongo)
      id            <- ZIO.attempt(rc.pathParam("id"))
      response      = if (user.isEmpty || id.contains("..") || id.contains("/")) Response.Empty(statusCode = Some(403)) else
                        Response.Template(
                          path = s"content/$id.md",
                          parameters = Map(
                            "fileSharingUsername" -> user.get.settings.fileSharingUsername.getOrElse(""),
                            "fileSharingPassword" -> user.get.settings.fileSharingPassword.getOrElse(""),
                            "remoteLoginUsername" -> user.get.settings.remoteLoginUsername.getOrElse(""),
                            "remoteLoginPassword" -> user.get.settings.remoteLoginUsername.getOrElse("")),
                          statusCode = Some(200))
    } yield
      response


  def handleEvents: Task[Unit] =
    for {
      event     <- Mongo.findOneAndDelete[PendingEvent]("PendingEvents", Map(), List(("created", true))).provideLayer(CoreLayers.mongo)
      _         <- ZIO.when(event.nonEmpty)(Vertx.sendMessage(event.get.address, event.get.`type`, event.get.payload).provideLayer(ModuleLayers.vertx))
    } yield ()


  def error(rc: RoutingContext): Task[Response] =
    for {
      errorJson   <- ZIO.attempt(rc.body().asString)
      error       <- ZIO.attempt(jawn.decode[JavaScriptError](errorJson))
      _           <- ZIO.when(error.toOption.nonEmpty)(logInfo(error.toOption.get.toString))
      response    =  Response.Empty()
    } yield response
}