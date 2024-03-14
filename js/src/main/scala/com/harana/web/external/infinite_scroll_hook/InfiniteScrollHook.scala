package com.harana.web.external.infinite_scroll_hook

import slinky.core.facade.{ReactElement, ReactRef}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("react-infinite-scroll-hook", "useInfiniteScroll")
object ReactInfiniteScroll extends js.Object {
  def useInfiniteScroll(args: UseInfiniteScrollHookArgs): UseInfiniteScrollHookResult = js.native
}

@js.native
trait UseInfiniteScrollHookArgs extends js.Object {
  val loading: Boolean = js.native
  val hasNextPage: Boolean = js.native
  val onLoadMore: Boolean = js.native
  val disabled: Option[Boolean] = js.native
  val delayInMs: Option[Int] = js.native
}

@js.native
trait UseInfiniteScrollHookResult extends js.Object {
  val ref: ReactRef[ReactElement] = js.native
  val rootRef: ReactRef[ReactElement] = js.native
}