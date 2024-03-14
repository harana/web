package com.harana.web.external.chakra.tag

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@chakra-ui/react", "TagCloseButton")
@js.native
object ReactTagCloseButton extends js.Object

@react object TagCloseButton extends ExternalComponent {

  case class Props(centerContent: js.UndefOr[String] = js.undefined)

  override val component = ReactTagCloseButton

}