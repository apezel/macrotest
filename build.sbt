organization := "com.apyx"

name := "MacroTest"

version := "1.0"

scalaVersion := "2.11.0-SNAPSHOT"

mainClass := Some("com.apyx.macrotest.Main")

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % "2.11.0-SNAPSHOT"
)

resolvers += Resolver.sonatypeRepo("snapshots")

addCompilerPlugin("org.scala-lang.plugins" % "macro-paradise" % "2.0.0-SNAPSHOT" cross CrossVersion.full)

