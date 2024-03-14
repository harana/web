package com.harana.web.pages.grid.ui

import com.harana.sdk.shared.models.common.User
import com.harana.sdk.shared.models.flow.parameters.ParameterGroup
import com.harana.sdk.shared.utils.Random
import com.harana.web.components._
import com.harana.web.components.elements._
import com.harana.web.components.sidebar._
import com.harana.web.components.structure.Grid
import com.harana.web.components.table.{Column, GroupedTable, Row, RowGroup}
import com.harana.web.components.widgets.PillWidget
import com.harana.web.external.shoelace.Radio
import com.harana.web.pages.grid.GridStore._
import com.harana.web.pages.{ViewMode, filterSection, searchSection}
import com.harana.web.utils.i18nUtils.ops
import diode.{ActionResult, Circuit, Dispatcher}
import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.core.facade.{Fragment, React, ReactElement}

import scala.collection.mutable.ListBuffer
import scala.language.existentials

@react class GridPage extends StatelessComponent {

  case class Props(circuit: Circuit[_ <: AnyRef],
                   entityType: String,
                   state: GridState[_, _],
                   userId: User.UserId,
                   title: String,
                   navigationBar: Option[ReactElement],
                   tableColumns: List[Column],
                   tableContent: (Column, GridPageItem[_]) => ReactElement,
                   toolbarItems: List[HeadingItem] = List(),
                   itemSubTypes: List[(String, EntitySubType)] = List(),
                   itemMenuItems: Option[GridPageItem[_] => List[ReactElement]] = None,
                   fixedNavigationBar: Boolean = true,
                   footerNavigationBar: Option[ReactElement] = None,
                   leftSidebarSections: List[SidebarSection] = List(),
                   rightSidebarSections: List[SidebarSection] = List(),
                   showOwners: Boolean = false,
                   showTags: Boolean = true,
                   allowViewChange: Boolean = false,
                   allowDelete: Boolean = true,
                   allowEdit: Boolean = true,
                   editParameterGroupLayout: Option[ParameterGroup => ParameterGroupLayout] = None,
                   editWidth: Option[String] = None,
                   editAdditionalSections: List[(String, ReactElement)] = List())

  val drawerRef = React.createRef[Drawer.Def]
  val deleteDialogRef = React.createRef[Dialog.Def]

  override def componentDidMount() =
    props.circuit.asInstanceOf[Circuit[AnyRef]].addProcessor(
      (_: Dispatcher, action: Any, next: Any => ActionResult[AnyRef], _: AnyRef) => {
        action match {
          case ShowEditDialog(entityType, title) =>
            if (entityType == props.entityType)
              drawerRef.current.show(
                style = editStyle,
                title = title,
                values = Some(props.state.editValues),
                width = props.editWidth
              )

          case RefreshEditDialogWithValues(entityType, values) =>
            if (entityType == props.entityType)
              drawerRef.current.update(
                style = editStyle,
                values = values
              )

          case RefreshEditDialog(entityType) =>
            if (entityType == props.entityType) {
              drawerRef.current.update(
                style = editStyle,
                values = props.state.editValues
              )
            }

          case _ =>
        }
        next(action)
      }
  )

  def editStyle = {
    DrawerStyle.Sectioned(
      parametersOrSections = Left(
        DrawerParameters(
          groups = props.state.editParameterGroups,
          i18nPrefix = props.entityType,
          layout = props.editParameterGroupLayout,
          additionalSections = props.editAdditionalSections
        )
      ),
      onChange = Some((parameter, value) =>
        props.circuit.dispatch(OnEditParameterChange(props.entityType, parameter, value))
      ),
      onOk = Some(values => {
        val item = props.state.selectedItem
        props.circuit.dispatch(
          if (item.isEmpty)
            SaveNewItem(props.entityType, props.userId, values)
          else
            SaveExistingItem(props.entityType, props.userId, item.get.id, values))
      })
    )
  }


  def headingItems = {
    val headingMenu = if (props.state.viewMode == ViewMode.List)
      List(menus.headingMenu(
        props.circuit,
        props.state.viewMode,
        props.entityType,
        props.state.selectedItem,
        props.allowEdit,
        props.allowDelete,
        deleteDialogRef,
        props.itemMenuItems)
      )
    else
      List()

    val viewMode =
      if (props.allowViewChange)
        List(menus.viewMode(props.entityType, props.state.viewMode))
      else
        List()

    headingMenu ++ menus.newItem(props.circuit, props.entityType, props.itemSubTypes, props.allowEdit) ++
      viewMode ++ List(menus.sort(props.circuit, props.entityType, props.state.sortOrdering))
  }


  def leftSidebar = {
    val sections = new ListBuffer[SidebarSection]()
    sections += searchSection(props.state.searchQuery, query => props.circuit.dispatch(UpdateSearchQuery(props.entityType, query)))
    if (props.showOwners && props.state.owners.nonEmpty) sections += filterSection("Owner", false, props.state.owners, props.state.owner, owner => props.circuit.dispatch(UpdateOwner(props.entityType, owner)))
    if (props.showTags && props.state.tags.nonEmpty) sections += filterSection(i"common.sidebar.tags", true, props.state.tags, props.state.tag, tag => props.circuit.dispatch(UpdateTag(props.entityType, tag)))
    sections ++= props.leftSidebarSections
    Sidebar(sections.toList)
  }


  def content: ReactElement =
    props.state.viewMode match {
      case ViewMode.List =>
        val rows: List[Row] = props.state.items.map { item =>
          val checked = props.state.selectedItem.nonEmpty && props.state.selectedItem.get == item
          val radio = Some(Radio.Props(name = "s", checked = Some(checked), onChange = Some(checked => if (checked) props.circuit.dispatch(UpdateSelectedItem(props.entityType, Some(item))))))
          Row(props.tableColumns.map(column => column -> props.tableContent(column, item)).toMap, radio, None, onDoubleClick = item.link.map(l => () => openLink(props.circuit, l)))
        }
        GroupedTable(props.tableColumns, List(RowGroup(None, rows))).withKey(Random.short)

      case ViewMode.Grid =>
        Grid(
          props.state.items.map { item =>
            PillWidget(
              circuit = props.circuit,
              title = item.title,
              subtitle = item.description,
              chartType = None,
              link = item.link,
              background = item.background,
              menuItems = menus.itemMenu(props.circuit, props.state.viewMode, props.entityType, item, props.allowEdit, props.allowDelete, deleteDialogRef, props.itemMenuItems)
            ).withKey(item.id)
          },
          ColumnSize.Three
        )
    }


  def render() = {
    Fragment(
      Drawer().withRef(drawerRef),
      Dialog().withRef(deleteDialogRef),
      Page(
        circuit = props.circuit,
        title = props.title,
        subtitle = None,
        navigationBar = props.navigationBar,
        fixedNavigationBar = props.fixedNavigationBar,
        footerNavigationBar = props.footerNavigationBar,
        toolbarItems = props.toolbarItems ++ headingItems,
        blocked = props.state.blocked,
        leftSidebar = Some(leftSidebar),
        rightSidebar = if (props.rightSidebarSections.nonEmpty) Some(Sidebar(props.rightSidebarSections)) else None,
        content = Some(content)
      )
    )
  }
}