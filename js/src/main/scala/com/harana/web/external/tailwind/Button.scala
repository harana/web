package com.harana.web.external.tailwind

import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html._
import com.harana.web.components._

sealed trait ButtonSize

object ButtonSize {
  case object ExtraSmall extends ButtonSize
  case object Small extends ButtonSize
  case object Medium extends ButtonSize
  case object Large extends ButtonSize
  case object ExtraLarge extends ButtonSize
}


@react class Button extends StatelessComponent {

  case class Props(label: Option[String] = None,
                   icon: Option[ReactElement] = None,
                   size: ButtonSize,
                   className: Option[String] = None,
                   backgroundColor: String = "white",
                   hoverColor: String = "bg-gray-50",
                   textColor: String = "text-gray-900",
                   rounded: Boolean = false,
                   onClick: () => Unit)

  def render() = {
    val sizeClasses = props.size match {
      case ButtonSize.ExtraSmall => "px-2 py-1 text-xs"
      case ButtonSize.Small => "px-4 py-1 text-sm"
      case ButtonSize.Medium => "px-6 py-1.5 text-sm"
      case ButtonSize.Large => "px-8 py-2 text-sm"
      case ButtonSize.ExtraLarge => "px-12 py-2.5 text-sm"
    }

    button(
      `type` := "button",
      onClick := props.onClick,
      className := s"" +
        s"$sizeClasses " +
        s"${if (props.rounded) "rounded-full " else ""}" +
        s"${props.backgroundColor} " +
        s"hover:${props.hoverColor} " +
        s"${props.textColor} " +
        s"font-semibold shadow-sm ring-1 ring-inset"
    )(
      when(props.icon.isDefined)(props.icon.get),
      when(props.label.isDefined)(props.label.get)
    )
  }
}