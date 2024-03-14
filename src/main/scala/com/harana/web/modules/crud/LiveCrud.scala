package com.harana.web.modules.crud

import com.harana.id.jwt.modules.jwt.JWT
import com.harana.modules.core.config.Config
import com.harana.modules.core.logger.Logger
import com.harana.modules.core.micrometer.Micrometer
import com.harana.modules.core.mongo.Mongo
import com.harana.modules.vertx.models.Response
import com.harana.sdk.shared.models.common.User.UserId
import com.harana.sdk.shared.models.common.{Entity, Visibility}
import com.harana.sdk.shared.models.jwt.HaranaClaims
import io.circe.parser._
import io.circe.syntax._
import io.circe.{Decoder, Encoder}
import io.vertx.ext.web.RoutingContext
import org.mongodb.scala.bson.BsonDocument
import zio.{Task, ZIO, ZLayer}

import java.time.Instant
import scala.jdk.CollectionConverters._
import scala.reflect.ClassTag
import scala.reflect.runtime.universe._

object LiveCrud {
  val layer = ZLayer {
    for {
      config        <- ZIO.service[Config]
      jwt           <- ZIO.service[JWT]
      logger        <- ZIO.service[Logger]
      micrometer    <- ZIO.service[Micrometer]
      mongo         <- ZIO.service[Mongo]
    } yield LiveCrud(config, jwt, logger, micrometer, mongo)
  }
}

case class LiveCrud(config: Config, jwt: JWT, logger: Logger, micrometer: Micrometer, mongo: Mongo) extends Crud {

    def userId(rc: RoutingContext): Task[Option[String]] =
      for {
        authToken       <- ZIO.succeed(Option(rc.request.headers().get("auth-token")))
        userIdAttempt   =  if (authToken.nonEmpty)
                            for {
                              secretToken   <- config.secret("harana-token")
                              _             <- ZIO.fail(new Exception("Auth token != Secret")).unless(authToken.get == secretToken)
                              headerUserId  <- ZIO.getOrFail(Option(rc.request.headers().get("user-id")))
                            } yield headerUserId
                          else
                            jwt.claims[HaranaClaims](rc).map(_.userId)
        userId        <- userIdAttempt.option
      } yield userId


  def owners[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[Map[String, Int]] =
    for {
      userId        <- userId(rc)
      filterStage   = BsonDocument("$match" -> BsonDocument("$or" -> creatorOrPublic(userId)))
      projectStage  = BsonDocument("$project" -> BsonDocument("tags" -> 1))
      unwindStage   = BsonDocument("$unwind" -> BsonDocument("path" -> "$tags"))
      groupStage    = BsonDocument("$group" -> BsonDocument("_id" -> "$tags", "count" -> BsonDocument("$sum" -> 1)))

      entities      <- mongo.aggregate(collection, List(filterStage, projectStage, unwindStage, groupStage))
      tags          = entities.map(e => e.get("_id").asString().getValue -> e.get("count").asNumber().intValue).toMap
    } yield tags


  def ownersResponse[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[Response] =
    owners(collection, rc).map(r => Response.JSON(r.asJson))


  def tags[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[Map[String, Int]] =
    for {
      userId        <- userId(rc)

      filterStage   = BsonDocument("$match" -> BsonDocument("$or" -> creatorOrPublic(userId)))
      projectStage  = BsonDocument("$project" -> BsonDocument("tags" -> 1))
      unwindStage   = BsonDocument("$unwind" -> BsonDocument("path" -> "$tags"))
      groupStage    = BsonDocument("$group" -> BsonDocument("_id" -> "$tags", "count" -> BsonDocument("$sum" -> 1)))

      entities      <- mongo.aggregate(collection, List(filterStage, projectStage, unwindStage, groupStage))
      tags          = entities.map(e => e.get("_id").asString().getValue -> e.get("count").asNumber().intValue).toMap
    } yield tags


  def tagsResponse[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[Response] =
    tags(collection, rc).map(r => Response.JSON(r.asJson))


  def list[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[List[E]] =
    for {
      sort      <- ZIO.attempt(rc.queryParam("sort").asScala.toList)
      limit     <- ZIO.attempt(rc.queryParam("limit").asScala.headOption.map(_.toInt))
      skip      <- ZIO.attempt(rc.queryParam("skip").asScala.headOption.map(_.toInt))
      tag       <- ZIO.attempt(rc.queryParam("tag").asScala.headOption)
      userId    <- userId(rc)

      filter    <- ZIO.succeed(if (tag.nonEmpty) Map("$or" -> creatorOrPublic(userId), "tags" -> tag.get) else Map("$or" -> creatorOrPublic(userId)))
      entities  <- mongo.findEquals[E](collection, filter, sortList(sort), limit, skip).onError(e => logger.error(e.prettyPrint))

    } yield entities


  def listResponse[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[Response] =
    list(collection, rc).map(r => Response.JSON(r.asJson))


  def search[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[List[E]] =
    for {
      query         <- ZIO.attempt(rc.pathParam("query"))
      tag           <- ZIO.attempt(rc.queryParam("tag").asScala.headOption)

      userId        <- userId(rc)
      entityName    = ct.runtimeClass.getSimpleName

      filter        <- ZIO.succeed(if (tag.nonEmpty) Map("$or" -> creatorOrPublic(userId), "tags" -> tag.get) else Map("$or" -> creatorOrPublic(userId)))
      entities      <- mongo.textSearchFindEquals[E](collection, query, filter).onError(e => logger.error(e.prettyPrint))

      _             <- logger.debug(s"Search $entityName. Found: ${entities.size}")
    } yield entities


  def searchResponse[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[Response] =
    search(collection, rc).map(r => Response.JSON(r.asJson))


  def get[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[Option[E]] =
    for {
      id          <- ZIO.attempt(rc.pathParam("id"))
      userId      <- userId(rc)
      entityName  = ct.runtimeClass.getSimpleName

      entity      <- mongo.findEquals[E](collection, Map("id" -> id, "$or" -> creatorOrPublic(userId))).map(_.headOption)
      _           <- logger.debug(s"Get $entityName with id: $id")
    } yield entity


  def getResponse[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[Response] =
    get(collection, rc).map(r => Response.JSON(r.asJson))


  def delete[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E]): Task[Unit] =
    for {
      id            <- ZIO.attempt(rc.pathParam("id"))
      userId        <- userId(rc)
      entityName    = ct.runtimeClass.getSimpleName
      _             <- mongo.deleteEquals[E](collection, Map("id" -> id, "createdBy" -> userId))
      _             <- logger.debug(s"Delete $entityName with id: $id")
    } yield ()


  def deleteResponse[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E]): Task[Response] =
    delete(collection, rc).as(Response.Empty())


  def create[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[E] =
    for {
      userId        <- userId(rc)
      entityName    =  ct.runtimeClass.getSimpleName
      entityJson    <- ZIO.attempt(rc.body().asJsonObject())
      isValid       <- ZIO.attempt(entityJson.getValue("createdBy").equals(userId))

      _             <- logger.error(s"Creating: ${rc.body().asString}")
      entity        <- ZIO.fromEither(decode[E](rc.body().asString))
      _             <- mongo.insert[E](collection, entity).when(isValid)
      _             <- logger.debug(s"Insert $entityName")
    } yield entity


  def createResponse[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[Response] =
    create(collection, rc).as(Response.Empty())


  def update[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[E] =
    for {
      userId        <- userId(rc)
      entityName    =  ct.runtimeClass.getSimpleName
      entityJson    <- ZIO.attempt(rc.body().asJsonObject())
      isValid       <- ZIO.attempt(entityJson.getValue("createdBy").equals(userId))
      _             =  entityJson.put("updated", Instant.now)
      entity        <- ZIO.fromEither(decode[E](entityJson.toString))
      _             <- logger.debug(entityJson.toString)
      _             <- mongo.update[E](collection, entity.id, entity).when(isValid)
      _             <- logger.debug(s"Update $entityName")
    } yield entity


  def updateResponse[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[Response] =
    update(collection, rc).as(Response.Empty())


  private def sortList(sortParams: List[String]) = {
    sortParams.map(_.split("/")).map { sp =>
      (sp(0), sp(1).toBoolean)
    }
  }

  def creatorOrPublic(userId: Option[UserId]) = {
    val isPublic = BsonDocument("visibility" -> Visibility.Public.toString)
    if (userId.nonEmpty)
      List(isPublic, BsonDocument("createdBy" -> userId))
    else
      List(isPublic)
  }
}