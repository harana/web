package com.harana.web.external.chakra.card

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@chakra-ui/react", "CardFooter")
@js.native
object ReactCardFooter extends js.Object

@react object CardFooter extends ExternalComponent {

  case class Props(centerContent: js.UndefOr[String] = js.undefined)

  override val component = ReactCardFooter

}