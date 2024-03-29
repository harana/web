package com.harana.web.components.table

import com.harana.web.external.shoelace.Tag
import slinky.core.StatelessComponent
import slinky.core.annotations.react

@react class DateColumn extends StatelessComponent {

	case class Props(tags: Set[String])

	def render() =
		props.tags.toList.sorted.map(tag =>
			Tag(
				label = tag,
				pill = Some(true),
				size = Some("small")
			)
		)
}