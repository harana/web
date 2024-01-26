package com.harana.web.handlers.data.item.ui

import com.harana.web.handlers.data.item.DataSourceItemStore._
import com.harana.web.components.elements.Page
import com.harana.web.components.sidebar._
import com.harana.web.handlers.data.item.DataSourceItemStore
import com.harana.web.utils.DateUtils
import com.harana.web.utils.i18nUtils.ops
import diode.Circuit
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.Hooks.useEffect
import slinky.core.facade.ReactElement
import slinky.web.html._

import java.util.concurrent.atomic.AtomicReference
import scala.scalajs.js

case class DataSourceItemPage(circuit: Circuit[_ <: AnyRef],
                              state: DataSourceItemStore.State,
                              navigationBar: ReactElement) {

  @react object Component {

    val currentDataSource = new AtomicReference[String]("")

    val component = FunctionalComponent[js.Dynamic] { props =>

      useEffect(() => {
        val dataSourceId = props.selectDynamic("match").params.selectDynamic("id").toString
        if (!currentDataSource.get.equals(dataSourceId)) {
          circuit.dispatch(OpenDataSource(dataSourceId))
          currentDataSource.set(dataSourceId)
        }
      })

      Page(
        circuit = circuit,
        title = i"heading.section.data",
        subtitle = state.dataSource.map(_.title),
        navigationBar = Some(navigationBar),
        leftSidebar = Some(
          Sidebar(
            List(SidebarSection(Some(i"datasources.sidebar.about"), allowCollapse = false, allowClear = false, None,
              TextListSection(List(
                TextListItem(i"datasources.sidebar.updated", state.dataSource.map(d => Left(d.updated))),
                TextListItem(i"datasources.sidebar.type", state.dataSourceType.map(dst => Right(i"datasources.${dst.syncDirection.value}.${dst.name}.title")))
              ))
            ))
          )
        ),
        content =
          div(className := "panel panel-flat")(
            div(className := "panel-body panel-fullscreen panel-centered")("Preview not available for this data source")
          )
      )
    }
  }
}