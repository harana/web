package com.harana.web.external.tailwind.combobox

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@headlessui/react", "Combobox")
@js.native
object ReactCombobox extends js.Object

@react object Combobox extends ExternalComponent {

  case class Props(as: js.UndefOr[String | js.Object] = js.undefined,
                   disabled: js.UndefOr[Boolean] = js.undefined,
                   value: js.UndefOr[js.Any] = js.undefined,
                   className: js.UndefOr[String] = js.undefined,
                   defaultValue: js.UndefOr[js.Any] = js.undefined,
                   by: js.UndefOr[(js.Any, js.Any) => Boolean] = js.undefined,
                   onChange: js.UndefOr[js.Any => Unit] = js.undefined,
                   name: js.UndefOr[String] = js.undefined,
                   nullable: js.UndefOr[Boolean] = js.undefined,
                   multiple: js.UndefOr[Boolean] = js.undefined,
                   activeIndex: js.UndefOr[Int] = js.undefined,
                   activeOption: js.UndefOr[js.Any] = js.undefined)

  override val component = ReactCombobox
}