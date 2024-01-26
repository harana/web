package com.harana.web.external.tauri

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@tauri-apps/api", "app")
@js.native
object App extends js.Object {
  def getVersion: js.Promise[String] = js.native
  def getName: js.Promise[String] = js.native
  def getTauriVersion: js.Promise[String] = js.native
  def show: js.Promise[Unit] = js.native
  def hide: js.Promise[Unit] = js.native
}