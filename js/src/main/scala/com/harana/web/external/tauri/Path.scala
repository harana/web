package com.harana.web.external.tauri

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@tauri-apps/api", "path")
@js.native
object Path extends js.Object {
  def appDir(): js.Promise[String] = js.native
  def appConfigDir(): js.Promise[String] = js.native
  def appDataDir(): js.Promise[String] = js.native
  def appLocalDataDir(): js.Promise[String] = js.native
  def appLogDir(): js.Promise[String] = js.native
  def appCacheDir(): js.Promise[String] = js.native
  def audioDir(): js.Promise[String] = js.native
  def basename(path: String, ext: Option[String]): js.Promise[String] = js.native
  def cacheDir(): js.Promise[String] = js.native
  def configDir(): js.Promise[String] = js.native
  def dataDir(): js.Promise[String] = js.native
  def desktopDir(): js.Promise[String] = js.native
  def dirname(path: String): js.Promise[String] = js.native
  def documentDir(): js.Promise[String] = js.native
  def downloadDir(): js.Promise[String] = js.native
  def executableDir(): js.Promise[String] = js.native
  def extname(path: String): js.Promise[String] = js.native
  def fontDir(): js.Promise[String] = js.native
  def homeDir(): js.Promise[String] = js.native
  def isAbsolute(path: String): js.Promise[Boolean] = js.native
  def joins(paths: String): js.Promise[String] = js.native
  def localDataDir(): js.Promise[String] = js.native
  def logDir(): js.Promise[String] = js.native
  def normalize(path: String): js.Promise[String] = js.native
  def pictureDir(): js.Promise[String] = js.native
  def publicDir(): js.Promise[String] = js.native
  def resolve(paths: js.Array[String]): js.Promise[String] = js.native
  def resourceDir(): js.Promise[String] = js.native
  def resolveResource(resourcePath: String): js.Promise[String] = js.native
  def runtimeDir(): js.Promise[String] = js.native
  def templateDir(): js.Promise[String] = js.native
  def videoDir(): js.Promise[String] = js.native
}