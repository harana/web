import sbt._
import com.harana.sbt.common._

val app = haranaProject("app").in(file("."))
  .settings(
    libraryDependencies ++=
      Library.zio2.value ++
      Library.circe.value :+
      Library.upickle.value :+
      "com.harana" %%% "id-jwt" % "1.0.0" :+
      "com.harana" %%% "modules" % "1.0.0" :+
      "com.harana" %%% "modules-core" % "1.0.0" :+
      "com.harana" %%% "sdk" % "1.0.0" :+
      "com.harana" %%% "web" % "1.0.0"
  )