package com.harana.web.base

import com.harana.sdk.shared.models.jwt.HaranaClaims
import com.harana.web.external.google_tag_manager.{DataLayerArgs, TagManager}
import org.scalajs.dom
import slinky.history.History

import java.time.format.DateTimeFormatter
import java.time.{Instant, ZoneId, ZoneOffset}
import scala.collection.mutable.ListBuffer
import scala.scalajs.js
import scala.scalajs.js.JSConverters._

class Analytics {

  private val pageHistory = new ListBuffer[String]

  val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.from(ZoneOffset.UTC))

  def duration(start: Option[Instant], end: Option[Instant], inDays: Boolean = true) = {
    val duration = (start, end) match {
      case (Some(start), Some(end)) => (end.toEpochMilli - start.toEpochMilli) / 1000
      case (Some(start), None) => (Instant.now().toEpochMilli - start.toEpochMilli) / 1000
      case _ => 0
    }
    (if (inDays) duration / (3600 * 24) else duration).toString
  }

  implicit def optional(o: Option[String]): Option[String] =
    o.getOrElse("").asInstanceOf[Option[String]]

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

  def purchaseItem(itemId: Option[String] = None,
                   itemName: Option[String] = None,
                   affiliation: Option[String] = None,
                   coupon: Option[String] = None,
                   discount: Option[Double] = None,
                   index: Option[Int] = None,
                   itemBrand: Option[String] = None,
                   itemCategory: Option[String] = None,
                   itemCategory2: Option[String] = None,
                   itemCategory3: Option[String] = None,
                   itemCategory4: Option[String] = None,
                   itemCategory5: Option[String] = None,
                   itemListId: Option[String] = None,
                   itemListName: Option[String] = None,
                   itemVariant: Option[String] = None,
                   locationId: Option[String] = None,
                   price: Option[Double] = None,
                   quantity: Option[Int] = None) =
    js.Dictionary[js.Any](
      "affiliation"     -> affiliation.orUndefined,
      "coupon"          -> coupon.orUndefined,
      "discount"        -> discount.orUndefined,
      "index"           -> index.orUndefined,
      "item_brand"      -> itemBrand.orUndefined,
      "item_category"   -> itemCategory.orUndefined,
      "item_category2"  -> itemCategory2.orUndefined,
      "item_category3"  -> itemCategory3.orUndefined,
      "item_category4"  -> itemCategory4.orUndefined,
      "item_category5"  -> itemCategory5.orUndefined,
      "item_id"         -> itemId.orUndefined,
      "item_list_id"    -> itemListId.orUndefined,
      "item_list_name"  -> itemListName.orUndefined,
      "item_name"       -> itemName.orUndefined,
      "item_variant"    -> itemVariant.orUndefined,
      "location_id"     -> locationId.orUndefined,
      "price"           -> price.orUndefined,
      "quantity"        -> quantity.orUndefined,
    )

  def addPurchaseInfo(affiliation: Option[String] = None,
                      claims: Option[HaranaClaims] = None,
                      coupon: Option[String] = None,
                      items: js.Array[js.Dictionary[js.Any]] = js.Array(),
                      currency: Option[String] = None,
                      paymentType: Option[String] = None,
                      tax: Option[Double] = None,
                      transactionId: Option[String] = None,
                      value: Option[Double] = None) =
    pushEvent("add_purchase_info", claims, otherAttributes = js.Dictionary[js.Any](
      "affiliation"     -> affiliation.orUndefined,
      "coupon"          -> coupon.orUndefined,
      "currency"        -> currency.orUndefined,
      "items"           -> items,
      "payment_type"    -> paymentType.orUndefined,
      "tax"             -> tax.orUndefined,
      "transaction_id"  -> transactionId.orUndefined,
      "value"           -> value.orUndefined,
    ))

  def addShippingInfo(claims: Option[HaranaClaims] = None,
                      coupon: Option[String] = None,
                      currency: Option[String] = None,
                      items: js.Array[js.Dictionary[js.Any]] = js.Array(),
                      shippingTier: Option[String] = None,
                      value: Option[Double] = None) =
    pushEvent("add_shipping_info", claims, otherAttributes = js.Dictionary[js.Any](
      "coupon"         -> coupon.orUndefined,
      "currency"       -> currency.orUndefined,
      "items"          -> items,
      "shipping_tier"  -> shippingTier.orUndefined,
      "value"          -> value.orUndefined,
    ))

  def addToCart(claims: Option[HaranaClaims] = None,
                currency: Option[String] = None,
                items: js.Array[js.Dictionary[js.Any]] = js.Array(),
                value: Option[Double] = None) =
    pushEvent("add_to_cart", claims, otherAttributes = js.Dictionary[js.Any](
      "currency"    -> currency.orUndefined,
      "items"       -> items,
      "value"       -> value.orUndefined
    ))

  def beginCheckout(claims: Option[HaranaClaims] = None,
                    coupon: Option[String] = None,
                    currency: Option[String] = None,
                    items: js.Array[js.Dictionary[js.Any]] = js.Array(),
                    value: Option[Double] = None) =
    pushEvent("begin_checkout", claims, otherAttributes = js.Dictionary[js.Any](
      "currency" -> currency.orUndefined,
      "items"    -> items,
      "value"    -> value.orUndefined
    ))

  def generateLead(claims: Option[HaranaClaims] = None,
                   currency: Option[String] = None,
                   value: Option[Double] = None) =
    pushEvent("generate_lead", claims, otherAttributes = js.Dictionary[js.Any](
      "currency" -> currency.orUndefined,
      "value"    -> value.orUndefined
    ))

  def login(claims: Option[HaranaClaims] = None,
            method: Option[String] = None) =
    pushEvent("login", claims, otherAttributes = js.Dictionary[js.Any](
      "method" -> method.orUndefined
    ))

  def purchase(claims: Option[HaranaClaims] = None,
               coupon: Option[String] = None,
               currency: Option[String] = None,
               items: js.Array[js.Dictionary[js.Any]] = js.Array(),
               shipping: Option[Double] = None,
               tax: Option[Double] = None,
               value: Option[Double] = None) =
    pushEvent("purchase", claims, otherAttributes = js.Dictionary[js.Any](
      "coupon"    -> coupon.orUndefined,
      "currency"  -> currency.orUndefined,
      "items"     -> items,
      "shipping"  -> shipping.orUndefined,
      "tax"       -> tax.orUndefined,
      "value"     -> value.orUndefined,
    ))

  def signup(claims: Option[HaranaClaims] = None,
             method: Option[String] = None) =
    pushEvent("sign_up", claims, otherAttributes = js.Dictionary[js.Any](
        "method" -> method.orUndefined
    ))

  def share(claims: Option[HaranaClaims] = None,
            contentType: Option[String] = None,
            itemId: Option[String] = None,
            method: Option[String] = None) =
    pushEvent("share", claims, otherAttributes = js.Dictionary[js.Any](
        "contentType" -> contentType.orUndefined,
        "itemId"      -> itemId.orUndefined,
        "method"      -> method.orUndefined
    ))

  def appStart(claims: HaranaClaims, appId: String, appName: String) =
    pushEvent("app_start", Some(claims), appId = Some(appId), appName = Some(appName))

  def appStop(claims: HaranaClaims, appId: String, appName: String) =
    pushEvent("app_stop", Some(claims), appId = Some(appId), appName = Some(appName))

  def init(claims: HaranaClaims) =
    pushEvent("init", Some(claims))

  def pageView(pageHostname: String, pagePath: String, pageReferrer: Option[String], pageUrl: String) =
    pushEvent("page_view", None, pageHostname = Some(pageHostname), pagePath = Some(pagePath), pageReferrer = pageReferrer, pageUrl = Some(pageUrl))

  def session(claims: HaranaClaims) =
    pushEvent("session", Some(claims), newSession = true)

  def subscribe(claims: HaranaClaims) =
    pushEvent("subscribe", Some(claims))

  def unsubscribe(claims: HaranaClaims) =
    pushEvent("unsubscribe", Some(claims))

  protected def pushEvent(eventName: String,
                          claims: Option[HaranaClaims],
                          appId: Option[String] = None,
                          appName: Option[String] = None,
                          newSession: Boolean = false,
                          pageHostname: Option[String] = None,
                          pagePath: Option[String] = None,
                          pageReferrer: Option[String] = None,
                          pageUrl: Option[String] = None,
                          otherAttributes: js.Dictionary[js.Any] = js.Dictionary()) =

    try {
      val event =
        if (claims.isEmpty)
          js.Dictionary[js.Any](
            "event"         -> eventName,
            "app_id"        -> appId.orUndefined,
            "app_name"      -> appName.orUndefined,
            "page_hostname" -> pageHostname.orUndefined,
            "page_path"     -> pagePath.orUndefined,
            "page_url"      -> pageUrl.orUndefined,
            "referrer"      -> pageReferrer.orUndefined
          )
        else {
          val lastSession: js.UndefOr[String] = if (newSession) date(Some(Instant.now)) else js.undefined
          js.Dictionary[js.Any](
            "event"                     -> eventName,
            "app_id"                    -> appId.orUndefined,
            "app_name"                  -> appName.orUndefined,
            "page_hostname"             -> pageHostname.orUndefined,
            "page_path"                 -> pagePath.orUndefined,
            "page_url"                  -> pageUrl.orUndefined,
            "referrer"                  -> pageReferrer.orUndefined,
            "email_address"             -> claims.get.emailAddress,
            "first_name"                -> claims.get.firstName,
            "last_name"                 -> claims.get.lastName,
            "user_id"                   -> claims.get.userId,
            "last_login"                -> date(Some(claims.get.issued)),
            "last_session"              -> lastSession,
            "marketing_channel"         -> claims.get.marketingChannel.map(_.entryName).orUndefined,
            "marketing_channel_id"      -> claims.get.marketingChannelId.orUndefined,
            "subscription_customer_id"  -> claims.get.billing.subscriptionCustomerId.orUndefined,
            "subscription_duration"     -> duration(claims.get.billing.subscriptionStarted, claims.get.billing.subscriptionEnded),
            "subscription_ended"        -> date(claims.get.billing.subscriptionEnded),
            "subscription_id"           -> claims.get.billing.subscriptionId.orUndefined,
            "subscription_price"        -> claims.get.billing.subscriptionPrice.map(p => (p / 100).toString).orUndefined,
            "subscription_price_id"     -> claims.get.billing.subscriptionPriceId.orUndefined,
            "subscription_product"      -> claims.get.billing.subscriptionProduct.orUndefined,
            "subscription_started"      -> date(claims.get.billing.subscriptionStarted),
            "trial_duration"            -> duration(claims.get.billing.trialStarted, claims.get.billing.trialEnded),
            "trial_ended"               -> date(claims.get.billing.trialEnded),
            "trial_started"             -> date(claims.get.billing.trialStarted))
      }

      TagManager.dataLayer(new DataLayerArgs {
        override val dataLayer = event
      })

    } catch {
      case e: Exception => println(s"Failed to send event: ${e.getMessage}")
    }
}