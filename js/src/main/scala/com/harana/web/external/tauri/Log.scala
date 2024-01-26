package com.harana.web.external.tauri

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("tauri-plugin-log-api")
@js.native
object Log extends js.Object {
  def attachConsole(): js.Promise[Unit] = js.native
}