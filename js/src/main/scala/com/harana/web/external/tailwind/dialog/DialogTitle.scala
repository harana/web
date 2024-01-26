package com.harana.web.external.tailwind.dialog

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@headlessui/react", "Dialog.Title")
@js.native
object ReactDialogTitle extends js.Object

@react object DialogTitle extends ExternalComponent {

  case class Props(as: js.UndefOr[String | js.Object] = js.undefined)

  override val component = ReactDialogTitle
}