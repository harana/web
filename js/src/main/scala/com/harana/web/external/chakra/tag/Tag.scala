package com.harana.web.external.chakra.tag

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@chakra-ui/react", "Tag")
@js.native
object ReactTag extends js.Object

@react object Tag extends ExternalComponent {

  case class Props(centerContent: js.UndefOr[String] = js.undefined)

  override val component = ReactTag

}