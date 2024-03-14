package com.harana.web.external.tailwind.combobox

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@headlessui/react", "Combobox.Options")
@js.native
object ReactComboboxOptions extends js.Object

@react object ComboboxOptions extends ExternalComponent {

  case class Props(as: js.UndefOr[String | js.Object] = js.undefined,
                   className: js.UndefOr[String] = js.undefined,
                   static: js.UndefOr[Boolean] = js.undefined,
                   unmount: js.UndefOr[Boolean] = js.undefined,
                   hold: js.UndefOr[Boolean] = js.undefined,
                   open: js.UndefOr[Boolean] = js.undefined)

  override val component = ReactComboboxOptions
}