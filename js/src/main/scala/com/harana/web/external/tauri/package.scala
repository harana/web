package com.harana.web.external

import typings.std.ArrayBuffer

import scala.scalajs.js
import scala.scalajs.js.|

package object tauri {

  trait WindowEvent[T] extends js.Object {
    val event: String
    val windowLabel: String
    val id: Int
    val payload: T
  }

  type BinaryFileContents = List[Int] | Array[Int] | ArrayBuffer

  type WindowEventCallback[T] = js.Function1[WindowEvent[T], Unit]

  type HttpVerb = String

  type UnlistenFn = () => Unit

}
