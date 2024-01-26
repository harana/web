package com.harana.web.handlers.user

import com.harana.web.actions.Init
import com.harana.sdk.shared.models.common.UserSettings
import com.harana.web.handlers.user.UserStore._
import com.harana.web.handlers.welcome.WelcomeStore
import com.harana.web.utils.http.Http
import diode._
import io.circe.syntax._
import org.scalajs.macrotaskexecutor.MacrotaskExecutor.Implicits._

class UserHandler[S](state: ModelRW[S, UserStore.State]) extends ActionHandler(state) {

  override def handle = {

    case Init(preferences) =>
      effectOnly(
        Effect(Http.getRelativeAs[Map[String, String]](s"/api/user/preferences").map(p => UpdatePreferences(p.getOrElse(Map())))) +
        Effect(Http.getRelativeAs[UserSettings](s"/api/user/settings").map(s => UpdateSettings(s)))
      )


    case SetPreference(id, updatedValue) =>
      val updatedPreferences = updatedValue match {
        case Some(v) => value.preferences + (id -> v)
        case None => value.preferences - id
      }

      val dirty = (value.preferences.get(id), updatedPreferences.get(id)) match {
        case (Some(existingValue), Some(newValue)) => existingValue != newValue
        case _ => true
      }

      updatedSilent(value.copy(preferences = updatedPreferences, preferencesDirty = dirty))


    case SavePreferences =>
      if (value.preferencesDirty)
        updatedSilent(value.copy(preferencesDirty = false), Effect(Http.postRelative(s"/api/user/preferences", body = value.preferences.asJson.noSpaces).map(_ => NoAction)))
      else
        noChange


    case SaveSettings =>
      if (value.settings.nonEmpty)
        effectOnly(Effect(Http.postRelative(s"/api/user/settings", body = value.settings.get.asJson.noSpaces).map(_ => NoAction)))
      else
        noChange

    case UpdatePreferences(preferences) =>
      updated(value.copy(preferences = preferences))


    case UpdateSettings(settings) =>
      updated(value.copy(settings = settings))
  }
}