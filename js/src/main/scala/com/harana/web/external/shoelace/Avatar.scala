package com.harana.web.external.shoelace

import slinky.core.annotations.react
import slinky.core.{CustomTag, StatelessComponent, TagMod}

import scala.collection.mutable.ListBuffer
import scala.scalajs.js

@react class Avatar extends StatelessComponent {

  case class Props(className: Option[String] = None,
                   image: Option[String] = None,
                   initials: Option[String] = None,
                   label: Option[String] = None,
                   loading: Option[String] = None,
                   shape: Option[String] = None,
                   slot: Option[String] = None,
                   size: Option[String] = None)

  def render() = {
    val attrs = new ListBuffer[TagMod[_]]()
    add(attrs, props.className, "class")
    add(attrs, props.image, "image")
    add(attrs, props.initials, "initials")
    add(attrs, props.label, "label")
    add(attrs, props.loading, "loading")
    add(attrs, props.shape, "shape")
    add(attrs, props.slot, "slot")

    val style = js.Dynamic.literal()
    if (props.size.nonEmpty) style.updateDynamic("--size")(props.size.get)
    add(attrs, Some(style), "style")

    CustomTag("sl-avatar")(attrs.toSeq: _*)
  }
}