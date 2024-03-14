package com.harana.web.external.json_lens

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("react-json-lens", "JSONView")
@js.native
object ReactJsonLens extends js.Object

@react object JsonLens extends ExternalComponent {

  case class Props(value: js.Dynamic | js.Object | js.Array[_])

  override val component = ReactJsonLens
}