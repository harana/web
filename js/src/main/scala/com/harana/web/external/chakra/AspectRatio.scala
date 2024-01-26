package com.harana.web.external.chakra

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@chakra-ui/react", "AspectRatio")
@js.native
object ReactAspectRatio extends js.Object

@react object AspectRatio extends ExternalComponent {

  case class Props(ratio: String)

  override val component = ReactAspectRatio

}