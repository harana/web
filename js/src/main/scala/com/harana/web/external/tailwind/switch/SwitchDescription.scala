package com.harana.web.external.tailwind.switch

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@headlessui/react", "Switch.Description")
@js.native
object ReactSwitchDescription extends js.Object

@react object SwitchDescription extends ExternalComponent {

  case class Props(as: js.UndefOr[String | js.Object] = js.undefined)

  override val component = ReactSwitchDescription
}