package com.harana.web.components.sidebar

import com.harana.web.components._
import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html._

@react class ContentSection extends StatelessComponent with SidebarSectionComponent {

	case class Props(content: ReactElement, padding: Boolean = true)

	def render() =
		div(className := cssSet("category-content" -> props.padding))(
			props.content
		)
}