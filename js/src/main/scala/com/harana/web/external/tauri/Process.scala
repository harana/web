package com.harana.web.external.tauri

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@tauri-apps/api", "process")
@js.native
object Process extends js.Object {
  def exit(exitCode: Int = 0): js.Promise[Unit] = js.native
  def relaunch(): js.Promise[Unit] = js.native
}