package com.harana.web.external.tailwind.combobox

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@headlessui/react", "Combobox.Label")
@js.native
object ReactComboboxLabel extends js.Object

@react object ComboboxLabel extends ExternalComponent {

  case class Props(as: js.UndefOr[String | js.Object] = js.undefined,
                   open: js.UndefOr[Boolean] = js.undefined,
                   disabled: js.UndefOr[Boolean] = js.undefined)

  override val component = ReactComboboxLabel
}