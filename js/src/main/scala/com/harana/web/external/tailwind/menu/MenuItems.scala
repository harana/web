package com.harana.web.external.tailwind.menu

import slinky.core.ExternalComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@headlessui/react", "Menu.Items")
@js.native
object ReactMenuItems extends js.Object

@react object MenuItems extends ExternalComponent {

  case class Props(as: js.UndefOr[String | js.Object] = js.undefined,
                   className: js.UndefOr[String] = js.undefined,
                   static: js.UndefOr[Boolean] = js.undefined,
                   style: js.UndefOr[String] = js.undefined,
                   unmount: js.UndefOr[Boolean] = js.undefined,
                   children: js.UndefOr[js.Dynamic => ReactElement] = js.undefined)

  override val component = ReactMenuItems
}