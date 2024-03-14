package com.harana.web.handlers.welcome.ui

import com.harana.sdk.shared.models.jwt.HaranaClaims
import com.harana.web.components.LinkType
import com.harana.web.components.elements.{NavigationBarItem, Page}
import com.harana.web.external.shoelace.Icon
import com.harana.web.handlers.navigation.NavigationStore
import com.harana.web.handlers.navigation.ui.Navigation
import com.harana.web.handlers.welcome.WelcomeStore
import diode.Circuit
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.web.html._

@react object WelcomePage {

  case class Props(circuit: Circuit[_ <: AnyRef],
                   navigationItems: List[(NavigationBarItem, Option[LinkType.Page])],
                   claims: HaranaClaims,
                   authDomain: Option[String],
                   state: WelcomeStore.State,
                   navigationState: NavigationStore.State)

  val component = FunctionalComponent[Props] { props =>
    val title = "Welcome"

    Page(
      circuit = props.circuit,
      title = title,
      navigationBar = Some(Navigation(props.circuit, props.navigationItems, props.claims, props.navigationState, props.authDomain)),
      content = pageContent
    )
  }

  def pageContent = {
    div(className := "flow-container")(
      div(className := "panel panel-flat")(
        div(className := "table-responsive")(
          Icon(library = Some("icomoon"), name = "stack4", className = Some("welcome-icon"))
        )
      )
    )
  }
}