import scalariform.formatter.preferences._
import com.typesafe.sbt.SbtScalariform
import com.typesafe.sbt.SbtScalariform.ScalariformKeys

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "io.github.alexbergeron",
      scalaVersion := "2.12.3",
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
	name := "xyrop",
	libraryDependencies ++= Seq(
		"com.typesafe.akka" %% "akka-http" % "10.0.9",
		"org.typelevel" %% "cats" % "0.9.0",
		"io.fabric8" % "kubernetes-client-project" % "2.4.1",
		"org.scodec" %% "scodec-core" % "1.10.3",
		"com.chuusai" %% "shapeless" % "2.3.2",
    "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.6",

		"org.scalacheck" %% "scalacheck" % "1.13.4" % "test",
		"org.scalatest" %% "scalatest" % "3.0.1" % "test"
	)
)

