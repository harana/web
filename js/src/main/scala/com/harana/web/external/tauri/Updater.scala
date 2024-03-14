package com.harana.web.external.tauri

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@tauri-apps/api", "updater")
@js.native
object Updater extends js.Object {
  def onUpdaterEvent(handler: UpdateStatusResult => Unit): js.Promise[UnlistenFn] = js.native
  def installUpdate(): js.Promise[Unit] = js.native
  def checkUpdate(): js.Promise[UpdateResult] = js.native
}

@js.native
trait UpdateStatusResult extends js.Object {
  val error: Option[String]
  val status: String
}

@js.native
trait UpdateManifest extends js.Object {
  val version: String
  val date: String
  val body: String
}

@js.native
trait UpdateResult extends js.Object {
  val manifest: Option[UpdateManifest]
  val shouldUpdate: Boolean
}