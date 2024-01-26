package com.harana.web.external.tailwind.listbox

import slinky.core.ExternalComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@headlessui/react", "Listbox.Options")
@js.native
object ReactListboxOptions extends js.Object

@react object ListboxOptions extends ExternalComponent {

  case class Props(as: js.UndefOr[String | js.Object] = js.undefined,
                   className: js.UndefOr[String] = js.undefined,
                   static: js.UndefOr[Boolean] = js.undefined,
                   unmount: js.UndefOr[Boolean] = js.undefined)

  override val component = ReactListboxOptions
}