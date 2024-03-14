package com.harana.web.external.tauri

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|
import typings.std.Record

@JSImport("@tauri-apps/api", "http")
@js.native
object Http extends js.Object {
  def drop(): js.Promise[Unit] = js.native
  def request[T](options: HttpOptions): js.Promise[Response[T]] = js.native
  def get[T](url: String, options: Option[HttpOptions]): js.Promise[Response[T]] = js.native
  def post[T](url: String, body: Option[Body], options: Option[HttpOptions]): js.Promise[Response[T]] = js.native
  def put[T](url: String, body: Option[Body], options: Option[HttpOptions]): js.Promise[Response[T]] = js.native
  def patch[T](url: String, options: Option[HttpOptions]): js.Promise[Response[T]] = js.native
  def delete[T](url: String, options: Option[HttpOptions]): js.Promise[Response[T]] = js.native
  def getClient(options: Option[ClientOptions]): js.Promise[Client] = js.native
  def fetch[T](url: String, options: Option[ClientOptions]): js.Promise[Response[T]] = js.native
}

@js.native
trait Client extends js.Object {
  val id: Int
}

@js.native
trait Duration extends js.Object {
  val secs: Int
  val nanos: Int
}

@js.native
trait ClientOptions extends js.Object {
  val maxRedirections: Option[Int]
  val connectTimeout: Option[Int | Duration]
}

@js.native
trait FilePart[T] extends js.Object {
  val file: String | T
  val mime: Option[String]
  val fileName: Option[String]
}

@js.native
trait HttpOptions extends js.Object {
  val method: HttpVerb
  val url: String
  val headers: Option[Map[String, js.Any]]
  val query: Option[Map[String, js.Any]]
  val body: Option[Body]
  val timeout: Option[Int | Duration]
  val responseType: Option[String]
}

@js.native
trait Response[T] extends js.Object {
  val url: String
  val status: Int
  val headers: Record[String, String]
  val rawHeaders: Record[String, Array[String]]
  val data: T
}

@JSImport("@tauri-apps/api/http", "Body")
@js.native
class Body(`type`: String, payload: js.Any) extends js.Object
object Body {
  def json(data: js.Object): Unit = new Body("Json", data)
  def text(data: String): Unit = new Body("Text", data)
}

