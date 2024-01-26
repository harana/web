package com.harana.web.external.tailwind.dialog

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@headlessui/react", "Dialog")
@js.native
object ReactDialog extends js.Object

@react object Dialog extends ExternalComponent {

  case class Props(open: js.UndefOr[Boolean] = js.undefined,
                   className: js.UndefOr[String] = js.undefined,
                   onClose: js.UndefOr[Boolean => Unit] = js.undefined,
                   initialFocus: js.UndefOr[js.Any] = js.undefined,
                   as: js.UndefOr[String | js.Object] = js.undefined,
                   static: js.UndefOr[Boolean] = js.undefined,
                   unmount: js.UndefOr[Boolean] = js.undefined)

  override val component = ReactDialog
}