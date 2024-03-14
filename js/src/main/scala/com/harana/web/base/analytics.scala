package com.harana.web.base

import scala.scalajs.js

object analytics {

  def analyticsEvent(event: String,
                     app_id: js.UndefOr[String] = js.undefined,
                     app_name: js.UndefOr[String] = js.undefined,
                     ecommerce: js.UndefOr[js.Object] = js.undefined,
                     page_hostname: js.UndefOr[String] = js.undefined,
                     page_path: js.UndefOr[String] = js.undefined,
                     page_url: js.UndefOr[String] = js.undefined,
                     referrer: js.UndefOr[String] = js.undefined,
                     email_address: js.UndefOr[String] = js.undefined,
                     first_name: js.UndefOr[String] = js.undefined,
                     last_name: js.UndefOr[String] = js.undefined,
                     user_id: js.UndefOr[String] = js.undefined,
                     last_login: js.UndefOr[String] = js.undefined,
                     last_session: js.UndefOr[String] = js.undefined,
                     marketing_channel: js.UndefOr[String] = js.undefined,
                     marketing_channel_id: js.UndefOr[String] = js.undefined,
                     schedule_count: js.UndefOr[String] = js.undefined,
                     subscription_ended: js.UndefOr[String] = js.undefined,
                     subscription_customer_id: js.UndefOr[String] = js.undefined,
                     subscription_duration: js.UndefOr[String] = js.undefined,
                     subscription_id: js.UndefOr[String] = js.undefined,
                     subscription_price: js.UndefOr[String] = js.undefined,
                     subscription_price_id: js.UndefOr[String] = js.undefined,
                     subscription_product: js.UndefOr[String] = js.undefined,
                     subscription_started: js.UndefOr[String] = js.undefined,
                     trial_duration: js.UndefOr[String] = js.undefined,
                     trial_ended: js.UndefOr[String] = js.undefined,
                     trial_started: js.UndefOr[String] = js.undefined) =
    js.Dynamic.literal(
      event = event,
      app_id = app_id,
      app_name = app_name,
      ecommerce = ecommerce,
      page_hostname = page_hostname,
      page_path = page_path,
      page_url = page_url,
      referrer = referrer,
      email_address = email_address,
      first_name = first_name,
      last_name = last_name,
      user_id = user_id,
      last_login = last_login,
      last_session = last_session,
      marketing_channel = marketing_channel,
      marketing_channel_id = marketing_channel_id,
      schedule_count = schedule_count,
      subscription_ended = subscription_ended,
      subscription_customer_id = subscription_customer_id,
      subscription_duration = subscription_duration,
      subscription_id = subscription_id,
      subscription_price = subscription_price,
      subscription_price_id = subscription_price_id,
      subscription_product = subscription_product,
      subscription_started = subscription_started,
      trial_duration = trial_duration,
      trial_ended = trial_ended,
      trial_started = trial_started).asInstanceOf[js.Object]

  def ecommerce(purchase: js.UndefOr[js.Object] = js.undefined,
                items: js.Array[js.Object] = js.Array()) =
    js.Dynamic.literal(
      purchase = purchase,
      items = items
    ).asInstanceOf[js.Object]

  def purchaseItem(index: Int,
                   item_name: js.UndefOr[String] = js.undefined,
                   item_id: js.UndefOr[String] = js.undefined,
                   price: js.UndefOr[String] = js.undefined,
                   quantity: js.UndefOr[String] = js.undefined,
                   item_brand: js.UndefOr[String] = js.undefined,
                   item_category: js.UndefOr[String] = js.undefined,
                   item_category_2: js.UndefOr[String] = js.undefined,
                   item_category_3: js.UndefOr[String] = js.undefined,
                   item_category_4: js.UndefOr[String] = js.undefined,
                   item_variant: js.UndefOr[String] = js.undefined,
                   item_list_name: js.UndefOr[String] = js.undefined,
                   item_list_id: js.UndefOr[String] = js.undefined) =
    js.Dynamic.literal(
      index = index,
      item_name = item_name,
      item_id = item_id,
      price = price,
      quantity = quantity,
      item_brand = item_brand,
      item_category = item_category,
      item_category_2 = item_category_2,
      item_category_3 = item_category_3,
      item_category_4 = item_category_4,
      item_variant = item_variant,
      item_list_name = item_list_name,
      item_list_id = item_list_id).asInstanceOf[js.Object]

  def purchase(transaction_id: js.UndefOr[String] = js.undefined,
               affiliation: js.UndefOr[String] = js.undefined,
               value: js.UndefOr[String] = js.undefined,
               tax: js.UndefOr[String] = js.undefined,
               shipping: js.UndefOr[String] = js.undefined,
               currency: js.UndefOr[String] = js.undefined,
               coupon: js.UndefOr[String] = js.undefined,
               items: js.Array[js.Object] = js.Array()) =
    js.Dynamic.literal(
      transaction_id = transaction_id,
      affiliation = affiliation,
      value = value,
      tax = tax,
      shipping = shipping,
      currency = currency,
      coupon = coupon,
      items = items).asInstanceOf[js.Object]
}