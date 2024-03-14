package com.harana.web.external.tailwind.switch

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@headlessui/react", "Switch.Group")
@js.native
object ReactSwitchGroup extends js.Object

@react object SwitchGroup extends ExternalComponent {

  case class Props(as: js.UndefOr[String | js.Object] = js.undefined,
                   className: js.UndefOr[String] = js.undefined)

  override val component = ReactSwitchGroup
}