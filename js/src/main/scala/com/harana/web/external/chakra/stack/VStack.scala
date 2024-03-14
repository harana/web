package com.harana.web.external.chakra.stack

import slinky.core.ExternalComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@chakra-ui/react", "VStack")
@js.native
object ReactVStack extends js.Object

@react object VStack extends ExternalComponent {

  case class Props(align: js.UndefOr[String] = js.undefined,
                   direction: js.UndefOr[String] = js.undefined,
                   divider: js.UndefOr[ReactElement] = js.undefined,
                   isInline: js.UndefOr[Boolean] = js.undefined,
                   justify: js.UndefOr[String] = js.undefined,
                   shouldWrapChildren: js.UndefOr[Boolean] = js.undefined,
                   spacing: js.UndefOr[String | Int] = js.undefined,
                   wrap: js.UndefOr[String] = js.undefined)

  override val component = ReactVStack

}