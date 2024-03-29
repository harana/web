package com.harana.web.handlers.files

import com.harana.sdk.shared.PreviewData
import com.harana.sdk.shared.models.HaranaFile
import com.harana.sdk.shared.models.flow.parameters.{Parameter, StringParameter}
import com.harana.sdk.shared.utils.HMap
import com.harana.web.external.filepond.UploadedFile
import com.harana.web.pages.FilterItem
import com.harana.web.pages.grid.SortOrdering
import diode.{Circuit, Action => DiodeAction}

object FilesStore {

  case class State(blocked: Boolean,
                   files: List[HaranaFile],
                   item: Option[HaranaFile],
                   itemPreview: Option[Either[String, PreviewData]],
                   path: List[String],
                   pathStr: String,
                   searchQuery: Option[String],
                   selectedFile: Option[HaranaFile],
                   sortOrdering: SortOrdering,
                   tags: List[FilterItem],
                   tag: Option[FilterItem],
                   uploadedFiles: List[UploadedFile],
                   content: Map[String, String])

  val initialState = State(blocked = false, List(), None, None, List(), "/", None, None, SortOrdering.NameAscending, List(), None, List(), Map())


  case class ReceiveEvent(eventType: String, eventParameters: Map[String, String]) extends DiodeAction

  case object Block extends DiodeAction
  case object Unblock extends DiodeAction
  case object Refresh extends DiodeAction

  case class PushPath(folder: String) extends DiodeAction
  case class PopPath(amount: Int = 1) extends DiodeAction
  case object PopToHome extends DiodeAction

  case object DeleteItem extends DiodeAction
  case class DownloadItem(circuit: Circuit[_]) extends DiodeAction
  case object DuplicateItem extends DiodeAction
  case class EditItemInfo(parameterValues: HMap[Parameter.Values]) extends DiodeAction
  case object ShareItem extends DiodeAction
  case object CompressItem extends DiodeAction
  case object DecompressItem extends DiodeAction

  case object RefreshSharingContent extends DiodeAction
  case class DownloadContent(id: String) extends DiodeAction

  case class NewFolder(name: String) extends DiodeAction

  case object SortFolder extends DiodeAction
  case class FilterFolder(query: Option[String], tag: Option[FilterItem]) extends DiodeAction

  case class UpdateContent(content: Map[String, String]) extends DiodeAction
  case class UpdateFiles(files: List[HaranaFile]) extends DiodeAction
  case class UpdateItem(item: Option[HaranaFile]) extends DiodeAction
  case class UpdateItemPreview(itemPreview: Option[Either[String, PreviewData]]) extends DiodeAction
  case class UpdatePath(path: List[String]) extends DiodeAction
  case class UpdateSearchQuery(searchQuery: Option[String]) extends DiodeAction
  case class UpdateSelectedFile(file: Option[HaranaFile]) extends DiodeAction
  case class UpdateSortOrdering(ordering: SortOrdering) extends DiodeAction
  case class UpdateTags(tags: Map[String, Int]) extends DiodeAction
  case class UpdateTag(tag: Option[FilterItem]) extends DiodeAction
  case class UpdateUploadedFiles(files: List[UploadedFile]) extends DiodeAction

  val nameParameter = StringParameter("name", required = true)

}