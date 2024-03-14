package com.harana.web.external.tailwind.listbox

import slinky.core.ExternalComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@headlessui/react", "Listbox")
@js.native
object ReactListbox extends js.Object

@react object Listbox extends ExternalComponent {

  case class Props(as: js.UndefOr[String | js.Object] = js.undefined,
                   className: js.UndefOr[String] = js.undefined,
                   disabled: js.UndefOr[Boolean] = js.undefined,
                   value: js.UndefOr[js.Any | String | Int] = js.undefined,
                   defaultValue: js.UndefOr[js.Any] = js.undefined,
                   by: js.UndefOr[(js.Any, js.Any) => Boolean] = js.undefined,
                   onChange: js.UndefOr[js.Any => Unit] = js.undefined,
                   horizontal: js.UndefOr[Boolean] = js.undefined,
                   name: js.UndefOr[String] = js.undefined,
                   multiple: js.UndefOr[Boolean] = js.undefined,
                   children: js.Dynamic => ReactElement)

  override val component = ReactListbox
}