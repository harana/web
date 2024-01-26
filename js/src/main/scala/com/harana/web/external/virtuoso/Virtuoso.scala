package com.harana.web.external.virtuoso

import org.scalajs.dom.{Element, HTMLElement}
import slinky.core.{ExternalComponent, ExternalComponentWithRefType}
import slinky.core.annotations.react
import slinky.core.facade.{ReactElement, ReactRef}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("react-virtuoso", "Virtuoso")
@js.native
object ReactVirtuoso extends js.Object

@js.native
trait ReactVirtuosoApi extends js.Object {
  def scrollToIndex(index: Int, align: String, behaviour: String): Unit = js.native
  def scrollIntoView(location: ScrollIntoViewLocation): Unit = js.native
}


@react object Virtuoso extends ExternalComponentWithRefType[ReactVirtuosoApi] {

  case class Props(data: List[_],
                   itemContent: Int => ReactElement,
                   className: js.UndefOr[String] = js.undefined,
                   fixedItemHeight: js.UndefOr[Int] = js.undefined,
                   increaseViewportBy: js.UndefOr[Int | IncreaseViewport] = js.undefined,
                   overscan: js.UndefOr[Int] = js.undefined,
                   itemSize: js.UndefOr[(HTMLElement, String) => Int] = js.undefined,
                   useWindowScroll: Boolean = false,
                   customScrollParent: js.UndefOr[org.scalajs.dom.Element] = js.undefined,
                   style: js.UndefOr[Style] = js.undefined,
                   endReached: js.UndefOr[Int => Unit] = js .undefined,
                   totalCount: js.UndefOr[Int] = js.undefined,
                   defaultItemHeight: js.UndefOr[Int] = js.undefined,
                   scrollerRef: js.UndefOr[HTMLElement => Unit] = js.undefined)

  override val component = ReactVirtuoso
}

trait Style extends js.Object {
  val height: String
}

trait ScrollIntoViewLocation extends js.Object {
  val index: Int
  val align: js.UndefOr[String] = js.undefined
  val behavior: js.UndefOr[String] = js.undefined
  val done: js.Function0[Unit]
}

trait IncreaseViewport extends js.Object {
  val top: Int
  val bottom: Int
}