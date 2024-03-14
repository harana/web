package com.harana.web.handlers.schedules.ui.events

import com.harana.sdk.shared.models.flow.Flow
import com.harana.sdk.shared.models.schedules.Event
import com.harana.web.external.shoelace.{Button, Input, MenuItem, Select}
import diode.Circuit
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.Fragment
import slinky.web.html.{div, table, td, tr}

@react object FlowEditor {

  case class Props(circuit: Circuit[_], rowIndex: Int, event: Event, flows: List[Flow])

  val component = FunctionalComponent[Props] { props =>
    Fragment(
      table(
        tr(
          td(
            Select(
              hoist = Some(true),
              name = s"${getClass.getSimpleName}-${props.rowIndex}",
              onChange = Some(id => {}),
              placeholder = Some("Select .."),
              options = List(props.flows.map(f => MenuItem(f.title, value = Some(f.id)))),
              size = Some("large")
            )
          )
        )
      )
    )
  }
}