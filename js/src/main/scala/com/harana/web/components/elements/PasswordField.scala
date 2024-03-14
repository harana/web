package com.harana.web.components.elements

import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.web.html._

@react class PasswordField extends StatelessComponent {

	case class Props(name: String)

	def render() =
		p("SelectElement")

}