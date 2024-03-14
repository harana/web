package com.harana.web.external.chakra.list

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@chakra-ui/react", "ListIcon")
@js.native
object ReactListIcon extends js.Object

@react object ListIcon extends ExternalComponent {

  case class Props(centerContent: js.UndefOr[String] = js.undefined)

  override val component = ReactListIcon

}