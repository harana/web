package com.harana.web.external.chakra

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@chakra-ui/react", "Text")
@js.native
object ReactText extends js.Object

@react object Text extends ExternalComponent {

  case class Props(align: js.UndefOr[String] = js.undefined,
                   casing: js.UndefOr[String] = js.undefined,
                   color: js.UndefOr[String] = js.undefined,
                   decoration: js.UndefOr[String] = js.undefined,
                   fontWeight: js.UndefOr[String] = js.undefined,
                   textStyle: js.UndefOr[String] = js.undefined,
                   whiteSpace: js.UndefOr[String] = js.undefined)

  override val component = ReactText

}