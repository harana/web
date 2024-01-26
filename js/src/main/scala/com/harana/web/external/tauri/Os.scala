package com.harana.web.external.tauri

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@tauri-apps/api", "os")
@js.native
object Os extends js.Object {
  def platform(): js.Promise[String] = js.native
  def version(): js.Promise[String] = js.native
  def `type`(): js.Promise[String] = js.native
  def arch(): js.Promise[String] = js.native
  def tempdir(): js.Promise[String] = js.native
  def locale(): js.Promise[Option[String]] = js.native
}