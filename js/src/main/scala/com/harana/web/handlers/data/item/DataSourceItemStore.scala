package com.harana.web.handlers.data.item

import com.harana.sdk.shared.models.data.{DataSource, DataSourceType}
import com.harana.sdk.shared.models.data.DataSource.DataSourceId
import diode.{Action => DiodeAction}

object DataSourceItemStore {

  case class State(dataSource: Option[DataSource],
                   dataSourceType: Option[DataSourceType])

  val initialState = State(None, None)


  case class OpenDataSource(dataSourceId: DataSourceId) extends DiodeAction
  case class UpdateDataSource(dataSource: Option[DataSource]) extends DiodeAction
  case class UpdateDataSourceType(dataSourceType: Option[DataSourceType]) extends DiodeAction
}