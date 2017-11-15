logLevel := Level.Warn

resolvers += Resolver.sonatypeRepo("releases")

addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.8.0")

addSbtPlugin("io.get-coursier" % "sbt-coursier" % "1.0.0-RC11")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.1")
