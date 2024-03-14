package com.harana.web.handlers.navigation

import diode.{Action => DiodeAction}

object NavigationStore {

  case class State(openingCheckout: Boolean)

  val initialState = State(false)


  case object OpenCheckout extends DiodeAction
  case class UpdateOpeningCheckout(opening: Boolean) extends DiodeAction

  case class ReceiveEvent(eventType: String, eventParameters: Map[String, String]) extends DiodeAction
  case class OpenRoute(route: String) extends DiodeAction
  case object SaveRoute extends DiodeAction
}