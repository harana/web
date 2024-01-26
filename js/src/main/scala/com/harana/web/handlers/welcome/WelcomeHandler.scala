package com.harana.web.handlers.welcome

import diode._

class WelcomeHandler[S](state: ModelRW[S, WelcomeStore.State]) extends ActionHandler(state) {

  override def handle = {

    case _ =>
      noChange

  }
}