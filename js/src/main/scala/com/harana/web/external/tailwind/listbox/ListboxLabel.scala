package com.harana.web.external.tailwind.listbox

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@headlessui/react", "Listbox.Label")
@js.native
object ReactListboxLabel extends js.Object

@react object ListboxLabel extends ExternalComponent {

  case class Props(as: js.UndefOr[String | js.Object] = js.undefined,
                   className: js.UndefOr[String] = js.undefined)

  override val component = ReactListboxLabel
}