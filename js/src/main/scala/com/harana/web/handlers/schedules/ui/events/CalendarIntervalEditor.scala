package com.harana.web.handlers.schedules.ui.events

import com.harana.sdk.shared.models.schedules.Event
import com.harana.web.components.elements.Dialog
import com.harana.web.external.shoelace.{Button, Input, MenuItem, Select}
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.{Fragment, React}
import slinky.web.html._

@react object CalendarIntervalEditor {

  case class Props(rowIndex: Int, event: Event.CalendarInterval)

  private val dialogRef = React.createRef[Dialog.Def]

  def onOk() = {
  }

  val component = FunctionalComponent[Props] { props =>
    Fragment(
      table(
        tr(
          td(
            Input(
              name = s"${getClass.getSimpleName}-${props.rowIndex}",
              placeholder = None,
              size = Some("large"),
              maxLength = Some(5),
              length = Some(5),
              numbersOnly = Some(true),
              `type` = Some("number")
            )
          ),
          td(
            Select(
              hoist = Some(true),
              name = s"${getClass.getSimpleName}-${props.rowIndex}",
              onChange = Some(id => {}),
              placeholder = Some("Select .."),
              options = List("seconds", "minutes", "days", "weeks", "months").map { o => MenuItem(o, value = Some(o)) },
              size = Some("large")
            )
          )
        )
      )
    )
  }
}