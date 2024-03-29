package com.harana.web.external.shoelace

import org.scalajs.dom.HTMLElement
import slinky.core.annotations.react
import slinky.core.facade.{React, ReactElement}
import slinky.core.{CustomTag, StatelessComponent, TagMod}
import slinky.web.html.ref

import scala.collection.mutable.ListBuffer
import scala.scalajs.js

@react class Details extends StatelessComponent {

  val elementRef = React.createRef[HTMLElement]

  case class Props(children: List[ReactElement] = List(),
                   className: Option[String] = None,
                   disabled: Option[Boolean] = None,
                   open: Option[Boolean] = None,
                   slot: Option[String] = None,
                   summary: Option[String] = None,
                   onAfterHide: Option[js.Any => Unit] = None,
                   onAfterShow: Option[js.Any => Unit] = None,
                   onHide: Option[js.Any => Unit] = None,
                   onShow: Option[js.Any => Unit] = None)

  override def componentDidMount(): Unit = {
    if (props.onAfterHide.nonEmpty) elementRef.current.addEventListener("sl-after-hide", props.onAfterHide.get)
    if (props.onAfterShow.nonEmpty) elementRef.current.addEventListener("sl-after-show", props.onAfterShow.get)
    if (props.onHide.nonEmpty) elementRef.current.addEventListener("sl-hide", props.onHide.get)
    if (props.onShow.nonEmpty) elementRef.current.addEventListener("sl-show", props.onShow.get)
  }

  override def componentWillUnmount(): Unit = {
    if (props.onAfterHide.nonEmpty) elementRef.current.removeEventListener("sl-after-hide", props.onAfterHide.get)
    if (props.onAfterShow.nonEmpty) elementRef.current.removeEventListener("sl-after-show", props.onAfterShow.get)
    if (props.onHide.nonEmpty) elementRef.current.removeEventListener("sl-hide", props.onHide.get)
    if (props.onShow.nonEmpty) elementRef.current.removeEventListener("sl-show", props.onShow.get)
  }

  def show() =
    elementRef.current.asInstanceOf[js.Dynamic].show()

  def hide() =
    elementRef.current.asInstanceOf[js.Dynamic].hide()

  def render() = {
    val attrs = new ListBuffer[TagMod[_]]()
    add(attrs, props.className, "class")
    add(attrs, props.disabled, "disabled")
    add(attrs, props.open, "open")
    add(attrs, props.slot, "slot")
    add(attrs, props.summary, "summary")

    attrs += (ref := elementRef)

    CustomTag("sl-details")(attrs.toSeq: _*)(props.children: _*)
  }
}