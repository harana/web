package com.harana.web.external.tailwind

import com.harana.web.components.when
import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.core.facade.{Fragment, ReactElement}
import com.harana.web.external.tailwind.menu.{Menu => TwMenu, MenuButton => TwMenuButton, MenuItem => TwMenuItem, MenuItems => TwMenuItems}
import com.harana.web.external.tailwind.transition.Transition
import slinky.web.html._

import scala.scalajs.js

@react class Menu extends StatelessComponent {

  case class Props(items: List[List[(String, () => Unit, Option[ReactElement])]],
                   icon: ReactElement,
                   highlightIcon: Boolean = false,
                   className: Option[String] = None)

  def render() = {
    val buttonClass = if (props.highlightIcon)
      "flex items-center rounded-full bg-gray-100 text-gray-400 hover:text-gray-600 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 focus:ring-offset-gray-100"
    else
      "flex items-center"

    TwMenu(as = "div", className = "relative inline-block text-left")(
      div(
        TwMenuButton(className = buttonClass)(
          props.icon
        )
      ),

      Transition(
        as = Fragment.component,
        enter = "transition ease-out duration-100",
        enterFrom = "transform opacity-0 scale-95",
        enterTo = "transform opacity-100 scale-100",
        leave = "transition ease-in duration-75",
        leaveFrom = "transform opacity-100 scale-100",
        leaveTo = "transform opacity-0 scale-95")(

        TwMenuItems(
          className = s"${props.className.getOrElse("")} absolute right-0 z-10 mt-2 origin-top-right rounded-md bg-white shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none"
        )(
          props.items.map { group =>
            div(className := "py-1")(
              group.map { item =>
                TwMenuItem(children = (renderProps: js.Dynamic ) => {
                  val active = renderProps.active.toString == "true"
                  a(
                    onClick := item._2,
                    className := s"block px-4 py-2 text-sm ${if (active) "bg-gray-100 text-gray-900" else "text-gray-700"}"
                  )(
                    when(item._3), item._1
                  )
                })
              }
            )
          }
        )
      )
    )
  }
}