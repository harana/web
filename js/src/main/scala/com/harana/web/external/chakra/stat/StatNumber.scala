package com.harana.web.external.chakra.stat

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@chakra-ui/react", "StatNumber")
@js.native
object ReactStatNumber extends js.Object

@react object StatNumber extends ExternalComponent {

  case class Props(centerContent: js.UndefOr[String] = js.undefined)

  override val component = ReactStatNumber

}