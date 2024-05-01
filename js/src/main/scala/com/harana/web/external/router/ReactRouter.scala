package com.harana.web.external.react_router

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html.a
import org.scalajs.dom.History

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("react-router", JSImport.Default)
@js.native
object ReactRouter extends js.Object {
  val StaticRouter: js.Object = js.native
}

@JSImport("react-router-dom", "BrowserRouter")
@js.native
object ReactBrowserRouter extends js.Object
@react object BrowserRouter extends ExternalComponent {
  case class Props()
  override val component = ReactBrowserRouter
}

@JSImport("react-router-dom", "Routes")
@js.native
object ReactRoutes extends js.Object
@react object Routes extends ExternalComponent {
  case class Props()
  override val component = ReactRoutes
}

@JSImport("react-router-dom", "Route")
@js.native
object ReactRoute extends js.Object
@react object Route extends ExternalComponent {
  case class Props(path: String, component: ReactComponentClass[_], exact: Boolean = false)
  override val component = ReactRoute
}