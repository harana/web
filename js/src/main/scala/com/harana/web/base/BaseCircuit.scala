package com.harana.web.base

import com.harana.web.base.macros.State
import diode._
import slinky.core.facade.{React, ReactContext}

abstract class BaseCircuit[S <: AnyRef] extends diode.Circuit[S] with State[S] {

  def handlers: List[(S, Any) => Option[ActionResult[S]]]
  val initialModel: S

  lazy val diodeContext: ReactContext[Circuit[S]] =
    React.createContext[Circuit[S]](this)

  override lazy val actionHandler =
    foldHandlers(handlers: _*)

  override def handleFatal(action: Any, e: Throwable): Unit = {
    println(s"Action: $action failed due to error: ${e.getMessage}")
    throw e
  }
}