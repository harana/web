package com.harana.web.external.tailwind

import com.harana.web.components.when
import com.harana.web.external.tailwind.SolidIcons.{CheckIcon, ChevronUpDownIcon}
import com.harana.web.external.tailwind.listbox.{Listbox => TwListbox, ListboxButton => TwListboxButton, ListboxLabel => TwListboxLabel, ListboxOption => TwListboxOption, ListboxOptions => TwListboxOptions}
import com.harana.web.external.tailwind.transition.Transition
import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.core.facade.Fragment
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.|

@react class Listbox extends StatelessComponent {

  case class Props(name: String,
                   items: List[js.Any | String | Int],
                   selectedItem: js.Any | String | Int,
                   itemId: js.Any | String | Int => String,
                   itemTitle: js.Any | String | Int => String,
                   label: Option[String],
                   onChange: js.Any => Unit,
                   className: js.UndefOr[String] = js.undefined)

  def render() = {
    div(className := props.className.toOption)(
      TwListbox(value = props.selectedItem, onChange = props.onChange, children = (listboxValue: js.Dynamic) =>
        Fragment(
          TwListboxLabel(className = "block text-sm font-medium leading-6 text-gray-900")(props.label),
          div(className := "relative")(
            TwListboxButton(className = "relative w-full cursor-default rounded-md bg-white py-1.5 pl-3 pr-10 text-left text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:outline-none focus:ring-2 focus:ring-emerald-600 sm:text-sm sm:leading-6")(
              span(className := "block truncate")(props.itemTitle(props.selectedItem)),
              span(className := "pointer-events-none absolute inset-y-0 right-0 flex items-center pr-2")(
                ChevronUpDownIcon(Some("h-5 w-5 text-gray-400"))
              )
            ),
            Transition(
              show = listboxValue.open.toString == "true",
              as = Fragment.component,
              leave = "transition ease-in duration-100",
              leaveFrom = "opacity-100",
              leaveTo = "opacity-0"
            )(
              TwListboxOptions(className = "absolute z-10 mt-1 max-h-60 w-full overflow-auto rounded-md bg-white py-1 text-base shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none sm:text-sm")(
                props.items.map { item =>
                  TwListboxOption(
                    key = props.itemId(item),
                    className = (value: js.Dynamic) => {
                      val active = value.active.toString == "true"
                      s"${if (active) "bg-emerald-600 text-white" else "text-gray-900"} relative cursor-default select-none py-2 pl-3 pr-9"
                    },
                    value = item,
                    children = (optionValue: js.Dynamic) => {
                      val selected = optionValue.selected.toString == "true"
                      val active = optionValue.active.toString == "true"

                      Fragment(
                        span(className := s"${if (selected) "font-semibold" else "font-normal"} block truncate")(props.itemTitle(item)),
                        when(selected)(
                          span(className := s"${if (active) "text-white" else "text-emerald-600"} absolute inset-y-0 right-0 flex items-center pr-4")(
                            CheckIcon("h-5 w-5")
                          )
                        )
                      )
                    }
                  )
                }
              )
            )
          )
        )
      )
    )
  }
}