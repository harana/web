package com.harana.web.external.tauri

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@tauri-apps/api", "notification")
@js.native
object Notification extends js.Object {
  def isPermissionGranted(): js.Promise[Boolean] = js.native
  def requestPermission(): js.Promise[String] = js.native
  def sendNotification(options: Options | String): Unit = js.native
}

@js.native
trait Options extends js.Object {
  val title: String
  val body: Option[String]
  val icon: Option[String]
}