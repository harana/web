package com.harana.web.external.character_counter

import slinky.core.ExternalComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("react-bootstrap-character-counter", JSImport.Default)
@js.native
object ReactBootstrapCharacterCounter extends js.Object

@react object CharacterCounter extends ExternalComponent {

  case class Props(children: List[ReactElement] = List(),
                   style: Option[js.Object] = None)

  override val component = ReactBootstrapCharacterCounter
}