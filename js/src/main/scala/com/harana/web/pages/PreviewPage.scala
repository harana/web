package com.harana.web.pages

import com.harana.sdk.shared.PreviewData
import com.harana.sdk.shared.models.HaranaFile
import com.harana.web.components.elements.{HeadingItem, Page}
import com.harana.web.components.sidebar._
import com.harana.web.external.axui_data_grid.Column
import com.harana.web.external.shoelace.Menu
//import com.harana.web.external.syntax_highlighter.{HighlightStyle, SyntaxHighlighter}
import com.harana.web.utils.FileUtils
import com.harana.web.utils.i18nUtils.ops
import diode.Circuit
import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html._


@react class PreviewPage extends StatelessComponent {

  case class Props(circuit: Circuit[_],
                   file: HaranaFile,
                   path: List[String],
                   title: String,
                   subtitle: Option[String] = None,
                   subtitleMenu: Option[Menu.Props] = None,
                   preview: Option[Either[String, PreviewData]] = None,
                   navigationBar: Option[ReactElement] = None,
                   fixedNavigationBar: Boolean = true,
                   footerNavigationBar: Option[ReactElement] = None,
                   toolbarItems: List[HeadingItem] = List(),
                   blocked: Boolean = false,
                   sidebarAboutItems: List[TextListItem])


	override def shouldComponentUpdate(nextProps: Props, nextState: Unit) =
    !(props.path.equals(nextProps.path)) || !(props.file.equals(nextProps.file))
  
  
  def sidebar =
    Sidebar(
      List(SidebarSection(Some(i"files.sidebar.about"), allowCollapse = false, allowClear = false, None, TextListSection(props.sidebarAboutItems)))
    )


//  def codePreview =
//    SyntaxHighlighter(language = FileUtils.highlightType(props.file), HighlightStyle.atomOneLight)(props.preview.map(_.left.get))


  def imagePreview = {
    img(src := s"/api/files/preview?path=${props.path.mkString("/")}")
  }


  def tablePreview: ReactElement = {
    if (props.preview.nonEmpty) {
      val preview = props.preview.get.toOption.get
      val columns = preview.headers.map { header => new Column {
        override val key = header
        override val label = header
      }}

      div(className := "panel panel-flat")(
        div(className := "table-responsive")(
          table(className := "table text-nowrap preview-table")(
            thead(preview.headers.map(th(_))),
            tbody(preview.rows.map(r => tr(r.map(td(_)))))
          )
        )
      )

    }else
      div()
  }


  def unknownPreview =
    div(className := "panel panel-flat")(
      div(className := "panel-body panel-fullscreen panel-centered")(
        "Preview not available for this file type"
      )
    )


  def render() =
    Page(
      circuit = props.circuit,
      title = props.title,
      subtitle = props.subtitle,
      subtitleMenu = props.subtitleMenu,
      navigationBar = props.navigationBar,
      fixedNavigationBar = props.fixedNavigationBar,
      footerNavigationBar = props.footerNavigationBar,
      toolbarItems = props.toolbarItems,
      blocked = props.blocked,
      leftSidebar = Some(sidebar),
      content =
        props.file match {
//          case f if FileUtils.isCode(f) => codePreview
          case f if FileUtils.isImage(f) => imagePreview
          case f if FileUtils.isTabular(f) => tablePreview
          case _ => unknownPreview
        }
    )
}