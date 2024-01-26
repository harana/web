package com.harana.web.external.chakra

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@chakra-ui/react", "WrapItem")
@js.native
object ReactWrapItem extends js.Object

@react object WrapItem extends ExternalComponent {

  case class Props(as: js.UndefOr[String] = js.undefined,
                   bg: js.UndefOr[String] = js.undefined,
                   borderHeight: js.UndefOr[String] = js.undefined,
                   borderRadius: js.UndefOr[String] = js.undefined,
                   borderWidth: js.UndefOr[String] = js.undefined,
                   color: js.UndefOr[String] = js.undefined,
                   fontWeight: js.UndefOr[String] = js.undefined,
                   position: js.UndefOr[String] = js.undefined,
                   px: js.UndefOr[Amount] = js.undefined,
                   py: js.UndefOr[Amount] = js.undefined,
                   w: js.UndefOr[String] = js.undefined)

  override val component = ReactWrapItem

}