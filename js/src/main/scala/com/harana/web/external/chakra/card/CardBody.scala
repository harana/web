package com.harana.web.external.chakra.card

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@chakra-ui/react", "CardBody")
@js.native
object ReactCardBody extends js.Object

@react object CardBody extends ExternalComponent {

  case class Props(centerContent: js.UndefOr[String] = js.undefined)

  override val component = ReactCardBody

}