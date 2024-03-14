package com.harana.web.external.meshgrad

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("meshgrad", JSImport.Default)
@js.native
object Meshgrad extends js.Object {
  def generateJSXMeshGradient(parameters: MeshGenerateParameters): MeshGenerateOutput = js.native
}

trait MeshGenerateParameters extends js.Object {
  val length: Int
  val baseColor: js.UndefOr[String] = js.undefined
  val hash: js.UndefOr[Int] = js.undefined
}

trait MeshGenerateOutput extends js.Object {
  val backgroundColor: js.Dynamic
  val backgroundImage: js.Dynamic
}