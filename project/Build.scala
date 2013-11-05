import sbt._
import Keys._

object MacroBuild extends Build {
   lazy val main = Project("main", file(".")) dependsOn(macros)
   lazy val macros = Project("macros", file("macros"))
}