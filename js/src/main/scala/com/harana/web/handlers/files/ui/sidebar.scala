package com.harana.web.handlers.files.ui

import com.harana.web.handlers.files.FilesStore._
import com.harana.web.components.elements.Dialog
import com.harana.web.components.sidebar._
import com.harana.web.handlers.user.UserStore
import com.harana.web.pages.filterSection
import com.harana.web.utils.i18nUtils.ops
import diode.Circuit
import slinky.core.facade.ReactRef
import slinky.web.html.div

object sidebar {

  def home(circuit: Circuit[_], filesState: State, userState: UserStore.State, ref: ReactRef[Dialog.Def]) =
    Sidebar(
      List(
        SidebarSection(title = Some("Buckets"), content = bucketSection(filesState)),
        SidebarSection(title = Some("Search"), content = SearchSection(onSearch = (search: Option[String]) => ())),
        filterSection(i"common.sidebar.tags", true, filesState.tags, filesState.tag, tag => circuit.dispatch(UpdateTag(tag))),
      )
    )
  
  def bucketSection(filesState: State) =
    div()

//  def sharingSection =
//    SidebarSection(title = Some("Sharing"), content = SwitchSection(List(
//      Switch(
//        label = "File Sharing",
//        checked = userState.settings.exists(_.fileSharingEnabled),
//        onClick = checked =>
//          if (checked)
//            Circuit.dispatch(
//              ActionBatch(
//                UpdateSettings(userState.settings.map(_.copy(fileSharingEnabled = true, fileSharingUsername = Some(Random.long), fileSharingPassword = Some(Random.long)))),
//                SaveSettings,
//                RefreshSharingContent
//              )
//            )
//          else
//            Circuit.dispatch(
//              ActionBatch(
//                UpdateSettings(userState.settings.map(_.copy(fileSharingEnabled = false, fileSharingUsername = None, fileSharingPassword = None))),
//                SaveSettings,
//                RefreshSharingContent
//              )
//            ),
//        rightElement = Some(IconButton(library = Some("icomoon"), name = "info", className = Some("switch-icon"), onClick = Some(_ => dialogs.mountDrive(dialogRef, filesState))))
//      ),
//      Switch(
//        label = "Remote Login",
//        checked = userState.settings.exists(_.remoteLoginEnabled),
//        onClick = checked =>
//          if (checked)
//            Circuit.dispatch(
//              ActionBatch(
//                UpdateSettings(userState.settings.map(_.copy(remoteLoginEnabled = true, remoteLoginUsername = Some(Random.long), remoteLoginPassword = Some(Random.long)))),
//                SaveSettings,
//                RefreshSharingContent
//              )
//            )
//          else
//            Circuit.dispatch(
//              ActionBatch(
//                UpdateSettings(userState.settings.map(_.copy(remoteLoginEnabled = false, remoteLoginUsername = None, remoteLoginPassword = None))),
//                SaveSettings,
//                RefreshSharingContent
//              )
//            ),
//        rightElement = Some(
//          div(className := "switch-div")
//          (IconButton(library = Some("icomoon"), name = "info", className = Some("switch-icon"), onClick = Some(_ => dialogs.connectViaSFTP(dialogRef))))
//        )
//      )
//    )))

}