package com.harana.web.external.tailwind

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object SolidIcons {

  @js.native @JSImport("@heroicons/react/20/solid", "Bars3Icon")
  object ReactBars3Icon extends js.Object

  @react object Bars3Icon extends ExternalComponent {
    case class Props(className: js.UndefOr[String] = js.undefined)
    override val component = ReactBars3Icon
  }

  @js.native
  @JSImport("@heroicons/react/20/solid", "CheckIcon")
  object ReactCheckIcon extends js.Object
  @react object CheckIcon extends ExternalComponent {
    case class Props(className: js.UndefOr[String] = js.undefined)
    override val component = ReactCheckIcon
  }

  @js.native
  @JSImport("@heroicons/react/20/solid", "CheckCircleIcon")
  object ReactCheckCircleIcon extends js.Object
  @react object CheckCircleIcon extends ExternalComponent {
    case class Props(className: js.UndefOr[String] = js.undefined)
    override val component = ReactCheckCircleIcon
  }

  @js.native @JSImport("@heroicons/react/20/solid", "ChevronDownIcon")
  object ReactChevronDownIcon extends js.Object
  @react object ChevronDownIcon extends ExternalComponent {
    case class Props(className: js.UndefOr[String] = js.undefined)
    override val component = ReactChevronDownIcon
  }

  @js.native
  @JSImport("@heroicons/react/20/solid", "ChevronUpDownIcon")
  object ReactChevronUpDownIcon extends js.Object

  @react object ChevronUpDownIcon extends ExternalComponent {
    case class Props(className: js.UndefOr[String] = js.undefined)

    override val component = ReactChevronUpDownIcon
  }

  @js.native @JSImport("@heroicons/react/20/solid", "ChevronRightIcon")
  object ReactChevronRightIcon extends js.Object

  @react object ChevronRightIcon extends ExternalComponent {
    case class Props(className: js.UndefOr[String] = js.undefined)
    override val component = ReactChevronRightIcon
  }

  @js.native
  @JSImport("@heroicons/react/20/solid", "PencilIcon")
  object ReactPencilIcon extends js.Object

  @react object PencilIcon extends ExternalComponent {
    case class Props(className: js.UndefOr[String] = js.undefined)
    override val component = ReactPencilIcon
  }

  @js.native
  @JSImport("@heroicons/react/20/solid", "PencilSquareIcon")
  object ReactPencilSquareIcon extends js.Object

  @react object PencilSquareIcon extends ExternalComponent {
    case class Props(className: js.UndefOr[String] = js.undefined)

    override val component = ReactPencilSquareIcon
  }

  @js.native
  @JSImport("@heroicons/react/24/solid", "TrashIcon")
  object ReactTrashIcon extends js.Object
  @react object TrashIcon extends ExternalComponent {
    case class Props(className: js.UndefOr[String] = js.undefined)
    override val component = ReactTrashIcon
  }

  @js.native
  @JSImport("@heroicons/react/20/solid", "EllipsisVerticalIcon")
  object ReactEllipsisVerticalIcon extends js.Object
  @react object EllipsisVerticalIcon extends ExternalComponent {
    case class Props(className: js.UndefOr[String] = js.undefined)
    override val component = ReactEllipsisVerticalIcon
  }

  @js.native @JSImport("@heroicons/react/20/solid", "PlayIcon")
  object ReactPlayIcon extends js.Object

  @react object PlayIcon extends ExternalComponent {
    case class Props(className: js.UndefOr[String] = js.undefined)
    override val component = ReactPlayIcon
  }

  @js.native
  @JSImport("@heroicons/react/20/solid", "PauseIcon")
  object ReactPauseIcon extends js.Object

  @react object PauseIcon extends ExternalComponent {
    case class Props(className: js.UndefOr[String] = js.undefined)
    override val component = ReactPauseIcon
  }

  @js.native
  @JSImport("@heroicons/react/20/solid", "PlusIcon")
  object ReactPlusIcon extends js.Object

  @react object PlusIcon extends ExternalComponent {
    case class Props(className: js.UndefOr[String] = js.undefined)
    override val component = ReactPlusIcon
  }

  @js.native
  @JSImport("@heroicons/react/20/solid", "UserIcon")
  object ReactUserIcon extends js.Object

  @react object UserIcon extends ExternalComponent {
    case class Props(className: js.UndefOr[String] = js.undefined)
    override val component = ReactUserIcon
  }

  @js.native
  @JSImport("@heroicons/react/20/solid", "XCircleIcon")
  object ReactXCircleIcon extends js.Object

  @react object XCircleIcon extends ExternalComponent {
    case class Props(className: js.UndefOr[String] = js.undefined)
    override val component = ReactXCircleIcon
  }

  @js.native
  @JSImport("@heroicons/react/20/solid", "XMarkIcon")
  object ReactXmarkIcon extends js.Object

  @react object XMarkIcon extends ExternalComponent {
    case class Props(className: js.UndefOr[String] = js.undefined)
    override val component = ReactXmarkIcon
  }

}