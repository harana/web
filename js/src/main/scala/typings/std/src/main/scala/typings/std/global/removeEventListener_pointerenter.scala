package typings.std.global

import typings.std.EventListenerOptions
import typings.std.Window
import typings.std.stdStrings.pointerenter
import scala.scalajs.js
import scala.scalajs.js.`|`
import scala.scalajs.js.annotation._

@JSGlobal("removeEventListener")
@js.native
object removeEventListener_pointerenter extends js.Object {
  def apply(
    `type`: pointerenter,
    listener: js.ThisFunction1[/* this */ Window, /* ev */ typings.std.PointerEvent, _]
  ): Unit = js.native
  def apply(
    `type`: pointerenter,
    listener: js.ThisFunction1[/* this */ Window, /* ev */ typings.std.PointerEvent, _],
    options: scala.Boolean
  ): Unit = js.native
  def apply(
    `type`: pointerenter,
    listener: js.ThisFunction1[/* this */ Window, /* ev */ typings.std.PointerEvent, _],
    options: EventListenerOptions
  ): Unit = js.native
}

