package com.harana.web.base.macros

import scala.language.experimental.macros

trait State[S] {

  def state[T](field: S => T, subscribe: Boolean): T = macro GenStateLens.generate[S, S, T]

}