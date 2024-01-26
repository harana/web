package com.harana.web.base

import com.harana.sdk.shared.models.jwt.HaranaClaims
import com.harana.web.handlers.navigation.NavigationStore.SaveRoute
import com.harana.web.utils.AuthUtils
import com.harana.web.utils.error.Error
import com.harana.web.InitialState
import diode._
import org.scalajs.dom
import org.scalajs.dom.{Event, HashChangeEvent}
import slinky.core.facade.ReactElement
import slinky.web.{ReactDOM, ReactDOMClient}
import slinky.web.ReactDOMClient.createRoot

import java.util.Timer
import scala.scalajs.js

object BaseApp {
  var claims: HaranaClaims = _

  def main[S <: AnyRef](circuit: Circuit[S],
                        router: ReactElement,
                        auth: Boolean = true,
                        onWindowFocus: Option[() => Unit] = None,
                        onWindowBlur: Option[() => Unit] = None,
                        logActions: Boolean = true,
                        logActionBatches: Boolean = false,
                        logErrorsServerSide: Boolean = true) = {
    if (auth) {
      val decodedJwt = AuthUtils.decode(InitialState.initialJwt)
      if (decodedJwt.isEmpty) println("Failed to decode JWT") else {
        claims = decodedJwt.get
        app[S](circuit, router, onWindowFocus, onWindowBlur, logActions, logActionBatches, logErrorsServerSide)
      }
    }else
      app[S](circuit, router, onWindowFocus, onWindowBlur, logActions, logActionBatches, logErrorsServerSide)
  }

  def app[S <: AnyRef](circuit: Circuit[S], router: ReactElement, onWindowFocus: Option[() => Unit], onWindowBlur: Option[() => Unit], logActions: Boolean, logActionBatches: Boolean, logErrorsServerSide: Boolean) = {
    if (logErrorsServerSide) js.Dynamic.global.window.onerror = Error.fromJS

    circuit.addProcessor((dispatch: Dispatcher, action: Any, next: Any => ActionResult[S], currentModel: S) => {
      var within_batch = false

      val actions = action match {
        case a: ActionBatch =>
          if (logActionBatches) {
            within_batch = true
            a.actions
          }else List()

        case a: Action => List(a)
      }

      if (logActions) actions.foreach(a => println(s"${if (within_batch) "Batch:" else "Action:"} ${a.getClass.getName.replaceFirst("\\$", " -> ").replace("$", "")}"))
      next(action)
    })

//    Not supported by Tauri
//    if (LinkingInfo.developmentMode) hot.initialize()

    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    dom.window.onhashchange = (e: HashChangeEvent) => {
      val hash = dom.window.location.hash
      if (hash.nonEmpty) {}
    }

    if (onWindowFocus.nonEmpty) {
      dom.window.addEventListener("focus", (_: Event) => {
        onWindowFocus.get.apply()
      }, false)
    }

    if (onWindowBlur.nonEmpty) {
      dom.window.addEventListener("blur", (_: Event) => {
        onWindowBlur.get.apply()
      }, false)
    }

    ReactDOM.render(router, container)
    //createRoot(container).render(router)

    new Timer().scheduleAtFixedRate(new java.util.TimerTask {
      def run(): Unit = {
        //        Circuit.dispatch(RefreshEvents)
        //        circuit.dispatch(SaveRoute)
        //        Circuit.dispatch(SavePreferences)
      }
    }, 0L, 2000L)
  }
}

