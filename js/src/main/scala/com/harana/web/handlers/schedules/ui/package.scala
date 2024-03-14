package com.harana.web.handlers.schedules

import com.harana.sdk.shared.models.schedules.ScheduleExecutionStatus
import com.harana.web.external.shoelace.Icon

package object ui {

  def statusIcon(status: ScheduleExecutionStatus, className: Option[String] = None) =
    if (status == ScheduleExecutionStatus.Succeeded)
      Icon(library = Some("icomoon"), name = "checkmark-circle", className = Some(s"schedule-success ${className.getOrElse("")}"))
    else
      Icon(library = Some("icomoon"), name = "cancel-circle2", className = Some(s"schedule-failure ${className.getOrElse("")}"))

}
