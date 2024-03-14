package com.harana.web.external.tailwind

import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.web.html._

@react class Heading extends StatelessComponent {

  case class Props(text: String,
                   className: Option[String] = None)

  def render() =
    h3(className := s"text-base font-semibold leading-6 text-gray-700 ${props.className.getOrElse("")}")(props.text)
}