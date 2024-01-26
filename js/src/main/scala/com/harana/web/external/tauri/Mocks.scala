package com.harana.web.external.tauri

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import typings.std.Record

import scala.scalajs.js.|

@JSImport("@tauri-apps/api", "mocks")
@js.native
object Mocks extends js.Object {
  def mockIPC(cb: (String, Map[String, js.Any]) => js.Any | js.Promise[js.Any]): Unit = js.native
  def mockWindows(current: String, additionalWindows: String*): Unit = js.native
}