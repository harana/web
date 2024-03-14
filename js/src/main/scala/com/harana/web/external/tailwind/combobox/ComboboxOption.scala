package com.harana.web.external.tailwind.combobox

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@headlessui/react", "Combobox.Option")
@js.native
object ReactComboboxOption extends js.Object

@react object ComboboxOption extends ExternalComponent {

  case class Props(as: js.UndefOr[String | js.Object] = js.undefined,
                   className: js.UndefOr[String] = js.undefined,
                   key: js.UndefOr[String] = js.undefined,
                   value: js.UndefOr[String] | js.UndefOr[js.Any] = js.undefined,
                   disabled: js.UndefOr[Boolean] = js.undefined,
                   active: js.UndefOr[Boolean] = js.undefined,
                   selected: js.UndefOr[Boolean] = js.undefined)

  override val component = ReactComboboxOption
}