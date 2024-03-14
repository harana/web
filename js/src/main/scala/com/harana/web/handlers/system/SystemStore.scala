package com.harana.web.handlers.system

import com.harana.sdk.shared.models.common.Event
import com.harana.web.InitialState
import diode.{Action => DiodeAction}

object SystemStore {

  case class State(debug: Boolean,
                   events: List[Event])

  val initialState = State(InitialState.debug, List())

  case object InitSourceMaps extends DiodeAction
  case object ClearSourceMaps extends DiodeAction

  case object RefreshEvents extends DiodeAction
  case class DeleteEvent(events: Event) extends DiodeAction
  case class UpdateEvents(events: List[Event]) extends DiodeAction

  case object EventBusConnected extends DiodeAction
  case object EventBusDisconnected extends DiodeAction

  case object ToggleDebug extends DiodeAction

}