package com.harana.web.external.chakra

import com.harana.web.external.chakra.stack.HStack
import slinky.core.FunctionalComponent
import slinky.core.annotations.react

@react object NavButton {

  case class Props(icon: String, label: String)

  val component = FunctionalComponent[Props] { props =>
    Button(variant = "tertiary", justifyContent = "start")(
      HStack(spacing = "3")(
//        Icon(as = props.icon, boxSize = "6", color = "fg.subtle"),
        Text(fontWeight = "medium")(props.label)
      )
    )
  }
}