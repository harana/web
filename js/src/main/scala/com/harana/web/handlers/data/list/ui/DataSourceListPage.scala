package com.harana.web.handlers.data.list.ui

import com.harana.sdk.shared.models.data.DataSource
import com.harana.sdk.shared.models.jwt.HaranaClaims
import com.harana.web.components.ColumnSize
import com.harana.web.components.Device.Desktop
import com.harana.web.components.table.{Column, HistoryColumn, TagsColumn}
import com.harana.web.handlers.data.list.DataSourceListStore
import com.harana.web.pages.grid.GridStore
import com.harana.web.pages.grid.ui.{GridPage, GridPageItem}
import com.harana.web.utils.i18nUtils.ops
import diode.Circuit
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html.div

case class DataSourceListPage(circuit: Circuit[_ <: AnyRef],
                              claims: HaranaClaims,
                              state: GridStore.GridState[DataSource, DataSourceListStore.AdditionalState],
                              navigationBar: ReactElement) {

  @react object Component {

    val titleColumn = Column(Some(i"datasources.common.title"), Map(Desktop -> ColumnSize.Three))
    val tagsColumn = Column(Some(i"datasources.common.tags"), Map(Desktop -> ColumnSize.Three))
    val typeColumn = Column(Some(i"datasources.common.type"), Map(Desktop -> ColumnSize.Two))
    val historyColumn = Column(Some(i"datasources.common.history"), Map(Desktop -> ColumnSize.Four))

    val component = FunctionalComponent[Unit] { props =>
      GridPage(
        circuit = circuit,
        entityType = "datasources",
        userId = claims.userId,
        state = state,
        title = i"heading.section.data",
        navigationBar = Some(navigationBar),
        tableColumns = List(titleColumn, tagsColumn, typeColumn, historyColumn),
        tableContent = (column: Column, item: GridPageItem[_]) => column match {
          case `titleColumn` =>
            div(item.title)

          case `tagsColumn` =>
            TagsColumn(Set("one", "two"))

          case `typeColumn` =>
            val parts = item.additionalData.getOrElse("type", "").asInstanceOf[String].toLowerCase.split("-")
            if (parts.nonEmpty) div(i"datasources.section.${parts(0)}.${parts(1)}.title") else div()

          case `historyColumn` =>
            HistoryColumn(values = List((1, "value"), (2, "value")), columns = 2)
        },
        editWidth = Some("400px")
      )
    }
  }
}