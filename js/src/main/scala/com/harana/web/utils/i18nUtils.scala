package com.harana.web.utils

import com.harana.web.InitialState
import org.scalajs.dom.window.atob
import upickle.default._

import scala.util.Try

object i18nUtils {

  val properties = Try(read[List[String]](atob(InitialState.i18n))).getOrElse(List())

  implicit class ops(sc: StringContext) {

    def i(subs: Any*): String = getProperty(subs: _*) match {
      case Left(x) =>
        //println(s"Failed to get i18n for key: $x")
        ""
      case Right(x) => x
    }

    def io(subs: Any*): Option[String] =
      getProperty(subs: _*).toOption

    private def getProperty(subs: Any*): Either[String, String] = {
      val sb = new java.lang.StringBuilder
      val pit = sc.parts.iterator
      val sit = subs.iterator
      sb.append(pit.next())
      while (sit.hasNext) {
        sb.append(sit.next().toString)
        sb.append(pit.next())
      }
      val str = sb.toString.toLowerCase
      properties.find(_.startsWith(str)).map(_.replace(str, "").replace("=", "").trim) match {
        case None => Left(str)
        case Some(x) => Right(x)
      }
    }
  }
}