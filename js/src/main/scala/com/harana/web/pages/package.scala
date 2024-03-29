package com.harana.web

import com.harana.web.components.sidebar._
import com.harana.web.utils.i18nUtils.ops
import enumeratum.{CirceEnum, Enum, EnumEntry}

package object pages {

  def searchSection(activeSearchQuery: Option[String], onSearchQueryChanged: Option[String] => Unit) =
    SidebarSection(
      Some(i"common.sidebar.search"),
      allowCollapse = false,
      allowClear = activeSearchQuery.nonEmpty,
      Some(_ => onSearchQueryChanged(None)),
      List(SearchSection(onSearch = (search: Option[String]) => onSearchQueryChanged(search), value = activeSearchQuery).withKey("search"))
    )


  case class FilterItem(title: String, count: Int, icon: Option[String])

  sealed trait ViewMode extends EnumEntry
  case object ViewMode extends Enum[ViewMode] with CirceEnum[ViewMode] {
    case object Grid extends ViewMode
    case object List extends ViewMode
    val values = findValues
  }

  def filterSection(title: String,
                    isPill: Boolean,
                    filterItems: List[FilterItem],
                    activeFilterItem: Option[FilterItem],
                    onFilterChanged: Option[FilterItem] => Unit) = {
    val navigationItems = filterItems.sortBy(_.title).map { fi =>
      val isActive = activeFilterItem.nonEmpty && activeFilterItem.get.equals(fi)
      NavigationItem(fi.title, onClick = () => onFilterChanged(Some(fi)), icon = fi.icon, rightText = Some(fi.count.toString), isActive = isActive, isPill = isPill)
    }

    SidebarSection(
      Some(title),
      allowCollapse = false,
      allowClear = activeFilterItem.nonEmpty,
      Some(_ => onFilterChanged(None)),
      List(NavigationSection(List(NavigationGroup(navigationItems)))),
      visible = Some(() => filterItems.nonEmpty)
    )
  }
}