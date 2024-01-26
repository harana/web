package com.harana.web.external.chakra

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@chakra-ui/react", "Highlight")
@js.native
object ReactHighlight extends js.Object

@react object Highlight extends ExternalComponent {

  case class Props(query: String | List[String],
                   styles: js.Object)

  override val component = ReactHighlight

}