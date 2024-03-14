package com.harana.web.base

import com.harana.web.base.analytics._
import com.harana.sdk.shared.models.jwt.HaranaClaims
import org.scalajs.dom
import slinky.history.History

import java.time.format.DateTimeFormatter
import java.time.{Instant, ZoneId, ZoneOffset}
import scala.collection.mutable.ListBuffer
import scala.scalajs.js
import scala.scalajs.js.Dynamic
import scala.scalajs.js.JSConverters._

class BaseAnalytics {

  private val pageHistory = new ListBuffer[String]

  val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.from(ZoneOffset.UTC))

  def duration(start: Option[Instant], end: Option[Instant], inDays: Boolean = true) = {
    val duration = (start, end) match {
      case (Some(start), Some(end)) => ((end.toEpochMilli - start.toEpochMilli) / 1000)
      case (Some(start), None) => ((Instant.now().toEpochMilli - start.toEpochMilli) / 1000)
      case _ => 0
    }
    (if (inDays) duration / (3600 * 24) else duration).toString
  }

  implicit def optional(o: Option[String]): js.UndefOr[String] =
    o.getOrElse("").asInstanceOf[js.UndefOr[String]]

  def date(time: Option[Instant]): String =
    if (time.nonEmpty) dateFormatter.format(time.get) else ""

  def history = {
    val history = History.createBrowserHistory()
    history.listen(() => {
      pageView(
        pageHostname = dom.window.location.hostname,
        pagePath = dom.window.location.pathname,
        pageReferrer = pageHistory.lastOption,
        pageUrl = dom.window.location.href
      )
      pageHistory += dom.window.location.href
    })
    history
  }

  def receiveEvent(eventType: String, eventParameters: Map[String, String], claims: HaranaClaims) =
    eventType match {
      case "subscribe" => subscribe(claims)
      case "unsubscribe" => unsubscribe(claims)
      case _ =>
    }

  def appStart(claims: HaranaClaims, appId: String, appName: String) =
    sendBaseEvent("app_start", Some(claims), appId = Some(appId), appName = Some(appName))

  def appStop(claims: HaranaClaims, appId: String, appName: String) =
    sendBaseEvent("app_stop", Some(claims), appId = Some(appId), appName = Some(appName))

  def checkout(claims: HaranaClaims) =
    sendBaseEvent("checkout", Some(claims))

  def init(claims: HaranaClaims) =
    sendBaseEvent("init", Some(claims))

  def pageView(pageHostname: String, pagePath: String, pageReferrer: Option[String], pageUrl: String) =
    sendBaseEvent("page_view", None, pageHostname = Some(pageHostname), pagePath = Some(pagePath), pageReferrer = pageReferrer, pageUrl = Some(pageUrl))

  def session(claims: HaranaClaims) =
    sendBaseEvent("session", Some(claims), newSession = true)

  def subscribe(claims: HaranaClaims) =
    sendBaseEvent("subscribe", Some(claims))

  def unsubscribe(claims: HaranaClaims) =
    sendBaseEvent("unsubscribe", Some(claims))

  protected def sendBaseEvent(eventName: String,
                              claims: Option[HaranaClaims],
                              appId: Option[String] = None,
                              appName: Option[String] = None,
                              newSession: Boolean = false,
                              pageHostname: Option[String] = None,
                              pagePath: Option[String] = None,
                              pageReferrer: Option[String] = None,
                              pageUrl: Option[String] = None,
                              attributes: Option[js.Dynamic] = None) =

    try {
      val dataLayer = Dynamic.global.window.dataLayer.asInstanceOf[js.Array[js.Any]]

      val event = if (claims.isEmpty)
        analyticsEvent(
          event = eventName,
          app_id = appId.orUndefined,
          app_name = appName,
          page_hostname = pageHostname,
          page_path = pagePath,
          page_url = pageUrl,
          referrer = pageReferrer
        )
      else {
        val purchaseItems = js.Array(
          purchaseItem(
            index = 1,
            item_name = claims.get.billing.subscriptionProduct.orUndefined,
            item_id = claims.get.billing.subscriptionPriceId.orUndefined,
            price = claims.get.billing.subscriptionPrice.map(_.toString).orUndefined,
            quantity = "1"
          )
        )
        analyticsEvent(
          event = eventName,
          app_id = appId.orUndefined,
          app_name = appName,
          ecommerce = ecommerce(
            purchase = purchase(
              transaction_id = claims.get.billing.subscriptionId.orUndefined,
              value = claims.get.billing.subscriptionPrice.map(_.toString).orUndefined,
              currency = "USD",
              items = purchaseItems
            ),
            items = purchaseItems
          ),
          page_hostname = pageHostname,
          page_path = pagePath,
          page_url = pageUrl,
          referrer = pageReferrer,
          email_address = claims.get.emailAddress,
          first_name = claims.get.firstName,
          last_name = claims.get.lastName,
          user_id = claims.get.userId,
          last_login = date(Some(claims.get.issued)),
          last_session = if (newSession) date(Some(Instant.now)) else js.undefined,
          marketing_channel = claims.get.marketingChannel.map(_.entryName).orUndefined,
          marketing_channel_id = claims.get.marketingChannelId.orUndefined,
          subscription_ended = date(claims.get.billing.subscriptionEnded),
          subscription_customer_id = claims.get.billing.subscriptionCustomerId.orUndefined,
          subscription_duration = duration(claims.get.billing.subscriptionStarted, claims.get.billing.subscriptionEnded),
          subscription_id = claims.get.billing.subscriptionId.orUndefined,
          subscription_price = claims.get.billing.subscriptionPrice.map(p => (p / 100).toString).orUndefined,
          subscription_price_id = claims.get.billing.subscriptionPriceId.orUndefined,
          subscription_product = claims.get.billing.subscriptionProduct.orUndefined,
          subscription_started = date(claims.get.billing.subscriptionStarted),
          trial_duration = duration(claims.get.billing.trialStarted, claims.get.billing.trialEnded),
          trial_ended = date(claims.get.billing.trialEnded),
          trial_started = date(claims.get.billing.trialStarted))
      }

      dataLayer.push(js.Object.assign(event.asInstanceOf[js.Object]), attributes.getOrElse(js.Object))
    } catch {
      case e: Exception => println(s"Failed to send event: ${e.getMessage}")
    }
}