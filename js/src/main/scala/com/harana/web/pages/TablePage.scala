package com.harana.web.pages

import com.harana.sdk.shared.utils.Random
import com.harana.web.components.Ref
import com.harana.web.components.elements.{HeadingItem, Page}
import com.harana.web.components.sidebar._
import com.harana.web.components.table.{Column, GroupedTable, RowGroup}
import com.harana.web.external.shoelace.Menu
import com.harana.web.utils.i18nUtils.ops
import diode.Circuit
import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement

import scala.collection.mutable.ListBuffer


@react class TablePage extends StatelessComponent {

  case class Props(circuit: Circuit[_],
                   title: String,
                   subtitle: Option[String] = None,
                   subtitleMenu: Option[Menu.Props] = None,
                   navigationBar: Option[ReactElement] = None,
                   fixedNavigationBar: Boolean = true,
                   footerNavigationBar: Option[ReactElement] = None,
                   toolbarItems: List[HeadingItem] = List(),
                   blocked: Boolean = false,
                   activeSearchQuery: Option[String] = None,
                   activeTag: Option[FilterItem] = None,
                   tags: List[FilterItem] = List(),
                   columns: List[Column],
                   rowGroups: List[RowGroup],
                   sidebar: Option[Ref[Sidebar]] = None,
                   sidebarAboutItems: List[TextListItem],
                   onSearchQueryChanged: Option[String] => Unit,
                   onTagChanged: Option[FilterItem] => Unit)

  def sidebar = {
    val sections = ListBuffer(searchSection(props.activeSearchQuery, props.onSearchQueryChanged))
    if (props.tags.nonEmpty) sections += filterSection(i"common.sidebar.tags", true, props.tags, props.activeTag, props.onTagChanged)
    sections += SidebarSection(Some(i"files.sidebar.about"), allowCollapse = false, allowClear = false, None, TextListSection(props.sidebarAboutItems))
    Sidebar(sections.toList)
  }

  def table =
    GroupedTable(props.columns, props.rowGroups).withKey(Random.short)

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
      content = Some(table)
    )
}