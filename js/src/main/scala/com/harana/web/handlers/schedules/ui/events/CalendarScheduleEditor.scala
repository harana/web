package com.harana.web.handlers.schedules.ui.events

import com.harana.sdk.shared.models.schedules.Event.{CalendarInterval, CalendarSchedule}
import com.harana.web.components.elements.Dialog
import com.harana.web.external.shoelace.{Button, Input}
import com.harana.web.handlers.files.FilesStore
import com.harana.web.handlers.files.ui.dialogs
import diode.Circuit
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.{Fragment, React}
import slinky.web.html._

@react object CalendarScheduleEditor {

  case class Props(circuit: Circuit[_ <: AnyRef],
                   filesState: FilesStore.State,
                   rowIndex: Int,
                   event: CalendarSchedule)

  private val dialogRef = React.createRef[Dialog.Def]

  def onOk() = {}

  val component = FunctionalComponent[Props] { props =>
    if (dialogRef.current != null)
      dialogs.updateSelect(props.circuit, dialogRef, props.filesState, onOk, width = Some("720px"))

    Fragment(
      Dialog().withRef(dialogRef),
      table(
        tr(
          td(
            Input(
              name = s"${getClass.getSimpleName}-${props.rowIndex}",
              placeholder = Some("0 0 * * *"),
              size = Some("large")
            )
          )
        )
      )
    )
  }
}