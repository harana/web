package com.harana.web.components.elements

import com.harana.web.components.LinkType
import com.harana.web.external.shoelace.Menu
import diode.Circuit
import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.reactrouter.NavLink
import slinky.web.html._
import org.scalajs.dom.window

@react class Link extends StatelessComponent {

	case class Props(circuit: Circuit[_],
									 linkType: LinkType,
									 className: Option[String] = None,
									 activeClassName: Option[String] = None,
									 children: List[ReactElement])

	def render() =
		props.linkType match {

			case LinkType.Action(action) =>
				val fn: () => Unit = () => props.circuit.dispatch(action)
				a(onClick := fn)(props.children)

			case LinkType.HideDialog(ref) =>
				val fn: () => Unit = () => ref.current.hide()
				a(onClick := fn)(props.children)

			case LinkType.Menu(menu) =>
				Menu(menu)

			case LinkType.Page(name) => {
				val activeClassName = if (window.location.pathname.startsWith(s"/$name")) props.activeClassName else None
				NavLink(to = name, activeClassName = activeClassName)(className := props.className)(props.children)
			}

			case LinkType.OnClick(click) =>
				a(onClick := click, className := props.className)(props.children)

			case LinkType.ShowDialog(ref, title, style, values, width) =>
				val fn: () => Unit = () => ref.current.show(title, style, values, width)
				a(onClick := fn)(props.children)

			case LinkType.Url(url) =>
				a(href := url, className := props.className)(props.children)

		}
}