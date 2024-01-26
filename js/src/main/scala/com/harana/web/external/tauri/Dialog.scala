package com.harana.web.external.tauri

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@tauri-apps/api", "dialog")
@js.native
object Dialog extends js.Object {
  def open(options: OpenDialogOptions): js.Promise[Option[String]] = js.native
  def save(options: SaveDialogOptions): js.Promise[Option[String]] = js.native
  def message(message: String, options: Option[MessageDialogOptions] = None): js.Promise[Unit] = js.native
  def ask(message: String, options: Option[ConfirmDialogOptions] = None): js.Promise[Boolean] = js.native
  def confirm(message: String, options: Option[ConfirmDialogOptions] = None): js.Promise[Boolean] = js.native
}

@js.native
trait DialogFilter extends js.Object {
  val name: String
  val extensions: List[String]
}

@js.native
trait OpenDialogOptions extends js.Object {
  val title: Option[String]
  val filters: Option[List[DialogFilter]]
  val defaultPath: Option[String]
  val multiple: Option[Boolean]
  val directory: Option[Boolean]
  val recursive: Option[Boolean]
}

@js.native
trait SaveDialogOptions extends js.Object {
  val title: Option[String]
  val filters: Option[List[DialogFilter]]
  val defaultPath: Option[String]
}

@js.native
trait MessageDialogOptions extends js.Object {
  val title: Option[String]
  val `type`: Option[String]
  val okLabel: Option[String]
}

@js.native
trait ConfirmDialogOptions extends js.Object {
  val title: Option[String]
  val `type`: Option[String]
  val okLabel: Option[String]
  val cancelLabel: Option[String]
}

