package com.harana.web.external.list_content_loader

import slinky.core.ExternalComponent
import slinky.core.annotations.react
import typings.react.mod.CSSProperties

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("react-content-loader", "List")
@js.native
object ReactListContentLoader extends js.Object

@react object ListContentLoader extends ExternalComponent {

  case class Props(animate: Option[Boolean] = None,
                   ariaLabel: Option[String] = None,
                   className: Option[String] = None,
                   height: Option[Double] = None,
                   preserveAspectRatio: Option[String | String] = None,
                   primaryColor: Option[String] = None,
                   primaryOpacity: Option[Double] = None,
                   secondaryColor: Option[String] = None,
                   secondaryOpacity: Option[Double] = None,
                   speed: Option[Double] = None,
                   style: Option[CSSProperties] = None,
                   uniquekey: Option[String] = None,
                   width: Option[Double] = None)

  override val component = ReactListContentLoader
}