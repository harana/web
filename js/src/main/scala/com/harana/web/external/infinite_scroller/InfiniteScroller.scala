package com.harana.web.external.infinite_scroller

import slinky.core.ExternalComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("react-infinite-scroller", "InfiniteScroll")
object ReactInfiniteScroll extends js.Object

@react object InfiniteScroll extends ExternalComponent {

  case class Props(children: ReactElement,
                   loadMore: Int => Unit,
                   element: Option[String] = None,
                   hasMore: Option[Boolean] = None,
                   initialLoad: Option[Boolean] = None,
                   isReverse: Option[Boolean] = None,
                   loader: Option[ReactElement] = None,
                   pageStart: Option[Int] = None,
                   threshold: Option[Int] = None,
                   useCapture: Option[Boolean] = None,
                   useWindow: Option[Boolean] = None)

  override val component = ReactInfiniteScroll
}