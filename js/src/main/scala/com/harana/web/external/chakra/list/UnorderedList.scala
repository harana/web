package com.harana.web.external.chakra.list

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@chakra-ui/react", "UnorderedList")
@js.native
object ReactUnorderedList extends js.Object

@react object UnorderedList extends ExternalComponent {

  case class Props(centerContent: js.UndefOr[String] = js.undefined)

  override val component = ReactUnorderedList

}