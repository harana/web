package com.harana.web.external.tailwind.listbox

import slinky.core.ExternalComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@headlessui/react", "Listbox.Option")
@js.native
object ReactListboxOption extends js.Object

@react object ListboxOption extends ExternalComponent {

  case class Props(as: js.UndefOr[String | js.Object] = js.undefined,
                   className: js.UndefOr[js.Dynamic => String] = js.undefined,
                   key: js.UndefOr[String] = js.undefined,
                   value: js.UndefOr[js.Any | String | Int] = js.undefined,
                   disabled: js.UndefOr[Boolean] = js.undefined,
                   children: js.Dynamic => ReactElement)

  override val component = ReactListboxOption
}