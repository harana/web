package com.harana.web.external.chakra.stack

import com.harana.web.external.chakra.Amount
import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@chakra-ui/react", "Stack")
@js.native
object ReactStack extends js.Object

@react object Stack extends ExternalComponent {

  case class Props(align: js.UndefOr[String] = js.undefined,
                   direction: js.UndefOr[String] = js.undefined,
                   divider: js.UndefOr[String] = js.undefined,
                   isInline: js.UndefOr[Boolean] = js.undefined,
                   justify: js.UndefOr[String] = js.undefined,
                   shouldWrapChildren: js.UndefOr[Boolean] = js.undefined,
                   spacing: js.UndefOr[String | Int | Amount] = js.undefined,
                   textAlign: js.UndefOr[String] = js.undefined,
                   width: js.UndefOr[String] = js.undefined,
                   wrap: js.UndefOr[String] = js.undefined)

  override val component = ReactStack

}