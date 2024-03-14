package com.harana.web.external.ipynb_renderer

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("react-ipynb-renderer/dist/styles/monokai.css?inline", JSImport.Default)
@js.native
object ReactIpynbRendererCSS extends js.Object

@JSImport("react-ipynb-renderer", "IpynbRenderer")
@js.native
object ReactIpynbRenderer extends js.Object

@react object IpynbRenderer extends ExternalComponent {

  case class Props(ipynb: js.Dynamic | js.Object | js.Array[_])

  override val component = ReactIpynbRenderer
}