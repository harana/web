package com.harana.web.external.tailwind

import com.harana.web.components.when
import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html.{scope, _}

@react class Table extends StatelessComponent {

  case class Props(name: String,
                   headers: List[String],
                   rows: List[List[ReactElement]],
                   noRowsMessage: Option[String] = None,
                   actionColumn: Boolean = false,
                   showHeader: Boolean = false,
                   className: String = "mt-4",
                   overflow: Boolean = true)

  def render() = {
    div(className := props.className)(
      div(className := s"-mx-4 -my-2 ${if (props.overflow) "overflow-x-auto " else ""} sm:-mx-6 lg:-mx-8")(
        div(className := "inline-block min-w-full py-2 align-middle sm:px-6 lg:px-8")(
          div(className := "overflow-hidden shadow ring-1 ring-black ring-opacity-5 sm:rounded-lg")(
            table(className := "min-w-full divide-y divide-gray-300")(
              when(props.showHeader)(
                thead(className := "bg-gray-50")(
                  tr(
                    props.headers.zipWithIndex.map { case (header, index) =>
                      if (index == 0)
                        th(scope := "col", className := s"py-3.5 pl-4 pr-3 text-left text-sm font-semibold text-gray-900 sm:pl-6")(
                          header
                        )
                      else
                        th(scope := "col", className := s"px-3 py-3.5 text-left text-sm font-semibold text-gray-900")(
                          header
                        )
                    },
                    when(props.actionColumn)(
                      th(scope := "col", className := "relative py-3.5 pl-3 pr-4 sm:pr-6")(
                        span(className := "sr-only")
                      )
                    )
                  )
                )
              ),
              tbody(className := "divide-y divide-gray-200 bg-white")(
                if (props.rows.isEmpty && props.noRowsMessage.nonEmpty)
                  tr(
                    td(colSpan := props.headers.size)(
                      div(className := "text-sm center font-medium text-gray-900")(props.noRowsMessage)
                    )
                  )
                else
                  props.rows.zipWithIndex.map { case (row, rowIndex) =>
                    val padding = rowIndex match {
                      case i if i == 0 && !props.showHeader => "pt-2"
                      case i if i == props.rows.size - 1 => "pb-2"
                      case _ => ""
                    }
                    tr(key := s"${props.name}-$rowIndex")(
                      props.headers.indices.map { colIndex =>
                        row.lift(colIndex) match {
                          case None => td(div())
                          case Some(r) => td(div(className := padding)(r))
                        }
                      },
                      when(props.actionColumn)(
                        row.lastOption match {
                          case None => td(div())
                          case Some(r) => td(div(className := s"right pr-4 $padding")(td(r)))
                        }
                      )
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