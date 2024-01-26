package com.harana.web.utils

import com.harana.sdk.shared.models.jwt.HaranaClaims
import scala.scalajs.js

object AuthUtils {

  def decode(jwt: String): Option[HaranaClaims] =
    try {
      val json = js.Dynamic.global.window.atob(jwt.split('.')(1)).toString
      io.circe.parser.decode[HaranaClaims](json).toOption
    } catch {
      case e: Exception => None
    }
}