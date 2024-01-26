package com.harana.web.external.latex

import slinky.core.ExternalComponent
import slinky.core.annotations.react
import typings.react.mod.CSSProperties

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, JSName}
import scala.scalajs.js.|

@JSImport("react-latex-next", JSImport.Default)
@js.native
object ReactLatex extends js.Object

@react object Latex extends ExternalComponent {

  case class Props()

  override val component = ReactLatex
}