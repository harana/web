package com.harana.web.external.html_to_image

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal
import scala.scalajs.js.typedarray.{Uint8Array}

object Encoder {
  @JSGlobal("base64Bytes")
  @js.native
  def base64Bytes(bytes: Uint8Array): String = js.native
}