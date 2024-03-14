package com.harana.web.external.chakra

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@chakra-ui/react", "Wrap")
@js.native
object ReactWrap extends js.Object

@react object Wrap extends ExternalComponent {

  case class Props(align: js.UndefOr[String] = js.undefined,
                   direction: js.UndefOr[String] = js.undefined,
                   justify: js.UndefOr[String] = js.undefined,
                   shouldWrapChildren: js.UndefOr[Boolean] = js.undefined,
                   spacing: js.UndefOr[String | Int] = js.undefined,
                   spacingX: js.UndefOr[String | Int] = js.undefined,
                   spacingY: js.UndefOr[String | Int] = js.undefined)

  override val component = ReactWrap

}