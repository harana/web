package com.harana.web.handlers.files.ui

import com.harana.sdk.shared.models.jwt.HaranaClaims
import com.harana.web.handlers.files.FilesStore._
import com.harana.web.components.elements.Dialog
import com.harana.web.components.sidebar.TextListItem
import com.harana.web.components.table.RowGroup
import com.harana.web.handlers.files.FilesStore
import com.harana.web.handlers.user.UserStore
import com.harana.web.pages.{FilterItem, PreviewPage, TablePage}
import com.harana.web.utils.SizeUtils
import com.harana.web.utils.i18nUtils.ops
import diode.Circuit
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.{Fragment, React, ReactElement}

import scala.scalajs.js

case class FilesPage(circuit: Circuit[_ <: AnyRef],
                     claims: HaranaClaims,
                     state: FilesStore.State,
                     userState: UserStore.State,
                     navigationBar: ReactElement) {

  @react object Component {

    private val dialogRef = React.createRef[Dialog.Def]

    val component = FunctionalComponent[js.Dynamic] { props =>
      val subtitle = if (state.item.nonEmpty && state.item.get.path != "") Some(state.item.get.name) else None

      Fragment(
        Dialog.withRef(dialogRef),
        state.item match {
          case Some(item) if !item.isFolder =>
            PreviewPage(
              circuit = circuit,
              file = item,
              path = state.path,
              title = i"heading.section.files",
              subtitle = subtitle,
              preview = state.itemPreview,
              navigationBar = Some(navigationBar),
              toolbarItems = List(toolbar.pathTree(circuit, state)),
              blocked = state.blocked,
              sidebarAboutItems = List(
                TextListItem(i"files.sidebar.updated", state.item.map(d => Left(d.updated))),
                TextListItem(i"files.sidebar.size", state.item.map(s => Right(SizeUtils.format(s.size)))),
                TextListItem(i"files.sidebar.owner", Some(Right(s"${props.claims.firstName} ${props.claims.lastName}"))),
                TextListItem("", None),
              ))

          case _ =>
            TablePage(
              circuit = circuit,
              title = i"heading.section.files",
              subtitle = subtitle,
              navigationBar = Some(navigationBar),
              toolbarItems = (
                if (state.path.nonEmpty)
                  List(toolbar.pathTree(circuit, state))
                else List()) ++ List(toolbar.edit(circuit, dialogRef, state), toolbar.create(circuit, dialogRef, state), toolbar.sort(circuit)),
              blocked = state.blocked,
              activeSearchQuery = state.searchQuery,
              activeTag = state.tag,
              tags = state.tags,
              columns = table.columns,
              rowGroups = List(RowGroup(None, table.rows(circuit, state))),
              sidebar = if (state.path.isEmpty) Some(sidebar.home(circuit, state, userState, dialogRef)) else None,
              sidebarAboutItems = List(
                TextListItem(i"files.sidebar.updated", state.item.map(d => Left(d.updated))),
                TextListItem(i"files.sidebar.size", state.item.map(s => Right(SizeUtils.format(s.size)))),
                TextListItem(i"files.sidebar.owner", Some(Right(s"${claims.firstName} ${claims.lastName}"))),
                //              TextListItem(i"files.sidebar.tags", s"")
              ),
              onSearchQueryChanged = (query: Option[String]) => circuit.dispatch(UpdateSearchQuery(query)),
              onTagChanged = (tag: Option[FilterItem]) => circuit.dispatch(UpdateTag(tag))
            )
        }
      )
    }
  }
}