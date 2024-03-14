  package com.harana.web.external.chakra.table

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@chakra-ui/react", "Thead")
@js.native
object ReactThead extends js.Object

@react object Thead extends ExternalComponent {

  case class Props(centerContent: js.UndefOr[String] = js.undefined)

  override val component = ReactThead

}