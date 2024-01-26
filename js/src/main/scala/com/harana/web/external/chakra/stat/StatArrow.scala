package com.harana.web.external.chakra.stat

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@chakra-ui/react", "StatArrow")
@js.native
object ReactStatArrow extends js.Object

@react object StatArrow extends ExternalComponent {

  case class Props(centerContent: js.UndefOr[String] = js.undefined)

  override val component = ReactStatArrow

}