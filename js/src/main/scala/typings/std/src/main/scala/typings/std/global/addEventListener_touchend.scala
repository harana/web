package typings.std.global

import typings.std.AddEventListenerOptions
import typings.std.Window
import typings.std.stdStrings.touchend
import scala.scalajs.js
import scala.scalajs.js.`|`
import scala.scalajs.js.annotation._

@JSGlobal("addEventListener")
@js.native
object addEventListener_touchend extends js.Object {
  def apply(
    `type`: touchend,
    listener: js.ThisFunction1[/* this */ Window, /* ev */ typings.std.TouchEvent, _]
  ): Unit = js.native
  def apply(
    `type`: touchend,
    listener: js.ThisFunction1[/* this */ Window, /* ev */ typings.std.TouchEvent, _],
    options: scala.Boolean
  ): Unit = js.native
  def apply(
    `type`: touchend,
    listener: js.ThisFunction1[/* this */ Window, /* ev */ typings.std.TouchEvent, _],
    options: AddEventListenerOptions
  ): Unit = js.native
}

