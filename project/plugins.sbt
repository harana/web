resolvers += Resolver.url("harana", url("https://maven.pkg.github.com/harana/sbt-plugin"))(Patterns("[organisation]/[module]/[revision]/[artifact].[ext]") )
addSbtPlugin("com.harana" % "sbt_js_jvm" % "1.0.0-dirty-SNAPSHOT")
