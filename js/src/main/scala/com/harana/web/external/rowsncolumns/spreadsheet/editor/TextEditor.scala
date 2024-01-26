package com.harana.web.external.rowsncolumns.spreadsheet.editor

import com.harana.web.external.rowsncolumns.Direction
import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("react-popconfirm", JSImport.Default)
@js.native
object ReactTextEditor extends js.Object

@react object TextEditor extends ExternalComponent {

  case class Props(value: String,
                   onChange: String => Unit,
                   onSubmit: (String, Direction) => Unit,
                   onCancel: () => Unit,
                   fontFamily: String,
                   fontSize: Int,
                   scale: Int,
                   color: String,
                   wrapping: js.Any,
                   horizontalAlign: js.Any,
                   underline: Boolean)

  override val component = ReactTextEditor
}