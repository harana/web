package com.harana.web.external.chakra

import slinky.core.ExternalComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("@chakra-ui/react", "Checkbox")
@js.native
object ReactCheckbox extends js.Object

@react object Checkbox extends ExternalComponent {

  case class Props(colorScheme: js.UndefOr[Amount] = js.undefined,
                   icon: js.UndefOr[ReactElement] = js.undefined,
                   iconColor: js.UndefOr[ReactElement] = js.undefined,
                   iconSize: js.UndefOr[String | Int] = js.undefined,
                   id: js.UndefOr[String] = js.undefined,
                   isChecked: js.UndefOr[Boolean] = js.undefined,
                   isDisabled: js.UndefOr[Boolean] = js.undefined,
                   isFocusable: js.UndefOr[Boolean] = js.undefined,
                   isIndeterminate: js.UndefOr[Boolean] = js.undefined,
                   isInvalid: js.UndefOr[Boolean] = js.undefined,
                   isReadOnly: js.UndefOr[Boolean] = js.undefined,
                   isRequired: js.UndefOr[Boolean] = js.undefined,
                   name: js.UndefOr[String] = js.undefined,
                   onChange: js.UndefOr[js.Any => Unit] = js.undefined,
                   onFocus: js.UndefOr[js.Any => Unit] = js.undefined,
                   size: js.UndefOr[String] = js.undefined,
                   spacing: js.UndefOr[String | Int] = js.undefined,
                   tabIndex: js.UndefOr[Int] = js.undefined,
                   value: js.UndefOr[String | Int] = js.undefined)

  override val component = ReactCheckbox

}