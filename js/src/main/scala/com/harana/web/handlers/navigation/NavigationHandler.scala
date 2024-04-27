package com.harana.web.handlers.navigation

import com.harana.web.InitialState
import com.harana.web.actions.Init
import com.harana.web.base.Analytics
import com.harana.web.handlers.navigation.NavigationStore._
import com.harana.web.handlers.user.UserStore.SetPreference
import com.harana.web.utils.http.Http
import diode.AnyAction._
import diode._
import io.circe.optics.JsonPath.root
import org.scalajs.dom
import org.scalajs.macrotaskexecutor.MacrotaskExecutor.Implicits._
import sttp.model.Header
import typings.stripeV3.global.{Stripe_ => Stripe}
import typings.stripeV3.stripe.StripeServerCheckoutOptions

class NavigationHandler[S](state: ModelRW[S, NavigationStore.State], analytics: Analytics) extends ActionHandler(state) {

  private val routePreferenceId = "designer.route"

  override def handle = {

    case Init(preferences) =>
      effectOnly(Effect.action(
        preferences.get(routePreferenceId) match {
          case Some(r) => OpenRoute(r)
          case None => OpenRoute("/")
        }
      ))


    // FIXME
    case OpenCheckout =>
      //analytics.checkout()
      updated(value.copy(openingCheckout = true),
        Effect(Http.getAsJson(s"https://${InitialState.authDomain}/billing/checkout", List(Header("jwt", InitialState.initialJwt))).map { json =>
          if (json.nonEmpty) {
            val stripe = Stripe(root.publishableKey.string.getOption(json.get).get)
            val serverOptions = StripeServerCheckoutOptions(root.sessionId.string.getOption(json.get).get)
            stripe.redirectToCheckout(serverOptions)
          }
          updated(value.copy(openingCheckout = false))
        })
      )


    case OpenRoute(route) =>
      analytics.history.push(route)
      noChange


    case SaveRoute =>
      effectOnly(Effect.action(SetPreference(routePreferenceId, Some(dom.window.location.pathname))))


    case UpdateOpeningCheckout(opening) =>
        updated(value.copy(openingCheckout = opening))
  }
}