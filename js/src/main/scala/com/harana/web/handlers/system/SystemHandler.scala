package com.harana.web.handlers.system

import com.harana.sdk.shared.models.common.Event
import com.harana.web.InitialState
import com.harana.web.handlers.system.SystemStore._
import com.harana.web.handlers.user.UserStore
import com.harana.web.pages.grid.GridStore.{ReceiveEvent => GridReceiveEvent}
import com.harana.web.utils.error.Error
import com.harana.web.utils.http.Http
import diode.AnyAction.aType
import diode._
import org.scalajs.macrotaskexecutor.MacrotaskExecutor.Implicits._

import scala.concurrent.Future

class SystemHandler[S](state: ModelRW[S, SystemStore.State]) extends ActionHandler(state) {

  override def handle = {

    case InitSourceMaps =>
      effectOnly(
        Effect(Error.init(List(InitialState.jsBundle)).map(_ => NoAction))
      )


    case ClearSourceMaps =>
      Error.clear
      noChange


    case RefreshEvents =>
      effectOnly(
        Effect(Http.getRelativeAs[List[Event]](s"/api/events").map(events => UpdateEvents(events.getOrElse(List.empty))))
      )


    case UpdateEvents(events) =>
      updated(value.copy(events = events), Effect(
        Future {
          events.foreach { e =>
            // FIXME
            //Analytics.receiveEvent(e.eventType, e.parameters)
            //circuit.dispatch(FilesReceiveEvent(e.eventType, e.parameters))
            //circuit.dispatch(GridReceiveEvent("apps", e.eventType, e.parameters))
            //circuit.dispatch(GridReceiveEvent("datasources", e.eventType, e.parameters))
            //circuit.dispatch(GridReceiveEvent("flows", e.eventType, e.parameters))
            //circuit.dispatch(DeleteEvent(e))
          }
        }).map(_ => NoAction)
      )


    case DeleteEvent(event) =>
      effectOnly(
        Effect(Http.deleteRelative(s"/api/events/${event.id}"))
      )


    case ToggleDebug =>
      updated(value.copy(debug = !value.debug), Effect.action(if (value.debug) ClearSourceMaps else InitSourceMaps))
  }
}