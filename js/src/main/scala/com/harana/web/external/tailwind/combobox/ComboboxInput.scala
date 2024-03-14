package com.harana.web.external.tailwind.combobox

import org.scalajs.dom.Event
import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@headlessui/react", "Combobox.Input")
@js.native
object ReactComboboxInput extends js.Object

@react object ComboboxInput extends ExternalComponent {

  case class Props(as: js.UndefOr[String | js.Object] = js.undefined,
                   autoCapitalize: js.UndefOr[String] = js.undefined,
                   autoCorrect: js.UndefOr[String] = js.undefined,
                   autoComplete: js.UndefOr[String] = js.undefined,
                   className: js.UndefOr[String] = js.undefined,
                   displayValue: js.UndefOr[js.Any => String] = js.undefined,
                   open: js.UndefOr[Boolean] = js.undefined,
                   disabled: js.UndefOr[Boolean] = js.undefined,
                   placeholder: js.UndefOr[String] = js.undefined,
                   spellCheck: js.UndefOr[Boolean] = js.undefined,
                   onFocus: js.UndefOr[Event => Unit] = js.undefined,
                   onChange: js.UndefOr[Event => Unit] = js.undefined)

  override val component = ReactComboboxInput
}