package com.harana.web.components.widgets

import com.harana.web.components.Value
import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.web.html._

@react class PieChartWidget extends StatelessComponent {

	case class Props(title: String,
									 subtitle: String,
									 values: List[Value],
									 relatedValues: List[Value] = List())

	def render() =
		p("SelectElement")

}