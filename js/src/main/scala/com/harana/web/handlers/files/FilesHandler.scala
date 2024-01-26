package com.harana.web.handlers.files

import com.harana.web.actions.Init
import com.harana.sdk.shared.PreviewData
import com.harana.sdk.shared.models.HaranaFile
import com.harana.web.components.{LinkType, openLink}
import com.harana.web.handlers.files.FilesStore._
import com.harana.web.handlers.user.UserStore.SetPreference
import com.harana.web.pages.FilterItem
import com.harana.web.pages.grid.CaseInsensitiveOrdering
import com.harana.web.pages.grid.SortOrdering._
import com.harana.web.utils.FileUtils
import com.harana.web.utils.http.Http
import diode.AnyAction.aType
import diode._
import io.circe.syntax._
import org.scalajs.macrotaskexecutor.MacrotaskExecutor.Implicits._

import java.time.Instant
import scala.concurrent.Future

class FilesHandler[S](state: ModelRW[S, FilesStore.State]) extends ActionHandler(state) {

  private val pathPreferenceId = "designer.files.path"

  override def handle = {

    case Init(preferences) =>
      effectOnly(
        Effect.action(RefreshSharingContent) +
        Effect(Http.getRelativeAs[Map[String, Int]](s"/api/files/tags").map(tags => UpdateTags(tags.getOrElse(Map())))) +
          Effect.action(
            preferences.get(pathPreferenceId) match {
              case Some(path) => UpdatePath(path.split("/").toList)
              case None => UpdatePath(List())
            }
          )
      )


    case Block =>
      updated(value.copy(blocked = true))


    case Unblock =>
      updated(value.copy(blocked = false))


    case ReceiveEvent(eventType, eventParameters) =>
      noChange


    case PushPath(folder) =>
      effectOnly(Effect.action(UpdatePath(value.path :+ folder)))


    case PopPath(amount) =>
      effectOnly(Effect.action(UpdatePath(value.path.dropRight(value.path.length - amount))))


    case PopToHome =>
      effectOnly(Effect.action(UpdatePath(List())))


    case Refresh =>
      effectOnly(
        Effect.action(Block) >>
        Effect(
          Http.getRelativeAs[HaranaFile](s"/api/files/info?path=${value.pathStr}").flatMap { item =>
            if (item.nonEmpty) {
              item match {
                case item if item.get.isFolder => Http.getRelativeAs[List[HaranaFile]](s"/api/files?path=${value.pathStr}").map(files => ActionBatch(UpdateItem(item), UpdateFiles(files.getOrElse(List()))))
                case item if FileUtils.isTabular(item.get) => Http.getRelativeAs[PreviewData](s"/api/files/preview?path=${value.pathStr}").map(file => ActionBatch(UpdateItem(item), UpdateItemPreview(file.map(Right(_)))))
                case _ => Http.getRelative(s"/api/files/preview?path=${value.pathStr}").map(file => ActionBatch(UpdateItem(item), UpdateItemPreview(file.map(Left(_)))))
              }
            } else
              Future.successful(UpdateItem(item))
          }
        ) >>
        Effect.action(Unblock)
    )


    case NewFolder(name) =>
      val directory = HaranaFile(name, "", None, isFolder = true, Instant.now, Instant.now, 0, List())

      effectOnly(
        Effect.action(Block) >>
        Effect(Http.postRelative(s"/api/files/directory?path=${value.pathStr}/$name", body = "").map(_ => NoAction)) >>
        Effect.action(UpdateFiles(value.files :+ directory)) >>
        Effect.action(Unblock)
      )


    case DeleteItem =>
      effectOnly(
        Effect.action(Block) >>
        Effect(Http.deleteRelative(s"/api/files?path=${value.pathStr}/${value.selectedFile.get.name}").map(_ => NoAction)) >>
        Effect.action(UpdateFiles(value.files.filterNot(_.equals(value.selectedFile.get)))) >>
        Effect.action(Unblock)
      )


    case DownloadItem(circuit: Circuit[_]) =>
      openLink(circuit, LinkType.Url(s"/api/files/download?path=${value.pathStr}/${value.selectedFile.get.name}"))
      noChange


    case DuplicateItem =>
      effectOnly(
        Effect(Http.getRelative(s"/api/files/duplicate?path=${value.pathStr}/${value.selectedFile.get.name}").map(_ => NoAction)) >>
        Effect.action(Refresh)
      )


    case CompressItem =>
      effectOnly(
        Effect(Http.getRelative(s"/api/files/compress?path=${value.pathStr}/${value.selectedFile.get.name}").map(_ => NoAction)) >>
        Effect.action(Refresh)
      )


    case DecompressItem =>
      effectOnly(
        Effect(Http.getRelative(s"/api/files/decompress?path=${value.pathStr}/${value.selectedFile.get.name}").map(_ => NoAction)) >>
        Effect.action(Refresh)
      )


    case EditItemInfo(values) =>
      val newFile = value.item.get.copy(name = values.getOrElse(nameParameter, ""))

      effectOnly(
        Effect(Http.postRelative(s"/api/files/info?path=${value.pathStr}/${value.selectedFile.get.name}", List(), newFile.asJson.noSpaces).map(_ => NoAction)) >>
        Effect.action(Refresh)
      )


    case ShareItem =>
      noChange


    case SortFolder =>
      value.sortOrdering match {
        case NameAscending => updated(value.copy(files = value.files.sortBy(_.name)(CaseInsensitiveOrdering)))
        case NameDescending => updated(value.copy(files = value.files.sortBy(_.name)(CaseInsensitiveOrdering).reverse))
        case SizeAscending => updated(value.copy(files = value.files.sortBy(_.size)))
        case SizeDescending => updated(value.copy(files = value.files.sortBy(_.size)(Ordering[Long].reverse)))
        case CreatedAscending => updated(value.copy(files = value.files.sortBy(_.created)))
        case CreatedDescending => updated(value.copy(files = value.files.sortBy(_.created)(Ordering[Instant].reverse)))
        case UpdatedAscending => updated(value.copy(files = value.files.sortBy(_.updated)))
        case UpdatedDescending => updated(value.copy(files = value.files.sortBy(_.updated)(Ordering[Instant].reverse)))
      }


    case FilterFolder(query, tag) =>
      val url = (query, tag) match {
        case (Some(q), Some(t)) => s"/api/files/search/$q?tag=${t.title}&?path=${value.pathStr}"
        case (Some(q), None) => s"/api/files/search/$q?path=${value.pathStr}"
        case (None, Some(t)) => s"/api/files?tag=$t&path=${value.pathStr}"
        case (None, None) => s"/api/files?path=${value.pathStr}"
      }
      effectOnly(
        Effect.action(Block) >>
          Effect(Http.getRelativeAs[List[HaranaFile]](url).map(data => UpdateFiles(data.getOrElse(List())))) >>
          Effect.action(Unblock)
      )


    case RefreshSharingContent =>
      effectOnly(
        Effect.action(DownloadContent("files.mount-drive.linux")) >>
        Effect.action(DownloadContent("files.mount-drive.mac")) >>
        Effect.action(DownloadContent("files.mount-drive.windows"))
      )


    case DownloadContent(id) =>
      effectOnly(Effect(Http.getRelative(s"/api/content/$id").map(content =>
        if (content.nonEmpty) UpdateContent(value.content + (id -> content.get)) else NoAction
      )))


    case UpdateContent(content) =>
      updated(value.copy(content = content))


    case UpdateFiles(files) =>
      updated(value.copy(files = files), Effect.action(SortFolder))


    case UpdateItem(item) =>
      updated(value.copy(item = item))


    case UpdateItemPreview(itemPreview) =>
      updated(value.copy(itemPreview = itemPreview))


    case UpdatePath(path) =>
      updated(
        value.copy(path = path, pathStr = s"/${path.mkString("/")}"),
        Effect.action(Refresh) + Effect.action(SetPreference(pathPreferenceId, Some(path.mkString("/"))))
      )


    case UpdateSearchQuery(searchQuery) =>
      updated(value.copy(searchQuery = searchQuery), Effect.action(FilterFolder(searchQuery, value.tag)))


    case UpdateSelectedFile(file) =>
      updated(value.copy(selectedFile = file))


    case UpdateSortOrdering(sortOrdering) =>
      updated(value.copy(sortOrdering = sortOrdering), Effect.action(SortFolder))


    case UpdateTag(tag) =>
      updated(value.copy(tag = tag), Effect.action(FilterFolder(value.searchQuery, tag)))


    case UpdateTags(tags) =>
      val newTags = tags.map { case (k, v) => FilterItem(k, v, None) }
      updated(value.copy(tags = newTags.toList))


    case UpdateUploadedFiles(files) =>
      //DesignerApp.analytics.fileUpload()
      updated(value.copy(uploadedFiles = value.uploadedFiles ++ files))
  }
}