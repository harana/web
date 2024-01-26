package com.harana.web.external.chakra.grid

import com.harana.js.csstype.mod.Property.GridAutoColumns
import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@chakra-ui/react", "Grid")
@js.native
object ReactGrid extends js.Object

@react object Grid extends ExternalComponent {

  case class Props(autoColumns: js.UndefOr[GridAutoColumns[_]] = js.undefined,
                   autoFlow: js.UndefOr[String] = js.undefined,
                   autoRows: js.UndefOr[String] = js.undefined,
                   column: js.UndefOr[String] = js.undefined,
                   columnGap: js.UndefOr[String] = js.undefined,
                   gap: js.UndefOr[String] = js.undefined,
                   row: js.UndefOr[String] = js.undefined,
                   rowGap: js.UndefOr[String] = js.undefined,
                   templateAreas: js.UndefOr[String] = js.undefined,
                   templateColumns: js.UndefOr[String] = js.undefined,
                   templateRows: js.UndefOr[String] = js.undefined)

  override val component = ReactGrid

}