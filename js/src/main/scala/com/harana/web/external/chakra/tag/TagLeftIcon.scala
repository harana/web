package com.harana.web.external.chakra.tag

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@chakra-ui/react", "TagLeftIcon")
@js.native
object ReactTagLeftIcon extends js.Object

@react object TagLeftIcon extends ExternalComponent {

  case class Props(centerContent: js.UndefOr[String] = js.undefined)

  override val component = ReactTagLeftIcon

}