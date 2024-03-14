package com.harana.web.handlers.navigation.ui

import com.harana.sdk.shared.models.jwt.HaranaClaims
import com.harana.web.components.LinkType
import com.harana.web.components.elements._
import com.harana.web.external.shoelace.Button
import com.harana.web.handlers.navigation.NavigationStore
import com.harana.web.handlers.navigation.NavigationStore.OpenCheckout
import com.harana.web.utils.i18nUtils.ops
import diode.Circuit
import org.scalajs.dom.window
import slinky.core.FunctionalComponent
import slinky.core.annotations.react


@react object Navigation {

  case class Props(circuit: Circuit[_ <: AnyRef],
                   navigationItems: List[(NavigationBarItem, Option[LinkType.Page])],
                   claims: HaranaClaims,
                   state: NavigationStore.State,
                   authDomain: Option[String])

  val component = FunctionalComponent[Props] { props =>

    if (props.authDomain.nonEmpty) {
      val billingCheckoutItem = NavigationBarItem(
        title = None,
        item = Some((ItemType.Button(Button.Props(
          label = Some(i"heading.signup"),
          loading = Some(props.state.openingCheckout),
          `type` = Some("primary"),
          className = Some("signup-button"),
          onClick = Some(_ => props.circuit.dispatch(OpenCheckout))
        )), ItemPosition.Right))
      )

      val userProfileItem = NavigationBarItem(
        None,
        None,
        showCaret = true,
        props.claims.imageUrl,
        Some(props.claims.firstName)
      )

      NavigationBar(
        circuit = props.circuit,
        authDomain = props.authDomain,
        leftItems = props.navigationItems,
        rightItems = if (props.claims.billing.subscriptionEnded.isEmpty) List(userProfileItem -> None) else List(billingCheckoutItem -> None, userProfileItem -> None),
        activeItem = props.navigationItems.find(item => item._2.nonEmpty && s"${window.location.pathname}".startsWith(item._2.get.name)).map(_._1),
        position = None,
        size = None,
        style = List(NavigationBarStyle.Inverse, NavigationBarStyle.Transparent),
        logoImageUrl = "/public/images/logo.png",
        logoLink = LinkType.Page("/"),
        isDebugging = false
      )
    } else
      NavigationBar(
        circuit = props.circuit,
        authDomain = None,
        leftItems = props.navigationItems,
        rightItems = List(),
        activeItem = props.navigationItems.find(item => item._2.nonEmpty && s"${window.location.pathname}".startsWith(item._2.get.name)).map(_._1),
        position = None,
        size = None,
        style = List(NavigationBarStyle.Inverse, NavigationBarStyle.Transparent),
        logoImageUrl = "/public/images/logo.png",
        logoLink = LinkType.Page("/"),
        isDebugging = false
      )
  }
}