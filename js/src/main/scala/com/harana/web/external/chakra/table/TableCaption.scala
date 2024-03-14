package com.harana.web.external.chakra.table

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@chakra-ui/react", "TableCaption")
@js.native
object ReactTableCaption extends js.Object

@react object TableCaption extends ExternalComponent {

  case class Props(centerContent: js.UndefOr[String] = js.undefined)

  override val component = ReactTableCaption

}