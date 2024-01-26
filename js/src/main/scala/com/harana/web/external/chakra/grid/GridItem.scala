package com.harana.web.external.chakra.grid

import slinky.core.ExternalComponent
import slinky.core.annotations.react
import slinky.web.html.div
import typings.csstype.mod.GridAreaProperty

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@chakra-ui/react", "GridItem")
@js.native
object ReactGridItem extends js.Object

@react object GridItem extends ExternalComponent {

  case class Props(area: js.UndefOr[GridAreaProperty] = js.undefined,
                   colEnd: js.UndefOr[Int | String] = js.undefined,
                   colSpan: js.UndefOr[Int | String] = js.undefined,
                   colStart: js.UndefOr[Int | String] = js.undefined,
                   rowEnd: js.UndefOr[Int | String] = js.undefined,
                   rowSpan: js.UndefOr[Int | String] = js.undefined,
                   rowStart: js.UndefOr[Int | String] = js.undefined)

  override val component = ReactGridItem

}