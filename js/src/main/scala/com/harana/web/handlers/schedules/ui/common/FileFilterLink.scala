package com.harana.web.handlers.schedules.ui.common

import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.web.html.div

@react object FileFilterLink {

  case class Props()

  val component = FunctionalComponent[Unit] { _ =>
    div()
  }
}