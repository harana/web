package com.harana.web.handlers.data.list

object DataSourceListStore {

  case class AdditionalState(dataSourceTypes: Map[String, List[String]])

  val initialState = AdditionalState(Map())

}