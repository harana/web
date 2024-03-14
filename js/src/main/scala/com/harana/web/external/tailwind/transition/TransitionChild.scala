package com.harana.web.external.tailwind.transition

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@headlessui/react", "Transition.Child")
@js.native
object ReactTransitionChild extends js.Object

@react object TransitionChild extends ExternalComponent {

  case class Props(as: js.UndefOr[String | js.Object] = js.undefined,
                   appear: js.UndefOr[Boolean] = js.undefined,
                   unmount: js.UndefOr[Boolean] = js.undefined,
                   enter: js.UndefOr[String] = js.undefined,
                   enterFrom: js.UndefOr[String] = js.undefined,
                   enterTo: js.UndefOr[String] = js.undefined,
                   entered: js.UndefOr[String] = js.undefined,
                   leave: js.UndefOr[String] = js.undefined,
                   leaveFrom: js.UndefOr[String] = js.undefined,
                   leaveTo: js.UndefOr[String] = js.undefined,
                   beforeEnter: js.UndefOr[() => Unit] = js.undefined,
                   afterEnter: js.UndefOr[() => Unit] = js.undefined,
                   beforeLeave: js.UndefOr[() => Unit] = js.undefined,
                   afterLeave: js.UndefOr[() => Unit] = js.undefined)

  override val component = ReactTransitionChild
}