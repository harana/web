package com.harana.web.handlers.files.ui

import com.harana.web.handlers.files.FilesStore._
import com.harana.sdk.shared.models.flow.parameters.{Parameter, ParameterGroup, StringParameter}
import com.harana.sdk.shared.utils.HMap
import com.harana.web.components.elements.{Dialog, DialogParameters, DialogStyle, Toolbar}
import com.harana.web.components.table.{GroupedTable, RowGroup}
import com.harana.web.external.filepond.{File, FilePond}
import com.harana.web.external.markdown.Markdown
import com.harana.web.utils.i18nUtils.ops
import diode.Circuit
import slinky.core.facade.ReactRef
import slinky.web.html.{className, div}

import scala.scalajs.js
import scala.scalajs.js.JSConverters._

object dialogs {

  def select(circuit: Circuit[_], dialogRef: ReactRef[Dialog.Def], state: State, onOk: () => Unit, width: Option[String] = None) =
    dialogRef.current.show(
      title = None,
      style = selectStyle(circuit, dialogRef, state, onOk),
      width = width
    )


  def updateSelect(circuit: Circuit[_], dialogRef: ReactRef[Dialog.Def], state: State, onOk: () => Unit, width: Option[String] = None) =
    dialogRef.current.update(style = Some(selectStyle(circuit, dialogRef, state, onOk)), width = width)


  private def selectStyle(circuit: Circuit[_], dialogRef: ReactRef[Dialog.Def], state: State, onOk: () => Unit) =
    DialogStyle.General(
      innerElement =
        div(className := "select-file-table")(
          GroupedTable(
            table.selectColumns,
            rowGroups = List(RowGroup(None, table.selectRows(circuit, state))),
            className = Some("select-file-table")
          )
        ),
      headerElement = Some(
        div(
          Toolbar(circuit, List(toolbar.pathTree(circuit, state), toolbar.create(circuit, dialogRef, state), toolbar.sort(circuit)))
        )
      ),
      okButtonLabel = "Select",
      onOk = Some(onOk)
    )


  def newFolder(circuit: Circuit[_], ref: ReactRef[Dialog.Def]) =
    ref.current.show(
      title = Some(i"files.menu.new.new-folder"),
      style = DialogStyle.Tabbed(
        parametersOrTabs = Left(DialogParameters(
          groups = List(ParameterGroup("new", nameParameter)),
          i18nPrefix = "files.new-folder"
        )),
        onOk = Some(values => circuit.dispatch(NewFolder(values.getOrElse(nameParameter, ""))))
      )
    )


  def uploadFiles(circuit: Circuit[_], ref: ReactRef[Dialog.Def], state: State) =
    ref.current.show(
      title = Some(i"files.menu.new.upload-files"),
      style = DialogStyle.General(
        div(className := "files-upload")(
          FilePond(
            allowMultiple = true,
            allowBrowse = true,
            allowRevert = false,
            files = state.uploadedFiles.toJSArray,
            labelIdle="""Drag & Drop up to 5 files or <span class="filepond--label-action">Browse</span>.""",
            maxFiles = 5,
            server = s"/api/files?path=${state.path}",
            onupdatefiles = Some((files: js.Array[File]) => circuit.dispatch(UpdateUploadedFiles(files.map(_.file).toList))).orUndefined
          )
        ),
        "Close", onOk = Some(() => circuit.dispatch(Refresh))
      ),
      width = Some("500px")
    )


  def editInfo(circuit: Circuit[_], ref: ReactRef[Dialog.Def], state: State) = {
    val nameParameter = StringParameter("name", required = true)

    ref.current.show(
      title = Some(i"files.menu.edit.edit-info"),
      style = DialogStyle.Tabbed(
        parametersOrTabs = Left(DialogParameters(
          groups = List(ParameterGroup("", nameParameter)),
          i18nPrefix = "files.edit-info",
        )),
        onOk = Some(values => circuit.dispatch(EditItemInfo(values)))
      ),
      values = Some(HMap[Parameter.Values]((nameParameter, state.selectedFile.map(_.name).getOrElse(""))))
    )
  }

  def deleteFiles(circuit: Circuit[_], ref: ReactRef[Dialog.Def]) =
    ref.current.show(
      title = Some(i"files.menu.edit.delete"),
      style = DialogStyle.Confirm("Are you sure you want to delete this file ?", "Delete", onOk = Some(() => circuit.dispatch(DeleteItem)))
    )

  def mountDrive(ref: ReactRef[Dialog.Def], state: State) =
    ref.current.show(
      title = Some(i"files.mount-drive.title"),
      style = DialogStyle.Tabbed(
        parametersOrTabs = Right(List(
          i"files.mount-drive.group.mac.title" -> Markdown(state.content.getOrElse("files.mount-drive.mac", "")),
          i"files.mount-drive.group.windows.title" -> Markdown(state.content.getOrElse("files.mount-drive.windows", "")),
          i"files.mount-drive.group.linux.title" -> Markdown(state.content.getOrElse("files.mount-drive.linux", ""))
        )),
        showCancelButton = false
      )
    )

  def connectViaSFTP(ref: ReactRef[Dialog.Def]) =
    ref.current.show(
      title = Some(i"files.connect-via-sftp.title"),
      style = DialogStyle.Tabbed(
        parametersOrTabs = Left(DialogParameters(
          groups = List(ParameterGroup("")),
          i18nPrefix = "files.connect-via-sftp",
        )),
        showCancelButton = false
      )
    )
}