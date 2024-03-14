package com.harana.web.external.chakra

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@chakra-ui/react", "Heading")
@js.native
object ReactHeading extends js.Object

@react object Heading extends ExternalComponent {

  case class Props(colorScheme: js.UndefOr[String] = js.undefined,
                   size: js.UndefOr[String | Amount] = js.undefined,
                   variant: js.UndefOr[String] = js.undefined)

  override val component = ReactHeading

}