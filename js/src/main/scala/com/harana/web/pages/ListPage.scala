package com.harana.web.pages

import com.harana.web.components.Ref
import com.harana.web.components.elements.{HeadingItem, Page}
import com.harana.web.components.sidebar._
import com.harana.web.external.shoelace.Menu
import com.harana.web.utils.i18nUtils.ops
import diode.Circuit
import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.core.facade.{Fragment, ReactElement}
import slinky.web.html.{className, div, h1, onClick}
import com.harana.web.external.virtuoso.{Style, Virtuoso}

import scala.collection.mutable.ListBuffer
import scala.scalajs.js

@react class ListPage extends StatelessComponent {

  case class Props(circuit: Circuit[_],
                   title: String,
                   subtitle: Option[String] = None,
                   subtitleMenu: Option[Menu.Props] = None,
                   navigationBar: Option[ReactElement] = None,
                   fixedNavigationBar: Boolean = true,
                   footerNavigationBar: Option[ReactElement] = None,
                   fullSizedContent: Boolean = false,
                   toolbarItems: List[HeadingItem] = List(),
                   listItems: List[js.Object],
                   listItemHeight: Int,
                   listItemContent: Int => ReactElement,
                   mainContent: Option[ReactElement],
                   blocked: Boolean = false,
                   activeSearchQuery: Option[String] = None,
                   activeTag: Option[FilterItem] = None,
                   tags: List[FilterItem] = List(),
                   additionalSidebarSections: List[SidebarSection] = List(),
                   sidebar: Option[Ref[Sidebar]] = None,
                   onListItemSelected: Int => Unit,
                   onMoreItemsRequested: Int => Unit,
                   onSearchQueryChanged: Option[String] => Unit,
                   onTagChanged: Option[FilterItem] => Unit)

  def sidebar = {
    val sections = ListBuffer(searchSection(props.activeSearchQuery, props.onSearchQueryChanged))
    if (props.tags.nonEmpty) sections += filterSection(i"common.sidebar.tags", true, props.tags, props.activeTag, props.onTagChanged)
    sections ++= props.additionalSidebarSections
    Sidebar(sections.toList)
  }

  def render() =
    Page(
      circuit = props.circuit,
      title = props.title,
      subtitle = props.subtitle,
      subtitleMenu = props.subtitleMenu,
      navigationBar = props.navigationBar,
      fixedNavigationBar = props.fixedNavigationBar,
      footerNavigationBar = props.footerNavigationBar,
      toolbarItems = props.toolbarItems,
      blocked = props.blocked,
      noScrollingContent = true,
      leftSidebar = if (props.sidebar.nonEmpty) props.sidebar else Some(sidebar),
      content = Some(
        Fragment(
          div(className := "panel panel-flat panel-full-inner-screen panel-list")(
            div(className := "panel-body")(
              Virtuoso(
                data = props.listItems,
                endReached = props.onMoreItemsRequested,
                itemContent = (index: Int) =>
                  div(className := "panel-list-item", onClick := (_ => props.onListItemSelected(index)))(
                    props.listItemContent(index)
                  ),
                style = new Style {
                  override val height: String = s"calc(100vh - 200px)"
                }
              )
            )
          ),
          div(className := "panel panel-flat panel-full-inner-screen panel-list-content")(
            div(className := "panel-body")(
              props.mainContent.getOrElse(div())
            )
          )
        )
      )
    )
}