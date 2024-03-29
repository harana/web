package typings.std.global

import typings.std.EventListenerOptions
import typings.std.Window
import typings.std.stdStrings.pointerout
import scala.scalajs.js
import scala.scalajs.js.`|`
import scala.scalajs.js.annotation._

@JSGlobal("removeEventListener")
@js.native
object removeEventListener_pointerout extends js.Object {
  def apply(
    `type`: pointerout,
    listener: js.ThisFunction1[/* this */ Window, /* ev */ typings.std.PointerEvent, _]
  ): Unit = js.native
  def apply(
    `type`: pointerout,
    listener: js.ThisFunction1[/* this */ Window, /* ev */ typings.std.PointerEvent, _],
    options: scala.Boolean
  ): Unit = js.native
  def apply(
    `type`: pointerout,
    listener: js.ThisFunction1[/* this */ Window, /* ev */ typings.std.PointerEvent, _],
    options: EventListenerOptions
  ): Unit = js.native
}

