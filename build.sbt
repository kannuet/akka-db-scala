name := "akka-db-scala"
organization := "com.akka-db"
version := "0.0.1-SNAPSHOT"
scalaVersion := "2.12.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.12",
  "com.typesafe.akka" %% "akka-testkit" % "2.4.12" % "test",
  "com.typesafe.akka" %% "akka-remote" % "2.4.12",
  "org.scalactic" %% "scalactic" % "3.0.0",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test"
)

mappings in(Compile, packageBin) ~= {
  _.filterNot {
    case (_, name) => Seq("application.conf").contains(name)
  }
}