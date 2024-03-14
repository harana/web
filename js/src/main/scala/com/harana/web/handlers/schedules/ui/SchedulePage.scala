package com.harana.web.handlers.schedules.ui

import com.harana.sdk.shared.models.data.DataSource
import com.harana.sdk.shared.models.flow.Flow
import com.harana.sdk.shared.models.jwt.HaranaClaims
import com.harana.web.pages.grid.ui.{GridPage, GridPageItem}
import com.harana.sdk.shared.models.schedules.{Schedule, ScheduleExecution, ScheduleExecutionStatus}
import com.harana.web.components.ColumnSize
import com.harana.web.components.Device.Desktop
import com.harana.web.components.elements.{Drawer, PrettyDate}
import com.harana.web.components.sidebar.SidebarSection
import com.harana.web.components.table.{Column, TagsColumn}
import com.harana.web.external.shoelace.{MenuItem, Tooltip}
import com.harana.web.handlers.data.list.DataSourceListStore
import com.harana.web.handlers.schedules.ScheduleStore
import com.harana.web.handlers.schedules.ui.actions.ActionRow
import com.harana.web.handlers.schedules.ui.events.EventRow
import com.harana.web.handlers.user.UserStore
import com.harana.web.pages.grid.GridStore.GridState
import com.harana.web.utils.DateUtils
import com.harana.web.utils.i18nUtils.ops
import diode.Circuit
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.{Fragment, React, ReactElement}
import slinky.web.html._

import scala.scalajs.js.Dynamic.literal


case class SchedulesPage(circuit: Circuit[_ <: AnyRef],
                         claims: HaranaClaims,
                         state: GridState[Schedule, ScheduleStore.AdditionalState],
                         dataState: GridState[DataSource, DataSourceListStore.AdditionalState],
                         userState: UserStore.State,
                         navigationBar: ReactElement) {

  @react object Component {
    type Props = Unit

    val titleColumn = Column(Some(i"schedules.common.title"), Map(Desktop -> ColumnSize.Four))
    val tagsColumn = Column(Some(i"schedules.common.tags"), Map(Desktop -> ColumnSize.Three))
    val historyColumn = Column(Some(i"schedules.common.history"), Map(Desktop -> ColumnSize.Five))

    val component = FunctionalComponent[Unit] { _ =>
      val drawerRef = React.createRef[Drawer.Def]

      val green = "#26a69a"
      val red = "rgb(184,65,102)"
      val orange = "rgb(207,144,58)"
      val grey = "rgb(200,200,200)"

      Fragment(
        Drawer.withRef(drawerRef),
        GridPage(
          circuit = circuit,
          entityType = "schedules",
          state = state,
          userId = claims.userId,
          title = i"heading.section.schedules",
          navigationBar = Some(navigationBar),
          showTags = true,
          itemMenuItems = Some((item: GridPageItem[_]) =>
            List(
              MenuItem(i"apps.menu.stop",
                iconPrefix = Some("lindua", "repeat"),
                onClick = None)
            )
          ),
          tableColumns = List(titleColumn, tagsColumn, historyColumn),
          tableContent = (column: Column, item: GridPageItem[_]) => {
            val schedule = item.entity.asInstanceOf[Schedule]
            val execution = schedule.executions.lastOption

            column match {
              case `titleColumn` => div(schedule.title)
              case `tagsColumn` => TagsColumn(Set("confidential", "engineering"))
              case `historyColumn` =>
                val executions = item.additionalData("recentExecutions").asInstanceOf[List[ScheduleExecution]]
                div(className := "schedules-container")(
                  executions.map { execution =>
                    val color = execution.status match {
                      case ScheduleExecutionStatus.None => grey
                      case ScheduleExecutionStatus.Executing => green
                      case ScheduleExecutionStatus.PendingCancellation => red
                      case ScheduleExecutionStatus.PendingExecution => orange
                      case ScheduleExecutionStatus.Failed => red
                      case ScheduleExecutionStatus.Killed => red
                      case ScheduleExecutionStatus.Cancelled => red
                      case ScheduleExecutionStatus.Initialised => grey
                      case ScheduleExecutionStatus.Paused => orange
                      case ScheduleExecutionStatus.Succeeded => green
                      case ScheduleExecutionStatus.TimedOut => red
                    }

                    Tooltip(content = DateUtils.format(execution.started), showDelay = Some(0.1), className = Some("schedules-pill-tooltip"))(
                      List(div(className := "schedules-pill", style := literal("backgroundColor" -> color), onClick := Some(() => dialogs.execution(drawerRef, schedule, executions, execution))))
                    )
                  },
                  Range(executions.size, 40).map { _ =>
                    div(className := "schedules-pill", style := literal("backgroundColor" -> grey))
                  }

                )
            }
          },
          rightSidebarSections = List(
            SidebarSection(
              Some(i"schedules.sidebar.history"),
              className = Some("schedules-history"),
              content =
                div(className := "items")(
                  state.additionalState.history.map(pair =>
                    div(className := "item")(
                      statusIcon(pair._1.status),
                      div(className := "title")(pair._2.title),
                      div(className := "info")(PrettyDate(pair._2.created))
                    )
                  )
                )
            )
          ),
          editWidth = Some("500px"),
          editAdditionalSections = List()
// FIXME
//          editAdditionalSections = List(
//            (i"schedules.groups.events", div(className := "schedule-editor")(
//              table(tbody(state.additionalState.itemEvents.zipWithIndex.map { case (a, index) =>
//                EventRow(circuit, index, Some(a._2), dataState.entities, state.entities, allowDelete = state.additionalState.itemEvents.size > 1)
//              }))
//            )),
//            (i"schedules.groups.actions", div(className := "schedule-editor")(
//              table(tbody(state.additionalState.itemActions.zipWithIndex.map { case (a, index) =>
//                ActionRow(circuit, index, Some(a._2), dataState.entities, state.entities, allowDelete = state.additionalState.itemActions.size > 1)
//              }))
//            ))
//          )
        )
      )
    }
  }
}