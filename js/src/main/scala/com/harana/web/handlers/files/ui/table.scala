package com.harana.web.handlers.files.ui

import com.harana.web.handlers.files.FilesStore.{PushPath, State, UpdateSelectedFile}
import com.harana.web.components.ColumnSize
import com.harana.web.components.Device.Desktop
import com.harana.web.components.elements.{DataFileName, PrettyDate}
import com.harana.web.components.table.{Column, Row}
import com.harana.web.external.shoelace.Radio
import com.harana.web.utils.SizeUtils
import com.harana.web.utils.i18nUtils.ops
import diode.Circuit
import slinky.core.facade.ReactElement
import slinky.web.html.p

object table {

  val columns = List(
    Column(Some(i"files.table.column.name"), Map(Desktop -> ColumnSize.Five)),
    Column(Some(i"files.table.column.size"), Map(Desktop -> ColumnSize.Two)),
    Column(Some(i"files.table.column.updated"), Map(Desktop -> ColumnSize.Two)),
    Column(Some(i"files.table.column.tags"), Map(Desktop -> ColumnSize.Three))
  )

  val selectColumns = List(
    Column(Some(i"files.table.column.name"), Map(Desktop -> ColumnSize.Six)),
    Column(Some(i"files.table.column.size"), Map(Desktop -> ColumnSize.Three)),
    Column(Some(i"files.table.column.updated"), Map(Desktop -> ColumnSize.Three))
  )

  def rows(circuit: Circuit[_], state: State): List[Row] =
    state.files.zipWithIndex.map { case (file, index) =>
      val selected = state.selectedFile.nonEmpty && state.selectedFile.get.equals(file)

      Row(
        Map[Column, ReactElement](
          columns.head -> DataFileName(file).withKey(s"datafilename-$index"),
          columns(1) -> p(SizeUtils.format(file.size)),
          columns(2) -> PrettyDate(file.updated),
          columns(3) -> ""
        ),
        radio = Some(Radio.Props(name = "s", checked = Some(selected), onChange = Some(value => if (value) circuit.dispatch(UpdateSelectedFile(Some(file)))))),
        onDoubleClick = Some(() => circuit.dispatch(PushPath(file.name)))
      )
    }

  def selectRows(circuit: Circuit[_], state: State): List[Row] =
    state.files.zipWithIndex.map { case (file, index) =>
      val selected = state.selectedFile.nonEmpty && state.selectedFile.get.equals(file)

      Row(
        Map[Column, ReactElement](
          selectColumns.head -> DataFileName(file).withKey(s"datafilename-$index"),
          selectColumns(1) -> p(SizeUtils.format(file.size)),
          selectColumns(2) -> PrettyDate(file.updated)
        ),
        onDoubleClick = Some(() => circuit.dispatch(PushPath(file.name))),
        radio =
          if (file.isFolder)
            Some(Radio.Props(name = "s", checked = Some(selected), onChange = Some(value => if (value) circuit.dispatch(UpdateSelectedFile(Some(file))))))
          else
            None
      )
    }
}