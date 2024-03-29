package com.harana.web.components.elements

import com.harana.sdk.shared.models.flow.parameters.{Parameter, ParameterGroup}
import com.harana.sdk.shared.utils.{HMap, Random}
import com.harana.web.components.ParameterGroupLayout
import com.harana.web.external.shoelace.{Button => ShoelaceButton, Drawer => ShoelaceDrawer}
import com.harana.web.utils.i18nUtils.ops
import slinky.core.Component
import slinky.core.annotations.react
import slinky.core.facade.{Fragment, React, ReactElement, ReactRef}
import slinky.web.html.{className, div, h4, key}

import scala.collection.mutable

@react class Drawer extends Component {

	val drawerRef = React.createRef[ShoelaceDrawer.Def]
	val parameterRefs = mutable.Map.empty[Parameter[_], ReactRef[ParameterItem.Def]]

	type Props = Unit

	case class State(style: Option[DrawerStyle],
									 values: Option[HMap[Parameter.Values]],
									 title: Option[String],
									 width: Option[String])

	def initialState = State(None, None, None, None)

	def show(style: DrawerStyle,
					 values: Option[HMap[Parameter.Values]] = None,
					 title: Option[String] = None,
					 width: Option[String] = None) = {
		update(style, values.getOrElse(HMap.empty), title, width)
		drawerRef.current.show()
	}


	def update(style: DrawerStyle,
						 values: HMap[Parameter.Values]): Unit =
		update(style, values, state.title, state.width)


	def update(style: DrawerStyle,
						 values: HMap[Parameter.Values],
						 title: Option[String],
						 width: Option[String] = None): Unit = {

		if (State(Some(style), Some(values), title, width) == state) return

		style match {
			case DrawerStyle.Sectioned(parametersOrSections, _, _, _, _, _, _) =>
				parametersOrSections match {
					case Left(pt) =>
						var updatedValues = values
						parameterRefs.clear()

						pt.groups.flatMap(_.parameters).foreach { p =>
							parameterRefs += (p -> React.createRef[ParameterItem.Def])
							if (!updatedValues.contains(p) && p.default.nonEmpty)
								updatedValues +~= (p, p.default.get)
						}
						setState(State(Some(style), Some(updatedValues), title, width))

					case Right(_) =>
						setState(State(Some(style), None, title, width))
				}
			case _ =>
				setState(State(Some(style), Some(values), title, width))
		}
	}


	def hide() = {
		drawerRef.current.hide()
		setState(State(None, None, None, None))
	}

	def render() = {
		if (state.style.nonEmpty)
			state.style.get match {
				case DrawerStyle.General(innerElement, okButtonLabel, onOk, showHeader) =>
					ShoelaceDrawer(label = state.title, width = state.width, noHeader = Some(!showHeader))(
						List(
							innerElement,
							ShoelaceButton(label = Some(okButtonLabel), slot = Some("footer"), `type` = Some("primary"), onClick = Some(_ => {
								if (onOk.nonEmpty) onOk.get.apply()
								hide()
							}))
						)
					).withRef(drawerRef)

				case DrawerStyle.Sectioned(parametersOrSections, onChange, onOk, onCancel, showCancelButton, alwaysShowTitle, showHeader) =>
					val content: ReactElement = parametersOrSections match {

						case Left(info) =>
							if (info.groups.size + info.additionalSections.size == 1) {
								val group = info.groups.head

								if (alwaysShowTitle)
									div(className := "drawer-section")(
										h4(className := "drawer-header")(i"${info.i18nPrefix}.section.${group.name}.title"),
										layoutParameterGroup(group, info, onChange, group.parameters.head)
									)
								else
									layoutParameterGroup(group, info, onChange, group.parameters.head)
							} else
								List(
									Fragment(
										info.groups.zipWithIndex.map {
											case (group, index) =>
												div(className := "drawer-section", key := index.toString)(
													h4(className := "drawer-header")(i"${info.i18nPrefix}.section.${group.name}.title"),
													layoutParameterGroup(group, info, onChange, info.groups.head.parameters.head)
												)
										} ++ info.additionalSections.map {
											case (name, content) =>
												div(className := "drawer-section", key := name)(
													h4(className := "drawer-header")(i"${info.i18nPrefix}.section.$name.title"),
													List(content)
												)
										}
									)
								)

						case Right(sections) =>
							List(
								Fragment(
									sections.map { section =>
										Fragment(
											h4(className := "drawer-header")(section._1),
											List(section._2)
										)
									}
								)
							)
					}

					val buttons: List[ReactElement] = if (showCancelButton)
						List(
							ShoelaceButton(label = Some(i"common.dialog.cancel"), slot = Some("footer"), `type` = Some("default"), onClick = Some(_ => {
								if (onCancel.nonEmpty) onCancel.get.apply()
								hide()
							})),
							ShoelaceButton(label = Some(i"common.dialog.save"), slot = Some("footer"), `type` = Some("success"), onClick = Some(_ => {
								if (onOk.nonEmpty) {
//									parameterRefs.values.foreach(_.current.validate)
									if (parameterRefs.forall(_._2.current.isValid)) {
										onOk.get.apply(state.values.getOrElse(HMap.empty))
										hide()
									}else{
										println(s"Validation failed for parameters: ${parameterRefs.filterNot(_._2.current.isValid).map(_._1.name)}")
									}
								}else
									hide()
							}))
						)
					else
						List(
							ShoelaceButton(label = Some(i"common.dialog.ok"), slot = Some("footer"), `type` = Some("success"), onClick = Some(_ => {
								if (onOk.nonEmpty) onOk.get.apply(state.values.getOrElse(HMap.empty))
								hide()
							}))
						)

					ShoelaceDrawer(label = state.title, width = state.width, noHeader = Some(!showHeader))(List(content) ++ buttons).withRef(drawerRef)
			}
		else div()
	}


	private def layoutParameterGroup(group: ParameterGroup,
																	 info: DrawerParameters,
																	 onChange: Option[(Parameter[_], Any) => Unit],
																	 focusParameter: Parameter[_]): List[ReactElement] =
		info.layout.map(l => l(group)) match {
			case Some(ParameterGroupLayout.Grid(columns)) =>
				List(com.harana.web.components.structure.Grid(group.parameters.map(p => parameterItem(p, group, s"${info.i18nPrefix}.section.${group.name}", onChange, p == focusParameter)), columns))

			case None | Some(ParameterGroupLayout.List) =>
				List(group.parameters.map(p => parameterItem(p, group, s"${info.i18nPrefix}.section.${group.name}", onChange, p == focusParameter)))
		}


	private def parameterItem(p: Parameter[_],
														group: ParameterGroup,
														i18nPrefix: String,
														onChange: Option[(Parameter[_], Any) => Unit],
														autoFocus: Boolean) = {
		val values = state.values.getOrElse(HMap.empty)
		ParameterItem(
			parameter = p,
			i18nPrefix = i18nPrefix,
			value = values.underlying.get(p),
			onChange = Some((_, value) => {
				if (onChange.nonEmpty) onChange.get(p, value)
			}),
			autoFocus = autoFocus
		).withRef(parameterRefs(p))
	}
}

case class DrawerParameters(groups: List[ParameterGroup],
														i18nPrefix: String,
														layout: Option[ParameterGroup => ParameterGroupLayout] = None,
														additionalSections: List[(String, ReactElement)] = List())

sealed trait DrawerStyle
object DrawerStyle {

	case class General(innerElement: ReactElement,
										 okButtonLabel: String,
										 onOk: Option[() => Unit] = None,
										 showHeader: Boolean = false) extends DrawerStyle

	case class Sectioned(parametersOrSections: Either[DrawerParameters, List[(String, ReactElement)]],
											 onChange: Option[(Parameter[_], Any) => Unit] = None,
											 onOk: Option[HMap[Parameter.Values] => Unit] = None,
											 onCancel: Option[() => Unit] = None,
											 showCancelButton: Boolean = true,
											 alwaysShowTitle: Boolean = true,
											 showHeader: Boolean = false) extends DrawerStyle
}