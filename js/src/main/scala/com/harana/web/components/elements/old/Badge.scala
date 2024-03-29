package com.harana.web.components.elements.old

import com.harana.web.components.elements.{Color, Link}
import com.harana.web.components._
import diode.Circuit
import enumeratum.values.{StringEnum, StringEnumEntry}
import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.web.html._

@react class Badge extends StatelessComponent {

	case class Props(circuit: Circuit[_],
									 title: String,
                   link: Option[LinkType] = None,
                   style: Option[BadgeStyle] = None,
                   color: Option[Color] = None)

	def render() = {
		val cls = cssSet(
			"badge" -> true,
			s"bg-${props.color.map(_.value).orNull}" -> props.color.nonEmpty,
			s"badge-${optEnum(props.style)}" -> props.style.nonEmpty
		)

		val badge = span(className := cls)

		props.link match {
			case Some(link) => Link(props.circuit, link, Some(cls))(List(badge))
			case None => badge
		}
	}
}

sealed abstract class BadgeStyle(val value: String) extends StringEnumEntry
case object BadgeStyle extends StringEnum[BadgeStyle] {
	case object Flat extends BadgeStyle("flat")
	val values = findValues
}