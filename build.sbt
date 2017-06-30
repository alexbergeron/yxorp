import scalariform.formatter.preferences._
import com.typesafe.sbt.SbtScalariform
import com.typesafe.sbt.SbtScalariform.ScalariformKeys

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "io.github.alexbergeron",
      scalaVersion := "2.12.1",
      scalaOrganization in ThisBuild := "org.typelevel",
      version      := "0.1.0-SNAPSHOT",
      resolvers ++= Seq(
        "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
      ),
      scalacOptions ++= Seq(
          "-target:jvm-1.8",
          "-deprecation",
          "-encoding", "UTF-8",
          "-feature",
          "-language:existentials",
          "-language:higherKinds",
          "-language:implicitConversions",
          "-unchecked",
          "-Ywarn-unused-import",
          "-Ywarn-nullary-unit",
          "-Xfatal-warnings",
          "-Xlint",
          //"-Yinline-warnings",
          "-Ywarn-dead-code",
          "-Xfuture")
    )),
    SbtScalariform.scalariformSettings,
    ScalariformKeys.preferences := ScalariformKeys.preferences.value
      .setPreference(AlignSingleLineCaseStatements, true)
      .setPreference(DoubleIndentClassDeclaration, true)
      .setPreference(RewriteArrowSymbols, true),
    name := "xyrop",
    libraryDependencies ++= Seq(
      "com.chuusai" %% "shapeless" % "2.3.2",
      "org.typelevel" %% "cats" % "0.9.0",
      "org.scodec" %% "scodec-core" % "1.10.3",
      "io.fabric8" % "kubernetes-client-project" % "2.4.1",

      "org.scalatest" %% "scalatest" % "3.0.1" % "test",
      "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
    )
  )

