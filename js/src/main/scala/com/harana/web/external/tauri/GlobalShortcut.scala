package com.harana.web.external.tauri

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@tauri-apps/api", "globalShortcut")
@js.native
object GlobalShortcut extends js.Object {
  def register(shortcut: String, handler: String => Unit): js.Promise[Unit] = js.native
  def registerAll(shortcuts: Array[String], handler: String => Unit): js.Promise[Unit] = js.native
  def isRegistered(shortcut: String): js.Promise[Boolean] = js.native
  def unregister(shortcut: String): js.Promise[Unit] = js.native
  def unregisterAll(): js.Promise[Unit] = js.native
}