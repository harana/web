package com.harana.web.external.tailwind.dialog

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@headlessui/react", "Dialog.Panel")
@js.native
object ReactDialogPanel extends js.Object

@react object DialogPanel extends ExternalComponent {

  case class Props(as: js.UndefOr[String | js.Object] = js.undefined)

  override val component = ReactDialogPanel
}