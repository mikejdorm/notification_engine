name := "notification_engine"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "com.twilio.sdk" % "twilio-java-sdk" % "3.4.5",
  "mysql" % "mysql-connector-java" % "5.1.10"
)     

play.Project.playJavaSettings
