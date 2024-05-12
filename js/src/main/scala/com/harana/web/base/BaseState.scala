package com.harana.web.base

import com.harana.web.handlers.system.SystemStore

trait BaseState {

  val systemState: SystemStore.State

}
