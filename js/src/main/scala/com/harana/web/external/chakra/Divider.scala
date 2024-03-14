package com.harana.web.external.chakra

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@chakra-ui/react", "Divider")
@js.native
object ReactDivider extends js.Object

@react object Divider extends ExternalComponent {

  case class Props(colorScheme: js.UndefOr[String] = js.undefined,
                   size: js.UndefOr[String] = js.undefined,
                   variant: js.UndefOr[String] = js.undefined)

  override val component = ReactDivider

}