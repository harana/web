package com.harana.web.handlers.schedules.ui.events

import com.harana.sdk.shared.models.schedules.Event
import com.harana.web.components.elements.Dialog
import com.harana.web.external.shoelace.{Button, Input}
import com.harana.web.handlers.files.FilesStore
import com.harana.web.handlers.files.ui.dialogs
import diode.Circuit
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.{Fragment, React}
import slinky.web.html.{table, td, tr}

@react object FileEditor {

  case class Props(state: FilesStore.State,
                   rowIndex: Int,
                   event: Event)(implicit val circuit: Circuit[_])

  private val dialogRef = React.createRef[Dialog.Def]

  val component = FunctionalComponent[Props] { props =>
    if (dialogRef.current != null)
      dialogs.updateSelect(props.circuit, dialogRef, props.state, () => {}, width = Some("720px"))

    Fragment(
      Dialog().withRef(dialogRef),
      table(
        tr(
          td(
            Input(
              name = s"${getClass.getSimpleName}-${props.rowIndex}",
              size = Some("large"),
              value = Some("/")
            )
          ),
          td(
            Button(
              icon = Some("icomoon", "folder6"),
              iconClassName = Some("schedule-action-options"),
              onClick = Some(_ => dialogs.select(props.circuit, dialogRef, props.state, () => println("hi"), width = Some("720px")))
            )
          )
        )
      )
    )
  }
}