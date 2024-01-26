package com.harana.web.external.chakra.tag

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@chakra-ui/react", "TagLabel")
@js.native
object ReactTagLabel extends js.Object

@react object TagLabel extends ExternalComponent {

  case class Props(centerContent: js.UndefOr[String] = js.undefined)

  override val component = ReactTagLabel

}