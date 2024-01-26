package com.harana.web.external.tauri

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@tauri-apps/api", "cli")
@js.native
object Cli extends js.Object {
  def getMatches: js.Promise[CliMatches] = js.native
}

@js.native
trait ArgMatch extends js.Object {
  val value: Option[String] | Option[Boolean] | Array[String] = js.native
  val occurrences: Int  = js.native
}

@js.native
trait SubcommandMatch extends js.Object {
  val name: String = js.native
  val matches: CliMatches  = js.native
}

@js.native
trait CliMatches extends js.Object {
  val args: Map[String, ArgMatch] = js.native
  val subcommand: Option[SubcommandMatch] = js.native
}
