package com.harana.web.external.tailwind

import com.harana.web.components.when
import slinky.web.html.{className, span, style}
import slinky.web.svg.{circle, cx, cy, r, svg, viewBox, className => svgClassName}

import scala.scalajs.js

object Badge {

  def gray(label: String, dot: Boolean = false, additionalClassName: Option[String] = None) =
    span(
      className := s"inline-flex items-center ${if (dot) "gap-x-1.5" else ""} rounded-md bg-gray-50 px-1.5 py-0.5 text-xs font-medium text-gray-600 ${additionalClassName.getOrElse("")}",
      style := js.Dynamic.literal("marginBottom" -> "3px")
    )(
      when(dot)(
        svg(svgClassName := s"h-1.5 w-1.5 fill-gray-400", viewBox := "0 0 6 6")(
          circle(cx := "3", cy := "3", r := "3")
        )
      ),label
    )

  def green(label: String, dot: Boolean = false, additionalClassName: Option[String] = None) =
    span(
      className := s"inline-flex items-center gap-x-1.5 rounded-md bg-green-50 px-1.5 py-0.5 text-xs font-medium text-green-700 ${additionalClassName.getOrElse("")}",
      style := js.Dynamic.literal("marginBottom" -> "3px")
    )(
      when(dot)(
        svg(svgClassName := s"h-1.5 w-1.5 fill-green-500", viewBox := "0 0 6 6")(
          circle(cx := "3", cy := "3", r := "3")
        )
      ), label
    )

  def red(label: String, dot: Boolean = false, additionalClassName: Option[String] = None) =
    span(
      className := s"inline-flex ite  ms-center gap-x-1.5 rounded-md bg-red-50 px-1.5 py-0.5 text-xs font-medium text-red-700 ${additionalClassName.getOrElse("")}",
      style := js.Dynamic.literal("marginBottom" -> "3px")
    )(
      when(dot)(
        svg(svgClassName := s"h-1.5 w-1.5 fill-red-500", viewBox := "0 0 6 6")(
          circle(cx := "3", cy := "3", r := "3")
        )
      ), label
    )

  def yellow(label: String, dot: Boolean = false, additionalClassName: Option[String] = None) =
    span(
      className := s"inline-flex items-center gap-x-1.5 rounded-md bg-yellow-50 px-1.5 py-0.5 text-xs font-medium text-yellow-700 ${additionalClassName.getOrElse("")}",
      style := js.Dynamic.literal("marginBottom" -> "3px")
    )(
      when(dot)(
        svg(svgClassName := s"h-1.5 w-1.5 fill-yellow-500", viewBox := "0 0 6 6")(
          circle(cx := "3", cy := "3", r := "3")
        )
      ), label
    )
}