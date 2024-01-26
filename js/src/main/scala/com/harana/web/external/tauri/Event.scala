package com.harana.web.external.tauri

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@tauri-apps/api", "event")
@js.native
object Event extends js.Object {
  def listen[T](event: String, handler: WindowEventCallback[T]): js.Promise[UnlistenFn] = js.native
  def once[T](event: String, handler: WindowEventCallback[T]): js.Promise[UnlistenFn] = js.native
  def emit(event: String, payload: Option[js.Any]): js.Promise[Unit] = js.native
}