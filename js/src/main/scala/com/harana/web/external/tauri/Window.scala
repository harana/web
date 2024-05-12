package com.harana.web.external.tauri

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|
import typings.std.Record

@JSImport("@tauri-apps/api/Window", JSImport.Namespace)
@js.native
object Window extends js.Object {
  def getCurrent(): WebviewWindow = js.native
  def getAll(): Array[WebviewWindow] = js.native

  def currentMonitor(): js.Promise[Option[Monitor]] = js.native
  def primaryMonitor(): js.Promise[Option[Monitor]] = js.native
  def availableMonitors(): js.Promise[Array[Monitor]] = js.native
}

@JSImport("@tauri-apps/api/WebviewWindowHandle", JSImport.Namespace)
@js.native
class WebviewWindowHandle(label: String = js.native,
                          listeners: Record[String, Array[WindowEventCallback[js.Any]]] = js.native) extends js.Object {
  def listen[T](event: String, handler: WindowEventCallback[T]): js.Promise[UnlistenFn] = js.native
  def once[T](event: String, handler: WindowEventCallback[T]): js.Promise[UnlistenFn] = js.native
  def emit(event: String, payload: Option[js.Any]): js.Promise[Unit] = js.native
}
@JSImport("@tauri-apps/api/WindowManager", JSImport.Namespace)
@js.native
class WindowManager() extends WebviewWindowHandle {
  def scaleFactor(): js.Promise[Double] = js.native
  def innerPosition(): js.Promise[PhysicalPosition] = js.native
  def outerPosition(): js.Promise[PhysicalPosition] = js.native
  def innerSize(): js.Promise[PhysicalSize] = js.native
  def outerSize(): js.Promise[PhysicalSize] = js.native
  def isFullscreen(): js.Promise[Boolean] = js.native
  def isMinimized(): js.Promise[Boolean] = js.native
  def isMaximized(): js.Promise[Boolean] = js.native
  def isFocused(): js.Promise[Boolean] = js.native
  def isDecorated(): js.Promise[Boolean] = js.native
  def isResizable(): js.Promise[Boolean] = js.native
  def isMaximizable(): js.Promise[Boolean] = js.native
  def isMinimizable(): js.Promise[Boolean] = js.native
  def isClosable(): js.Promise[Boolean] = js.native
  def isVisible(): js.Promise[Boolean] = js.native
  def title(): js.Promise[String] = js.native
  def theme(): js.Promise[Option[String]] = js.native
  def center(): js.Promise[Unit] = js.native
  def onCloseRequested(handler: WindowEventCallback[CloseRequestedEvent]): js.Promise[UnlistenFn] = js.native
  def onFileDropEvent(handler: WindowEventCallback[js.Any]): js.Promise[UnlistenFn] = js.native
  def onFocusChanged(handler: WindowEventCallback[Boolean]): js.Promise[UnlistenFn] = js.native
  def onMenuClicked(handler: WindowEventCallback[String]): js.Promise[UnlistenFn] = js.native
  def onMoved(handler: WindowEventCallback[PhysicalPosition]): js.Promise[UnlistenFn] = js.native
  def onResized(handler: WindowEventCallback[PhysicalSize]): js.Promise[UnlistenFn] = js.native
  def onScaleChanged(handler: WindowEventCallback[ScaleFactorChanged]): js.Promise[UnlistenFn] = js.native
  def onThemeChanged(handler: WindowEventCallback[String]): js.Promise[UnlistenFn] = js.native
  def requestUserAttention(requestType: Option[String]): js.Promise[Unit] = js.native
  def setAlwaysOnTop(alwaysOnTop: Boolean): js.Promise[Unit] = js.native
  def setClosable(closable: Boolean): js.Promise[Unit] = js.native
  def setContentProtected(`protected`: Boolean): js.Promise[Unit] = js.native
  def setCursorGrab(grab: Boolean): js.Promise[Unit] = js.native
  def setCursorIcon(icon: String): js.Promise[Unit] = js.native
  def setCursorPosition(position: LogicalPosition | PhysicalPosition): js.Promise[Unit] = js.native
  def setDecorations(decorations: Boolean): js.Promise[Unit] = js.native
  def setFocus(): js.Promise[Unit] = js.native
  def setFullscreen(fullscreen: Boolean): js.Promise[Unit] = js.native
  def setIcon(icon: String): js.Promise[Unit] = js.native
  def setIgnoreCursorEvents(ignore: Boolean): js.Promise[Unit] = js.native
  def setResizable(resizable: Boolean): js.Promise[Unit] = js.native
  def setMaximizable(maximizable: Boolean): js.Promise[Unit] = js.native
  def setMinimizable(minimizable: Boolean): js.Promise[Unit] = js.native
  def setMinSize(size: Option[LogicalSize | PhysicalSize]): js.Promise[Unit] = js.native
  def setMaxSize(size: Option[LogicalSize | PhysicalSize]): js.Promise[Unit] = js.native
  def setPosition(position: LogicalPosition | PhysicalPosition): js.Promise[Unit] = js.native
  def setSkipTaskbar(skip: Boolean): js.Promise[Unit] = js.native
  def setSize(size: LogicalSize | PhysicalSize): js.Promise[Unit] = js.native
  def setTitle(title: String): js.Promise[Unit] = js.native
  def startDragging(): js.Promise[Unit] = js.native
  def maximize(): js.Promise[Unit] = js.native
  def unmaximize(): js.Promise[Unit] = js.native
  def toggleMaximize(): js.Promise[Unit] = js.native
  def unminimize(): js.Promise[Unit] = js.native
  def show(): js.Promise[Unit] = js.native
  def hide(): js.Promise[Unit] = js.native
  def close(): js.Promise[Unit] = js.native
}

@JSImport("@tauri-apps/api/WebviewWindow", JSImport.Namespace)
@js.native
class WebviewWindow(val label: String, val options: WindowOptions) extends WindowManager

@JSImport("@tauri-apps/api/WebviewWindow", JSImport.Namespace)
@js.native
object WebviewWindow extends js.Object {
  def getByLabel(label: String): Option[WebviewWindow] = js.native
  def getFocusedWindow(): Option[WebviewWindow] = js.native
}

@js.native
trait Monitor extends js.Object {
  val name: Option[String]
  val size: PhysicalSize
  val position: PhysicalPosition
  val scaleFactor: Int
}

@js.native
trait ScaleFactorChanged extends js.Object {
  val scaleFactor: Int
  val size: PhysicalSize
}

@js.native
trait LogicalPosition extends js.Object {
  val `type` = "Logical"
  val x: Int
  val y: Int
}

@js.native
trait LogicalSize extends js.Object {
  val `type` = "Logical"
  val width: Int
  val height: Int
}

@js.native
trait PhysicalPosition extends js.Object {
  val `type` = "Physical"
  val x: Int
  val y: Int
}

@js.native
trait PhysicalSize extends js.Object {
  val `type` = "Physical"
  val width: Int
  val height: Int
}

@js.native
trait CloseRequestedEvent extends js.Object {
  val event: String
  val windowLabel: String
  val id: Int
}

trait WindowOptions extends js.Object {
  val url: Option[String]
  val center: Option[Boolean]
  val x: Option[Int]
  val y: Option[Int]
  val width: Option[Int]
  val height: Option[Int]
  val minWidth: Option[Int]
  val minHeight: Option[Int]
  val maxWidth: Option[Int]
  val maxHeight: Option[Int]
  val resizable: Option[Boolean]
  val title: Option[String]
  val fullscreen: Option[Boolean]
  val focus: Option[Boolean]
  val transparent: Option[Boolean]
  val maximized: Option[Boolean]
  val visible: Option[Boolean]
  val decorations: Option[Boolean]
  val alwaysOnTop: Option[Boolean]
  val contentProtected: Option[Boolean]
  val skipTaskbar: Option[Boolean]
  val fileDropEnabled: Option[Boolean]
  val theme: Option[String]
  val titleBarStyle: Option[String]
  val hiddenTitle: Option[Boolean]
  val acceptFirstMouse: Option[Boolean]
  val tabbingIdentifier: Option[String]
  val maximizable: Option[Boolean]
  val minimizable: Option[Boolean]
  val closable: Option[Boolean]
}
