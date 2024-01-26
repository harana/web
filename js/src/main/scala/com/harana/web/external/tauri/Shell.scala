package com.harana.web.external.tauri

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|
import typings.std.Record

@JSImport("@tauri-apps/api", "shell")
@js.native
object Shell extends js.Object {
  def exit(exitCode: Int = 0): js.Promise[Unit] = js.native
  def relaunch(): js.Promise[Unit] = js.native
  def execute(onEvent: ShellEvent[String, String] => Unit,
              program: String,
              args: String | Array[String],
              options: Option[InternalSpawnOptions]): js.Promise[Int] = js.native
  def open(path: String, openWith: Option[String]): js.Promise[Unit] = js.native
}

@js.native
trait ShellEvent[T, V] extends js.Object {
  val event: T
  val payload: V
}

@js.native
trait TerminatedPayload extends js.Object {
  val code: Option[Int]
  val signal: Option[Int]
}

@js.native
trait SpawnOptions extends js.Object {
  val cwd: Option[String]
  val env: Record[String, String]
  val encoding: Option[String]
}

@js.native
trait InternalSpawnOptions extends SpawnOptions {
  val sidecar: Option[String]
}

@js.native
trait ChildProcess extends js.Object {
  val code: Option[Int]
  val signal: Option[Int]
  val stdout: String
  val stderr: String
}