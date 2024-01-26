package com.harana.web.external.tailwind

import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.web.html._

import scala.scalajs.js

@react class Select extends StatelessComponent {

  case class Props(name: String,
                   onChange: String => Unit,
                   options: List[String])

  def render() =
    select(
      name := props.name,
      onChange := (event => props.onChange(event.target.asInstanceOf[js.Dynamic].value.toString)),
      className := "relative block w-full rounded-none rounded-t-md border-0 bg-transparent py-1.5 text-gray-900 ring-1 ring-inset ring-gray-300 focus:z-10 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
    )(props.options.map(o => option(o)))
}