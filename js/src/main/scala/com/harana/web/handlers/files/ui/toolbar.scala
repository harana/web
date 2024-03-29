package com.harana.web.handlers.files.ui

import com.harana.web.pages.grid.SortOrdering.{CreatedAscending, CreatedDescending, NameAscending, NameDescending, SizeAscending, SizeDescending, UpdatedAscending, UpdatedDescending}
import com.harana.web.handlers.files.FilesStore._
import com.harana.web.components.elements.{Dialog, HeadingItem}
import com.harana.web.external.shoelace.{MenuDivider, MenuItem, MenuLabel}
import com.harana.web.utils.FileUtils
import com.harana.web.utils.i18nUtils.ops
import diode.Circuit
import slinky.core.facade.{ReactElement, ReactRef}

object toolbar {

  def sort(circuit: Circuit[_]) = HeadingItem.IconMenu(("icomoon", "sort-alpha-asc"), i"common.menu.sort", className = Some("heading-icon"), menuItems =
    List(
      MenuLabel(i"common.menu.sort.ascending").withKey("ascending"),
      MenuItem(i"common.menu.sort.ascending.name", iconPrefix = Some("lindua", "sort-alpha-asc"), onClick = Some(_ => circuit.dispatch(UpdateSortOrdering(NameAscending)))).withKey("ascending-name"),
      MenuItem(i"common.menu.sort.ascending.size", iconPrefix = Some("lindua", "sort-numeric-asc"), onClick = Some(_ => circuit.dispatch(UpdateSortOrdering(SizeAscending)))).withKey("ascending-size"),
      MenuItem(i"common.menu.sort.ascending.created", iconPrefix = Some("lindua", "sort-time-asc"), onClick = Some(_ => circuit.dispatch(UpdateSortOrdering(CreatedAscending)))).withKey("ascending-created"),
      MenuItem(i"common.menu.sort.ascending.updated", iconPrefix = Some("lindua", "sort-time-asc"), onClick = Some(_ => circuit.dispatch(UpdateSortOrdering(UpdatedAscending)))).withKey("ascending-updated"),

      MenuDivider().withKey("sort-divider"),

      MenuLabel(i"common.menu.sort.descending").withKey("descending"),
      MenuItem(i"common.menu.sort.descending.name", iconPrefix = Some("lindua", "sort-alpha-desc"), onClick = Some(_ => circuit.dispatch(UpdateSortOrdering(NameDescending)))).withKey("descending-name"),
      MenuItem(i"common.menu.sort.descending.size", iconPrefix = Some("lindua", "sort-numeric-desc"), onClick = Some(_ => circuit.dispatch(UpdateSortOrdering(SizeDescending)))).withKey("descending-size"),
      MenuItem(i"common.menu.sort.descending.created", iconPrefix = Some("lindua", "sort-time-desc"), onClick = Some(_ => circuit.dispatch(UpdateSortOrdering(CreatedDescending)))).withKey("descending-created"),
      MenuItem(i"common.menu.sort.descending.updated", iconPrefix = Some("lindua", "sort-time-desc"), onClick = Some(_ => circuit.dispatch(UpdateSortOrdering(UpdatedDescending)))).withKey("descending-updated")
    )
  )

  def create(circuit: Circuit[_], ref: ReactRef[Dialog.Def], state: State) =
    HeadingItem.IconMenu(("icomoon", "plus3"), i"files.menu.new", className = Some("heading-icon"), menuItems =
      List(
        MenuItem(
          label = i"files.menu.new.new-folder",
          iconPrefix = Some("lindua", "folder-plus"),
          onClick = Some(_ => dialogs.newFolder(circuit, ref))
        ).withKey("new-folder"),

        MenuItem(
          label = i"files.menu.new.upload-files",
          iconPrefix = Some("lindua", "upload"),
          onClick = Some(_ => dialogs.uploadFiles(circuit, ref, state))
        ).withKey("upload-files")
      )
    )

  def pathTree(circuit: Circuit[_], state: State) =
    HeadingItem.IconMenu(("icomoon", "arrow-up8"), i"files.menu.new", className = Some("heading-icon"), menuItems =
      List[ReactElement](
        MenuItem(
          label = i"files.menu.pathtree.home",
          iconPrefix = Some("icomoon", "home6"),
          onClick = Some(_ => circuit.dispatch(PopToHome))
        ).withKey("pathtree-home")
      ) ++ (
        if (state.item.nonEmpty && state.item.get.path.nonEmpty) {
          state.item.get.path.split("/").toList.filter(_.nonEmpty).dropRight(1).zipWithIndex.map { case (folder, index) =>
            MenuItem(
              label = folder,
              iconPrefix = Some("icomoon", "folder4"),
              onClick = Some(_ => circuit.dispatch(PopPath(index+1)))
            ).withKey(s"pathtree-$folder")
          }
        }
        else
          List.empty[ReactElement]
        )
    )

  def edit(circuit: Circuit[_], ref: ReactRef[Dialog.Def], state: State) =
    HeadingItem.IconMenu(("icomoon", "menu7"), i"files.menu.edit", className = Some("heading-icon"), enabled = state.selectedFile.nonEmpty, menuItems =
      if (state.selectedFile.isEmpty)
        List()
      else
        List(
          MenuItem(
            label = i"files.menu.edit.open",
            iconPrefix = Some("lindua", "file-open"),
            onClick = Some(_ => circuit.dispatch(PushPath(state.selectedFile.get.name)))
          ),

          MenuDivider(),

          MenuItem(
            label = i"files.menu.edit.edit-info",
            iconPrefix = Some("lindua", "file-pencil"),
            onClick = Some(_ => dialogs.editInfo(circuit, ref, state)),
            disabled = Some(state.selectedFile.get.isFolder)
          ),

          MenuItem(
            label = i"files.menu.edit.duplicate",
            iconPrefix = Some("lindua", "files"),
            onClick = Some(_ => circuit.dispatch(DuplicateItem)),
            disabled = Some(false)
          ),

          MenuItem(
            label = i"files.menu.edit.delete",
            iconPrefix = Some("lindua", "trash"),
            onClick = Some(_ => dialogs.deleteFiles(circuit, ref))
          ),

          MenuDivider(),

          MenuItem(
            label = i"files.menu.edit.download",
            iconPrefix = Some("lindua", "download"),
            onClick = Some(_ => circuit.dispatch(DownloadItem(circuit))),
            disabled = Some(state.selectedFile.get.isFolder)
          ),

          MenuDivider(),

          MenuItem(
            label = i"files.menu.edit.compress",
            iconPrefix = Some("lindua", "file-compressed"),
            onClick = Some(_ => circuit.dispatch(CompressItem)),
            disabled = Some(FileUtils.isArchive(state.selectedFile.get))
          ),
          MenuItem(
            label = i"files.menu.edit.decompress",
            iconPrefix = Some("lindua", "file-compressed"),
            onClick = Some(_ => circuit.dispatch(DecompressItem)),
            disabled = Some(!FileUtils.isArchive(state.selectedFile.get))
          )
        )
    )
  }
