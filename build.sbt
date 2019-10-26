name := "aws"

version := "0.1"

scalaVersion := "2.13.1"

val dockerClientV = "8.14.1"

libraryDependencies += "com.amazonaws" % "aws-java-sdk" % "1.11.658"
libraryDependencies += "com.spotify"   % "docker-client" % dockerClientV
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"
