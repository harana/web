package com.harana.web.components.lists

import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.web.html._

@react class CommentsList extends StatelessComponent {

	case class Props(title: String,
									 icon: Option[String] = None,
									 comments: List[String] = List.empty)

	def render() =
		p("SelectElement")

}