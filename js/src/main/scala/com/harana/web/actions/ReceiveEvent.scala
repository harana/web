package com.harana.web.actions

import diode.Action

case class ReceiveEvent(message: String, payload: String) extends Action
