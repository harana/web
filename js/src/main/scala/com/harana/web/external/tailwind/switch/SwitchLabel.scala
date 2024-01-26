package com.harana.web.external.tailwind.switch

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@headlessui/react", "Switch.Label")
@js.native
object ReactSwitchLabel extends js.Object

@react object SwitchLabel extends ExternalComponent {

  case class Props(as: js.UndefOr[String | js.Object] = js.undefined,
                   className: js.UndefOr[String | js.Object] = js.undefined,
                   passive: js.UndefOr[Boolean] = js.undefined)

  override val component = ReactSwitchLabel
}