package com.harana.web.handlers.help

import com.harana.web.actions.Init
import com.harana.sdk.shared.models.common.HelpCategory
import com.harana.web.handlers.help.HelpStore._
import com.harana.web.actions.Init
import com.harana.web.utils.http.Http
import diode.AnyAction._
import diode._
import org.scalajs.macrotaskexecutor.MacrotaskExecutor.Implicits._

class HelpHandler[S](state: ModelRW[S, HelpStore.State]) extends ActionHandler(state) {

  override def handle = {

    case Init(preferences) =>
      effectOnly(
        Effect(Http.getRelativeAs[List[HelpCategory]](s"/api/help").map { categories =>
          updated(value.copy(categories = categories.getOrElse(List())))
        })
      )
  }
}
