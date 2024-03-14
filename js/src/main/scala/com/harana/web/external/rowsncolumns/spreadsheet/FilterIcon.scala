package com.harana.web.external.rowsncolumns.spreadsheet

import com.harana.web.external.rowsncolumns.grid.CellInterface
import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@rowsncolumns/spreadsheet", "FilterIcon")
@js.native
object ReactFilterIcon extends js.Object

@react object FilterIcon extends ExternalComponent {

  case class Props(rowIndex: Int,
                   columnIndex: Int,
                   isActive: Boolean,
                   onClick: js.UndefOr[CellInterface => Unit] = js.undefined)

  override val component = ReactFilterIcon
}