  package com.harana.web.external.chakra.stat

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@chakra-ui/react", "StatGroup")
@js.native
object ReactStatGroup extends js.Object

@react object StatGroup extends ExternalComponent {

  case class Props(centerContent: js.UndefOr[String] = js.undefined)

  override val component = ReactStatGroup

}