package com.harana.web.external.tailwind.popover

import org.scalajs.dom.HTMLElement
import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@headlessui/react", "Popover.Panel")
@js.native
object ReactPopoverPanel extends js.Object

@react object PopoverPanel extends ExternalComponent {

  case class Props(as: js.UndefOr[String | js.Object] = js.undefined,
                   className: js.UndefOr[String] = js.undefined,
                   focus: js.UndefOr[Boolean] = js.undefined,
                   static: js.UndefOr[Boolean] = js.undefined,
                   unmount: js.UndefOr[Boolean] = js.undefined,
                   open: js.UndefOr[Boolean] = js.undefined,
                   close: js.UndefOr[HTMLElement => Unit] = js.undefined)

  override val component = ReactPopoverPanel
}