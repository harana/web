package com.harana.web.external.shoelace

import com.harana.sdk.shared.utils.Random
import org.scalajs.dom.HTMLElement
import slinky.core.annotations.react
import slinky.core.facade.{React, ReactElement}
import slinky.core.{CustomTag, StatelessComponent, TagMod}
import slinky.web.html._

import scala.collection.mutable.ListBuffer
import scala.scalajs.js

@react class Icon extends StatelessComponent {

  val id = Random.short
  val elementRef = React.createRef[HTMLElement]

  case class Props(className: Option[String] = None,
                   label: Option[String] = None,
                   library: Option[String] = None,
                   name: String,
                   src: Option[String] = None,
                   onError: Option[js.Any => Unit] = None,
                   onLoad: Option[js.Any => Unit] = None)

  
  override def shouldComponentUpdate(nextProps: Props, nextState: Unit) = false

  override def componentDidMount(): Unit = {
    if (props.onError.nonEmpty) elementRef.current.addEventListener("sl-error", props.onError.get)
    if (props.onLoad.nonEmpty) elementRef.current.addEventListener("sl-load", props.onLoad.get)
  }

  override def componentWillUnmount(): Unit = {
    if (props.onError.nonEmpty) elementRef.current.removeEventListener("sl-error", props.onError.get)
    if (props.onLoad.nonEmpty) elementRef.current.removeEventListener("sl-load", props.onLoad.get)
  }

  def render() = {
    val attrs = new ListBuffer[TagMod[_]]()
    add(attrs, props.className, "class")
    add(attrs, props.label, "label")
    add(attrs, props.library, "library")
    add(attrs, props.name, "name")
    add(attrs, props.src, "src")

    attrs += (ref := elementRef)
    CustomTag("sl-icon")(attrs.toSeq: _*)
  }
}