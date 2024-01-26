package com.harana.web.external.tauri

import com.harana.web.external.tauri.Tauri.invoke

import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.{Dictionary, Function1, Thenable, |}
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.annotation.JSImport

@JSImport("@tauri-apps/api", "tauri")
@js.native
object Tauri extends js.Object {
  def convertFileSrc(filePath: String, protocol: js.UndefOr[String] = js.undefined): String = js.native
  def invoke[T, S <: Any](cmd: String, args: Dictionary[S] = Dictionary.empty[S]): js.Promise[T] = js.native
  def transformCallback(callback: Any => Unit, once: Boolean): Int = js.native
}