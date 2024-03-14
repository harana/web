package com.harana.web.external.tauri

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@tauri-apps/api", "clipboard")
@js.native
object Clipboard extends js.Object {
  def writeText(text: String): js.Promise[Unit] = js.native
  def readText(): js.Promise[Option[String]] = js.native
}