package com.harana.web.external.infinite_scroll_loader_y

import slinky.core.ExternalComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("react-infinite-scroll-loader-y", "InfiniteScroll")
object ReactInfiniteScroll extends js.Object

@react object InfiniteScroll extends ExternalComponent {

  case class Props(dataLength: Int,
                   batchSize: Option[Int] = None,
                   hasMore: Boolean,
                   loadMore: (Int) => Unit,
                   threshold: Option[Int] = None,
                   manualLoadFirstSet: Option[Boolean] = None,
                   loader: Option[ReactElement] = None,
                   parentRef: Option[ReactElement] = None,
                   disabled: Option[Boolean] = None)

  override val component = ReactInfiniteScroll
}