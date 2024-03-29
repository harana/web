package typings.std.global

import typings.std.EventListenerOptions
import typings.std.Window
import typings.std.stdStrings.progress
import scala.scalajs.js
import scala.scalajs.js.`|`
import scala.scalajs.js.annotation._

@JSGlobal("removeEventListener")
@js.native
object removeEventListener_progress extends js.Object {
  def apply(
    `type`: progress,
    listener: js.ThisFunction1[/* this */ Window, /* ev */ typings.std.ProgressEvent[Window], _]
  ): Unit = js.native
  def apply(
    `type`: progress,
    listener: js.ThisFunction1[/* this */ Window, /* ev */ typings.std.ProgressEvent[Window], _],
    options: scala.Boolean
  ): Unit = js.native
  def apply(
    `type`: progress,
    listener: js.ThisFunction1[/* this */ Window, /* ev */ typings.std.ProgressEvent[Window], _],
    options: EventListenerOptions
  ): Unit = js.native
}

