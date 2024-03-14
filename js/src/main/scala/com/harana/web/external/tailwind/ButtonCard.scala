package com.harana.web.external.tailwind

import com.harana.web.components.when
import com.harana.web.external.tailwind.SolidIcons._
import com.harana.web.external.tailwind._
import slinky.core.{FunctionalComponent, StatelessComponent}
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html._
import slinky.web.svg.{clipRule, d, fill, fillRule, path, rect, rx, svg, viewBox, className => svgClassName, height => svgHeight, width => svgWidth}

import scala.scalajs.js

@react class ButtonCard extends StatelessComponent {

  case class Props(content: ReactElement,
                   firstText: String,
                   firstIcon: Option[ReactElement] = None,
                   firstOnClick: () => Unit,
                   secondButton: Boolean = false,
                   secondText: Option[String] = None,
                   secondIcon: Option[ReactElement] = None,
                   secondOnClick: Option[() => Unit] = None,
                   className: Option[String] = None,
                   contentClassName: Option[String] = None,
                   contentPadding: Option[String] = None)

  def render() =
    div(className := s"${props.className.getOrElse("")} flex flex-col divide-y divide-gray-200 rounded-lg bg-white text-center shadow")(
      div(className := s"${props.contentPadding.getOrElse("p-8")} flex flex-1 flex-col")(
        props.content
      ),
      div(
        div(className := "-mt-px flex divide-x divide-gray-200")(
          div(className := "flex w-0 flex-1")(
            a(onClick := props.firstOnClick, className := "relative -mr-px inline-flex w-0 flex-1 items-center justify-center gap-x-3 rounded-bl-lg border border-transparent py-2 text-sm font-semibold text-gray-900")(
              when(props.firstIcon),
              props.firstText
            )
          ),
          when(props.secondButton)(
            div(className := "-ml-px flex w-0 flex-1")(
              a(onClick := props.secondOnClick.get, className := "relative inline-flex w-0 flex-1 items-center justify-center gap-x-3 rounded-br-lg border border-transparent py-2 text-sm font-semibold text-gray-900")(
                when(props.secondIcon),
                when(props.secondText)
              )
            )
          )
        )
      )
    )
}