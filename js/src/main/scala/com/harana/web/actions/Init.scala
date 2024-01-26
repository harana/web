package com.harana.web.actions

import diode.Action

case class Init(userPreferences: Map[String, String]) extends Action