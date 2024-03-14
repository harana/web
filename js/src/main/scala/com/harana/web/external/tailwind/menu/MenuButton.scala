package com.harana.web.external.tailwind.menu

import slinky.core.ExternalComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@headlessui/react", "Menu.Button")
@js.native
object ReactMenuButton extends js.Object

@react object MenuButton extends ExternalComponent {

  case class Props(as: js.UndefOr[String | js.Object] = js.undefined,
                   className: js.UndefOr[String] = js.undefined)

  override val component = ReactMenuButton
}