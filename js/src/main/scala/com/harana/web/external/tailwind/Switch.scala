package com.harana.web.external.tailwind

import com.harana.web.components.when
import com.harana.web.external.tailwind.switch.{Switch => TwSwitch, SwitchGroup => TwSwitchGroup, SwitchLabel => TwSwitchLabel}
import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html._
import typings.std.global.console

import scala.scalajs.js

@react class Switch extends StatelessComponent {

  case class Props(name: String,
                   label: Option[ReactElement],
                   enabled: Boolean,
                   onChange: js.Any => Unit,
                   className: js.UndefOr[String] = js.undefined,
                   labelClassName: js.UndefOr[String] = js.undefined)

  def render() =
    TwSwitchGroup(as = "span", className = s"flex items-center ${props.className}")(
      TwSwitch(
        checked = props.enabled,
        name = props.name,
        onChange = props.onChange,
        className = s"${if (props.enabled) "bg-emerald-600" else "bg-gray-200"} relative inline-flex h-6 w-11 flex-shrink-0 cursor-pointer rounded-full border-2 border-transparent transition-colors duration-200 ease-in-out focus:outline-none focus:ring-2 focus:ring-emerald-600 focus:ring-offset-2",
        children = (value: js.Dynamic) => {
          val checked = value.checked.toString == "true"
          span(className := s"${if (props.enabled) "translate-x-5" else "translate-x-0"} pointer-events-none inline-block h-5 w-5 transform rounded-full bg-white shadow ring-0 transition duration-200 ease-in-out")
        }
      ),
      when(props.label.isDefined)(
        TwSwitchLabel(as = "span", className = s"ml-3 ${props.labelClassName.getOrElse("text-sm")}")(
          span(className := "font-medium text-gray-900")(props.label)
        )
      )
    )
}