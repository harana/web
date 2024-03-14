package com.harana.web.external.chakra

import scala.scalajs.js

trait Amount extends js.Object {
  val _base: String
  val _sm: js.UndefOr[String]
  val _md: js.UndefOr[String]
}

object Amount {
  def apply(base: String, sm: js.UndefOr[String] = js.undefined, md: js.UndefOr[String] = js.undefined) = new Amount {
    val _base = base
    val _sm = sm
    val _md = md
  }
}