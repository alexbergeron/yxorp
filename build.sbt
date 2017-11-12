import scalariform.formatter.preferences._
import com.typesafe.sbt.SbtScalariform
import com.typesafe.sbt.SbtScalariform.ScalariformKeys

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "io.github.alexbergeron",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT",
      resolvers ++= Seq(
        "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
      ),
      scalacOptions ++= Seq(
        "-target:jvm-1.8",
        "-deprecation",           
        "-encoding", "UTF-8",       // yes, this is 2 args
        "-feature",                
        "-language:existentials",
        "-language:higherKinds",
        "-language:implicitConversions",
        "-unchecked",
        "-Xfatal-warnings",       
        "-Xlint",
        "-Yno-adapted-args",       
        "-Ywarn-dead-code",        // N.B. doesn't work well with the ??? hole
        "-Ywarn-numeric-widen",   
        "-Ywarn-value-discard",
				"-Xfuture",
				"-Ywarn-unused-import"     // 2.11 only
		)
	)),
	ScalariformKeys.preferences := ScalariformKeys.preferences.value
		.setPreference(AlignSingleLineCaseStatements, true)
		.setPreference(DanglingCloseParenthesis, Force)
		.setPreference(DoubleIndentConstructorArguments, true)
		.setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk, true)
		.setPreference(RewriteArrowSymbols, true),
	name := "yxorp",
	libraryDependencies ++= Seq(
    "com.github.finagle" %% "finch-core" % "0.16.0-M3",
    //"com.softwaremill.macwire" %% "macros" % "2.3.0",
    "com.squareup.okhttp3" % "okhttp" % "3.9.0",
		//"org.typelevel" %% "cats-core" % "1.0.0-MF",
		//"org.typelevel" %% "cats-free" % "1.0.0-MF",
    //"org.typelevel" %% "cats-effect" % "0.4",
		"io.fabric8" % "kubernetes-client" % "2.6.2",
    //"io.monix" %% "monix" % "3.0.0-M1",
    "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.6",

		//"org.scalacheck" %% "scalacheck" % "1.13.4" % "test",
		//"org.scalatest" %% "scalatest" % "3.0.1" % "test"
	)
)

