package com.harana.web.external.google_tag_manager

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("react-gtm-module", JSImport.Namespace)
@js.native
object TagManager extends js.Object {
  def dataLayer(args: DataLayerArgs): Boolean = js.native
  def initialize(args: TagManagerArgs): Boolean = js.native
}

trait DataLayerArgs extends js.Object {
  val dataLayer: js.Dictionary[js.Any]
  val dataLayerName: js.UndefOr[String] = js.undefined
}

trait TagManagerArgs extends js.Object {
  val auth: js.UndefOr[String] = js.undefined
  val events: js.UndefOr[js.Object] = js.undefined
  val gtmId: String
  val nonce: js.UndefOr[String] = js.undefined
  val preview: js.UndefOr[String] = js.undefined
}