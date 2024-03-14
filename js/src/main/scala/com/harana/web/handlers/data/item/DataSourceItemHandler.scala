package com.harana.web.handlers.data.item

import DataSourceItemStore._
import com.harana.web.actions.Init
import com.harana.sdk.shared.models.data.{DataSource, DataSourceType}
import com.harana.web.utils.http.Http
import diode._
import org.scalajs.macrotaskexecutor.MacrotaskExecutor.Implicits._

class DataSourceItemHandler[S](state: ModelRW[S, DataSourceItemStore.State]) extends ActionHandler(state) {

  override def handle = {

    case Init(preferences) =>
      noChange


    case OpenDataSource(id) =>
      effectOnly(
        Effect(Http.getRelativeAs[DataSource](s"/api/datasources/$id").map(ds => UpdateDataSource(ds))) >>
        Effect(Http.getRelativeAs[DataSourceType](s"/api/datasources/type/$id").map(dst => UpdateDataSourceType(dst)))
      )


    case UpdateDataSource(dataSource) =>
      updated(value.copy(dataSource = dataSource))


    case UpdateDataSourceType(dataSourceType) =>
      updated(value.copy(dataSourceType = dataSourceType))

  }
}