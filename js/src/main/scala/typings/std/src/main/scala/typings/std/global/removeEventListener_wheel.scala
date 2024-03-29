package typings.std.global

import typings.std.EventListenerOptions
import typings.std.Window
import typings.std.stdStrings.wheel
import scala.scalajs.js
import scala.scalajs.js.`|`
import scala.scalajs.js.annotation._

@JSGlobal("removeEventListener")
@js.native
object removeEventListener_wheel extends js.Object {
  def apply(`type`: wheel, listener: js.ThisFunction1[/* this */ Window, /* ev */ typings.std.WheelEvent, _]): Unit = js.native
  def apply(
    `type`: wheel,
    listener: js.ThisFunction1[/* this */ Window, /* ev */ typings.std.WheelEvent, _],
    options: scala.Boolean
  ): Unit = js.native
  def apply(
    `type`: wheel,
    listener: js.ThisFunction1[/* this */ Window, /* ev */ typings.std.WheelEvent, _],
    options: EventListenerOptions
  ): Unit = js.native
}

