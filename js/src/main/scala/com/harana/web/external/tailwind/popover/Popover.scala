package com.harana.web.external.tailwind.popover

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@headlessui/react", "Popover")
@js.native
object ReactPopover extends js.Object

@react object Popover extends ExternalComponent {

  case class Props(as: js.UndefOr[String | js.Object] = js.undefined,
                   className: js.UndefOr[String] = js.undefined,
                   open: js.UndefOr[Boolean] = js.undefined,
                   onOpen: js.UndefOr[() => Unit] = js.undefined)

  override val component = ReactPopover
}