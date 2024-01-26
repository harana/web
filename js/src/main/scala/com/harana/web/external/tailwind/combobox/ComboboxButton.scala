package com.harana.web.external.tailwind.combobox

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@headlessui/react", "Combobox.Button")
@js.native
object ReactComboboxButton extends js.Object

@react object ComboboxButton extends ExternalComponent {

  case class Props(as: js.UndefOr[String | js.Object] = js.undefined,
                   className: js.UndefOr[String] = js.undefined,
                   value: js.UndefOr[js.Any] = js.undefined,
                   open: js.UndefOr[Boolean] = js.undefined,
                   disabled: js.UndefOr[Boolean] = js.undefined)

  override val component = ReactComboboxButton
}