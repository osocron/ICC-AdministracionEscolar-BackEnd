name := "ICC_Administracion_Escolar_BackEnd"
 
version := "1.0" 
      
lazy val `icc_administracion_escolar_backend` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
      
scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  jdbc , ehcache , ws , specs2 % Test , guice,
  "mysql" % "mysql-connector-java" % "5.1.38",
  "com.typesafe.play" %% "play-slick" % "3.0.0")

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

      