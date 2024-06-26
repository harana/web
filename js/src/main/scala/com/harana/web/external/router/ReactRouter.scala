package com.harana.web.external.router

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html.a
import org.scalajs.dom.History

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("react-router", JSImport.Namespace)
@js.native
object ReactRouter extends js.Object {
  val StaticRouter: js.Object = js.native
}

@JSImport("react-router-dom", JSImport.Namespace)
@js.native
object ReactRouterDOM extends js.Object {
  val Router: js.Object = js.native
  val BrowserRouter: js.Object = js.native
  val MemoryRouter: js.Object = js.native
  val Route: js.Object = js.native
  val Routes : js.Object = js.native
  val Switch : js.Object = js.native
  val Link: js.Object = js.native
  val NavLink: js.Object = js.native
}


@react object MemoryRouter extends ExternalComponent {
  case class Props()
  override val component = ReactRouterDOM.MemoryRouter
}

@react object StaticRouter extends ExternalComponent {
  case class Props(location: String, context: js.Object)
  override val component = ReactRouter.StaticRouter
}

@react object Router extends ExternalComponent {
  case class Props(history: History)
  override val component = ReactRouterDOM.Router
}

object BrowserRouter extends ExternalComponentNoProps {
  override val component = ReactRouterDOM.BrowserRouter
}

object Routes extends ExternalComponentNoProps {
  override val component = ReactRouterDOM.Routes
}

object Switch extends ExternalComponentNoProps {
  override val component = ReactRouterDOM.Switch
}

@react object Route extends ExternalComponent {
  case class Props(path: String, component: ReactComponentClass[_], exact: Boolean = false)
  override val component = ReactRouterDOM.Route
}

@react object Link extends ExternalComponentWithAttributes[a.tag.type] {
  case class Props(to: String)
  override val component = ReactRouterDOM.Link
}

@react object NavLink extends ExternalComponentWithAttributes[a.tag.type] {
  case class Props(to: String, activeStyle: Option[js.Dynamic] = None, activeClassName: Option[String] = None)
  override val component = ReactRouterDOM.NavLink
}