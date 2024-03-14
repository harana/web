package com.harana.web.external.text_codec

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal
import scala.scalajs.js.typedarray.Uint8Array

@JSGlobal("TextDecoder")
@js.native
class TextDecoder(utfLabel: js.UndefOr[String]) extends js.Object {
  def decode(buffer: Uint8Array): String = js.native
}