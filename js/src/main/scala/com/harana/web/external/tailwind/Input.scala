package com.harana.web.external.tailwind

import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.web.html._

import scala.scalajs.js

@react class Input extends StatelessComponent {

  case class Props(name: String,
                   id: Option[String] = None,
                   placeholder: Option[String] = None,
                   onChange: Option[String => Unit] = None,
                   roundTop: Boolean = false,
                   roundTopLeft: Boolean = false,
                   roundTopRight: Boolean = false,
                   roundBottom: Boolean = false,
                   roundBottomLeft: Boolean = false,
                   roundBottomRight: Boolean = false)

  def render() = {

    var cls = ""
      if (props.roundTop) cls += " rounded-t-md"
      if (props.roundTopLeft) cls += " rounded-tl-md"
      if (props.roundTopRight) cls += " rounded-tr-md"
      if (props.roundBottom) cls += " rounded-b-md"
      if (props.roundBottomLeft) cls += " rounded-bl-md"
      if (props.roundBottomRight) cls += " rounded-br-md"

      input(
        `type` := "text",
        name := props.name,
        id := props.id,
        className := s"relative block w-full rounded-none $cls border-0 bg-transparent py-1.5 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:z-10 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6",
        placeholder := props.placeholder,
        onChange := (event => if (props.onChange.nonEmpty) props.onChange.get(event.target.asInstanceOf[js.Dynamic].value.toString))
      )
  }
}