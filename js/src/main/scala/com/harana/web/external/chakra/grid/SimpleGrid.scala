package com.harana.web.external.chakra.grid

import com.harana.js.csstype.mod.Property.GridAutoColumns
import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@chakra-ui/react", "SimpleGrid")
@js.native
object ReactSimpleGrid extends js.Object

@react object SimpleGrid extends ExternalComponent {

  case class Props(autoColumns: js.UndefOr[GridAutoColumns[_]] = js.undefined,
                   autoFlow: js.UndefOr[String] = js.undefined,
                   autoRows: js.UndefOr[String] = js.undefined,
                   column: js.UndefOr[String] = js.undefined,
                   columnGap: js.UndefOr[String] = js.undefined,
                   columns: js.UndefOr[Int] = js.undefined,
                   gap: js.UndefOr[String | Int] = js.undefined,
                   minChildWidth: js.UndefOr[String | Int] = js.undefined,
                   row: js.UndefOr[String] = js.undefined,
                   rowGap: js.UndefOr[String | Int] = js.undefined,
                   spacing: js.UndefOr[String | Int] = js.undefined,
                   spacingX: js.UndefOr[String | Int] = js.undefined,
                   spacingY: js.UndefOr[String | Int] = js.undefined,
                   templateAreas: js.UndefOr[String] = js.undefined,
                   templateColumns: js.UndefOr[String] = js.undefined,
                   templateRows: js.UndefOr[String] = js.undefined)

  override val component = ReactSimpleGrid

}