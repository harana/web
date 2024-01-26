package com.harana.web.external.tailwind.switch

import slinky.core.ExternalComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@headlessui/react", "Switch")
@js.native
object ReactSwitch extends js.Object

@react object Switch extends ExternalComponent {

  case class Props(as: js.UndefOr[String | js.Object] = js.undefined,
                   checked: js.UndefOr[Boolean] = js.undefined,
                   className: js.UndefOr[String] = js.undefined,
                   defaultChecked: js.UndefOr[js.Any] = js.undefined,
                   disabled: js.UndefOr[Boolean] = js.undefined,
                   name: js.UndefOr[String] = js.undefined,
                   onChange: js.UndefOr[js.Any => Unit] = js.undefined,
                   value: js.UndefOr[js.Any] = js.undefined,
                   children: js.Dynamic => ReactElement)

  override val component = ReactSwitch
}