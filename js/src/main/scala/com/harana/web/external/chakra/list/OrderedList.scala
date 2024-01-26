package com.harana.web.external.chakra.list

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@chakra-ui/react", "OrderedList")
@js.native
object ReactOrderedList extends js.Object

@react object OrderedList extends ExternalComponent {

  case class Props(centerContent: js.UndefOr[String] = js.undefined)

  override val component = ReactOrderedList

}