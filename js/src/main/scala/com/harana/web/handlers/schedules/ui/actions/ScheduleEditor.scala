package com.harana.web.handlers.schedules.ui.actions

import com.harana.sdk.shared.models.schedules.{Action, Schedule}
import com.harana.web.external.shoelace.{MenuItem, Select}
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.web.html.{table, td, tr}

@react object ScheduleEditor {

  case class Props(rowIndex: Int, action: Action, schedules: List[Schedule])

  val component = FunctionalComponent[Props] { props =>
    table(
      tr(
        td(
          Select(
            hoist = Some(true),
            name = s"${getClass.getSimpleName}-${props.rowIndex}",
            options = props.schedules.map { s => MenuItem(s.title, value = Some(s.title)) },
            placeholder = Some("Select .."),
            size = Some("large")
          )
        )
      )
    )
  }
}