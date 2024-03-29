package com.harana.web.handlers.schedules.ui.events

import com.harana.sdk.shared.models.schedules.Event.Gitlab
import com.harana.web.external.shoelace.{Button, Input}
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.Fragment
import slinky.web.html.{table, td, tr}

@react object GitlabEditor {

  case class Props(rowIndex: Int, event: Gitlab)

  val component = FunctionalComponent[Props] { props =>
    Fragment(
      table(
        tr(
          td(
            Input(
              name = s"${getClass.getSimpleName}-${props.rowIndex}",
              placeholder = Some("email@domain.com"),
              size = Some("large")
            )
          ),
          td(
            Button(label = Some("message .."))
          )
        )
      )
    )
  }
}