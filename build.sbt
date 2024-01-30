import sbt._
import com.harana.sbt.common._

val web = haranaCrossProject("web").in(file("."))
  .settings(
    libraryDependencies ++=
      Library.circe.value :+
      Library.upickle.value :+
      "com.harana" %%% "sdk" % "1.0.0"
  )
  .jsSettings(
    libraryDependencies ++=
      Library.scalajs.value ++
      Library.slinky.value ++
      Library.sttp.value :+
      Library.sttpQuicklens.value :+
      Library.scalablyTyped.value :+
      Library.scalajsJavaTime.value
  )
  .jvmSettings(
    unmanagedBase := baseDirectory.value / "lib"
  )

val root = haranaRootProject(web)