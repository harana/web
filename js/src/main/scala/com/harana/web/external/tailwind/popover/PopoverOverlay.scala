package com.harana.web.external.tailwind.popover

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@headlessui/react", "Popover.Overlay")
@js.native
object ReactPopoverOverlay extends js.Object

@react object PopoverOverlay extends ExternalComponent {

  case class Props(as: js.UndefOr[String | js.Object] = js.undefined,
                   className: js.UndefOr[String] = js.undefined,
                   open: js.UndefOr[Boolean] = js.undefined)

  override val component = ReactPopoverOverlay
}