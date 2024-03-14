package com.harana.web.external.html_to_image

import org.scalajs.dom.{Blob, Element, HTMLCanvasElement}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.typedarray.Uint8ClampedArray

@JSImport("html-to-image", JSImport.Namespace)
@js.native
object HtmlToImage extends js.Object {
  def toBlob(element: Element, options: js.UndefOr[Options] = js.undefined): js.Promise[Blob] = js.native
  def toCanvas(element: Element, options: js.UndefOr[Options] = js.undefined): js.Promise[HTMLCanvasElement] = js.native
  def toJpeg(element: Element, options: js.UndefOr[Options] = js.undefined): js.Promise[String] = js.native
  def toPng(element: Element, options: js.UndefOr[Options] = js.undefined): js.Promise[String] = js.native
  def toPixelData(element: Element, options: js.UndefOr[Options] = js.undefined): js.Promise[Uint8ClampedArray] = js.native
  def toSvg(element: Element, options: js.UndefOr[Options] = js.undefined): js.Promise[String] = js.native
}

@js.native
trait Options extends js.Object {
  val width: js.UndefOr[Int] = js.undefined
  val height: js.UndefOr[Int] = js.undefined
  val backgroundColor: js.UndefOr[String] = js.undefined
  val canvasWidth: js.UndefOr[Int] = js.undefined
  val canvasHeight: js.UndefOr[Int] = js.undefined
  val style: js.UndefOr[js.Dynamic] = js.undefined
  val quality: js.UndefOr[Int] = js.undefined
  val cacheBust: js.UndefOr[Boolean] = js.undefined
  val includeQueryParams: js.UndefOr[Boolean] = js.undefined
  val imagePlaceholder: js.UndefOr[String] = js.undefined
  val pixelRatio: js.UndefOr[Int] = js.undefined
  val skipFonts: js.UndefOr[Boolean] = js.undefined
  val preferredFontFormat: js.UndefOr[String] = js.undefined
  val fontEmbedCSS: js.UndefOr[String] = js.undefined
  val skipAutoScale: js.UndefOr[Boolean] = js.undefined
  val `type`: js.UndefOr[String] = js.undefined
}