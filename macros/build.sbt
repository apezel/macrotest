scalaVersion := "2.11.0-M5"

name := "macros"

version := "1.0"

organization := "com.apyx"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % "2.11.0-M5"
)

resolvers += Resolver.sonatypeRepo("snapshots")

addCompilerPlugin("org.scala-lang.plugins" % "macro-paradise" % "2.0.0-SNAPSHOT" cross CrossVersion.full)

scalacOptions ++= Seq(
    "-deprecation",
    "-unchecked",
    "-feature",
    "-encoding", "utf8"
)