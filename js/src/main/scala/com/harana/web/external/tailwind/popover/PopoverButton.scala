package com.harana.web.external.tailwind.popover

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@headlessui/react", "Popover.Button")
@js.native
object ReactPopoverButton extends js.Object

@react object PopoverButton extends ExternalComponent {

  case class Props(as: js.UndefOr[String | js.Object] = js.undefined,
                   className: js.UndefOr[String] = js.undefined,
                   open: js.UndefOr[Boolean] = js.undefined,
                   onClick: js.UndefOr[() => js.Any] = js.undefined)

  override val component = ReactPopoverButton
}