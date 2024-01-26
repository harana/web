package com.harana.web.external.chakra.shape

import slinky.core.ExternalComponent
import slinky.core.annotations.react
import com.harana.web.actions.Init

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@chakra-ui/react", "Shape")
@js.native
object ReactShape extends js.Object

@react object Shape extends ExternalComponent {

  case class Props(centerContent: js.UndefOr[String] = js.undefined)

  override val component = ReactShape

}