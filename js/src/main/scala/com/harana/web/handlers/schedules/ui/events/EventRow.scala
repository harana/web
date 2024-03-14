package com.harana.web.handlers.schedules.ui.events

import com.harana.sdk.shared.models.data.DataSource
import com.harana.sdk.shared.models.flow.Flow
import com.harana.web.handlers.schedules.ScheduleStore._
import com.harana.sdk.shared.models.schedules.{Event, Schedule}
import com.harana.web.external.shoelace.{Button, ButtonGroup, MenuItem, Select}
import com.harana.web.utils.i18nUtils.ops
import diode.Circuit
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.web.html.{className, i, td, tr}

@react object EventRow {

  case class Props(circuit: Circuit[_ <: AnyRef],
									 rowIndex: Int,
                   event: Option[Event] = None,
                   datasources: List[DataSource],
                   flows: List[Flow],
                   schedules: List[Schedule],
                   allowDelete: Boolean = true)(rowIndex2: Int)

  val component = FunctionalComponent[Props] { props =>
    tr(
      td(className := "schedule-row-type")(
        Select(
          hoist = Some(true),
          name = s"${getClass.getSimpleName}-${props.rowIndex}",
          value = Some(props.event.map(_.getClass.getSimpleName).getOrElse("")),
          onChange = Some(v => props.circuit.dispatch(UpdateEvent(props.rowIndex, Event.newWithName(v)))),
          placeholder = Some("Select .."),
          options = Event.typesByName.map(a => MenuItem(i"schedules.events.${a.toLowerCase}", value=Some(a))),
          size = Some("large")
        )
      ),
      props.event.map {
        case e @ Event.CalendarInterval(_, _, _)  => CalendarIntervalEditor(props.rowIndex, e)
//        case e @ Event.CalendarSchedule(_, _, _)  => CalendarScheduleEditor(props.circuit, props.rowIndex, e)
        case e @ Event.DataSyncStarted(_)         => DataSyncEditor(props.rowIndex, e, props.datasources)
        case e @ Event.DataSyncFinished(_)        => DataSyncEditor(props.rowIndex, e, props.datasources)
        case e @ Event.DataSyncFailed(_, _)       => DataSyncEditor(props.rowIndex, e, props.datasources)
//        case e @ Event.FileCreated(_, _)          => FileEditor(props.rowIndex, e)(props.circuit)
//        case e @ Event.FileModified(_, _)         => FileEditor(props.circuit, props.rowIndex, e)
//        case e @ Event.FileDeleted(_, _)          => FileEditor(props.circuit, props.rowIndex, e)
        case e @ Event.FlowStarted(_)             => FlowEditor(props.circuit, props.rowIndex, e, props.flows)
        case e @ Event.FlowFinished(_)            => FlowEditor(props.circuit, props.rowIndex, e, props.flows)
        case e @ Event.FlowFailed(_, _)           => FlowEditor(props.circuit, props.rowIndex, e, props.flows)
        case e @ Event.ScheduleStarted(_)         => ScheduleEditor(props.rowIndex, e, props.schedules)
        case e @ Event.ScheduleFinished(_)        => ScheduleEditor(props.rowIndex, e, props.schedules)
        case e @ Event.ScheduleFailed(_, _)       => ScheduleEditor(props.rowIndex, e, props.schedules)
      },
      td(className := "schedule-row-actions")(
        ButtonGroup(label = Some("Buttons"))(
          List(
            Button(icon = Some(("icomoon", "plus3")), iconClassName = Some("schedule-action-button"), circle = Some(true), size = Some("small"), onClick = Some(_ =>
              props.circuit.dispatch(AddEvent(Event.CalendarInterval()))
            )),
            Button(icon = Some(("icomoon", "minus3")), iconClassName = Some("schedule-action-button"), circle = Some(true), size = Some("small"), disabled = Some(!props.allowDelete), onClick = Some(_ =>
              props.circuit.dispatch(DeleteEvent(props.rowIndex))
            ))
          )
        )
      )
    )
  }
}