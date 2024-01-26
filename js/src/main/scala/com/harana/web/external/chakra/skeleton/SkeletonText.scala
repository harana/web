package com.harana.web.external.chakra.skeleton

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@chakra-ui/react", "SkeletonText")
@js.native
object ReactSkeletonText extends js.Object

@react object SkeletonText extends ExternalComponent {

  case class Props(centerContent: js.UndefOr[String] = js.undefined)

  override val component = ReactSkeletonText

}