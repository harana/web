package com.harana.web.external.tauri

import com.harana.web.external.tauri.Tauri.convertFileSrc
import org.scalajs.macrotaskexecutor.MacrotaskExecutor.Implicits.global

import scala.concurrent.Future

object Asset {

  def src(path: String): Future[String] =
    Path.resolveResource(path).toFuture.map(file => convertFileSrc(file))
}
