package com.harana.web.handlers.help.ui

import com.harana.sdk.shared.models.jwt.HaranaClaims
import com.harana.web.components.LinkType
import com.harana.web.handlers.help.HelpStore.State
import com.harana.web.components.elements.{NavigationBarItem, Page}
import com.harana.web.components.sidebar.{NavigationGroup, NavigationItem, NavigationSection, Sidebar, SidebarSection}
import com.harana.web.handlers.help.HelpStore
import com.harana.web.handlers.navigation.NavigationStore
import com.harana.web.handlers.navigation.ui.Navigation
import diode.Circuit
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.web.html._

@react object HelpPage {

  case class Props(circuit: Circuit[_ <: AnyRef],
                   navigationItems: List[(NavigationBarItem, Option[LinkType.Page])],
                   claims: HaranaClaims,
                   authDomain: Option[String],
                   state: HelpStore.State,
                   navigationState: NavigationStore.State)

  val component = FunctionalComponent[Props] { props =>
    val title = "Help Guide"

    Page(
      circuit = props.circuit,
      title = title,
      navigationBar = Some(Navigation(props.circuit, props.navigationItems, props.claims, props.navigationState, props.authDomain)),
      content = pageContent(props.state),
      leftSidebar = Some(sidebar(props.state))
    )
  }


  def sidebar(state: State) = {
    val categories = state.categories.map { category =>
      val pages = category.pages.map { page =>
        NavigationItem(title = page.name, onClick = () => ())
      }
      NavigationGroup(pages, Some(category.name))
    }
    Sidebar(List(SidebarSection(Some("Categories"), content = NavigationSection(categories))))
  }


  def pageContent(state: State) = {
    div(className := "flow-container")(
      div(className := "panel panel-flat")(
        div(className := "table-responsive")(
        )
      )
    )
  }
}
