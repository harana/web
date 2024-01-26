package com.harana.web.external.tauri

import typings.std.ArrayBuffer

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.typedarray.Uint8Array
import scala.scalajs.js.|

object Directory {
  val Audio: Int = 1
  val Cache: Int = 2
  val Config: Int = 3
  val Data: Int = 4
  val LocalData: Int = 5
  val Desktop: Int = 6
  val Document: Int = 7
  val Download: Int = 8
  val Executable: Int = 9
  val Font: Int = 10
  val Home: Int = 11
  val Picture: Int = 12
  val Public: Int = 13
  val Runtime: Int = 14
  val Template: Int = 15
  val Video: Int = 16
  val Resource: Int = 17
  val App: Int = 18
  val Log: Int = 19
  val Temp: Int = 20
  val AppConfig: Int = 21
  val AppData: Int = 22
  val AppLocalData: Int = 23
  val AppCache: Int = 24
  val AppLog: Int = 25
}

@JSImport("@tauri-apps/api", "fs")
@js.native
object Fs extends js.Object {
  def readTextFile(filePath: String, options: FsOptions): js.Promise[String] = js.native
  def readBinaryFile(filePath: String, options: FsOptions): js.Promise[Uint8Array] = js.native
  def writeTextFile(path: String, contents: String, options: Option[FsOptions]): js.Promise[Unit] = js.native
  def writeTextFile(file: FsTextFileOption, options: Option[FsOptions]): js.Promise[Unit] = js.native
  def writeTextFile(Path: String | FsTextFileOption, contents: Option[String | FsOptions], options: Option[FsOptions]): js.Promise[Unit]= js.native
  def writeBinaryFile(path: String, contents: BinaryFileContents, options: Option[FsOptions]): js.Promise[Unit] = js.native
  def writeBinaryFile(file: FsBinaryFileOption, options: Option[FsOptions]): js.Promise[Unit] = js.native
  def writeBinaryFile(path: String | FsBinaryFileOption, contents: Option[BinaryFileContents | FsOptions], options: Option[FsOptions]): js.Promise[Unit] = js.native
  def readDir(dir: String, options: FsDirOptions): js.Promise[Array[FileEntry]] = js.native
  def createDir(dir: String, options: FsDirOptions): js.Promise[Unit] = js.native
  def removeDir(dir: String, options: FsDirOptions): js.Promise[Unit]= js.native
  def copyFile(source: String, destination: String, options: FsOptions): js.Promise[Unit] = js.native
  def removeFile(file: String, options: FsOptions): js.Promise[Unit] = js.native
  def renameFile(oldPath: String, newPath: String, options: FsOptions): js.Promise[Unit] = js.native
  def exists(path: String, options: FsOptions): js.Promise[Boolean] = js.native
}

trait FsOptions extends js.Object {
  val dir: Int
}

trait FsDirOptions extends js.Object {
  val dir: Int
  val recursive: Option[Boolean]
}

trait FsTextFileOption extends js.Object {
  val path: String
  val content: String
}

trait FsBinaryFileOption extends js.Object {
  val path: String
  val contents: BinaryFileContents
}

trait FileEntry extends js.Object {
  val path: String
  val name: Option[String]
  val children: Option[Array[FileEntry]]
}

