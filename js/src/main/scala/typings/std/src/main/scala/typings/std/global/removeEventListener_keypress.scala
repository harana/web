package typings.std.global

import typings.std.EventListenerOptions
import typings.std.Window
import typings.std.stdStrings.keypress
import scala.scalajs.js
import scala.scalajs.js.`|`
import scala.scalajs.js.annotation._

@JSGlobal("removeEventListener")
@js.native
object removeEventListener_keypress extends js.Object {
  def apply(
    `type`: keypress,
    listener: js.ThisFunction1[/* this */ Window, /* ev */ typings.std.KeyboardEvent, _]
  ): Unit = js.native
  def apply(
    `type`: keypress,
    listener: js.ThisFunction1[/* this */ Window, /* ev */ typings.std.KeyboardEvent, _],
    options: scala.Boolean
  ): Unit = js.native
  def apply(
    `type`: keypress,
    listener: js.ThisFunction1[/* this */ Window, /* ev */ typings.std.KeyboardEvent, _],
    options: EventListenerOptions
  ): Unit = js.native
}

