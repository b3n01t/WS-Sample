name := "websockets"

version := "1.0"

scalaVersion := "2.11.7"
organization  := "da.sh"

ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }

resolvers += "Spray" at "http://repo.spray.io"
//resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"
resolvers += "typesafe" at "http://repo.typesafe.com/typesafe/releases/"

//libraryDependencies ++= {
//  val akkaV  = "2.3.10"
//  val sprayV = "1.3.3"
//  val kamonV = "0.3.5"
//  Seq(
//    "com.wandoulabs.akka" %%  "spray-websocket"       % "0.1.4",
//    "io.spray"            %%  "spray-json"            % "1.3.1",
//    "io.spray"            %%  "spray-can"             % sprayV,
//    "io.spray"            %%  "spray-routing"         % sprayV,
//    "com.typesafe.akka"   %%  "akka-actor"            % akkaV,
//    "com.typesafe.akka"   %%  "akka-slf4j"            % akkaV,
//
//    "com.typesafe.akka"   %%  "akka-testkit"          % akkaV    % "test",
//    "io.spray"            %%  "spray-testkit"         % sprayV   % "test",
//    "org.scalatest"       %%  "scalatest"             % "2.2.4"  % "test",
//    "junit"               %   "junit"                 % "4.12"   % "test",
//    "org.specs2"          %%  "specs2"                % "2.4.17" % "test"  // until spray-testkit gets compiled against specs 3.3
//  )
//}
libraryDependencies ++= {
  val akkaV = "2.3.10"
  val sprayV = "1.3.3"
  Seq(
    "com.wandoulabs.akka" %%  "spray-websocket"  % "0.1.4",
    "io.spray"            %%  "spray-can"     % sprayV,
    "io.spray"            %%  "spray-routing" % sprayV,
    "io.spray"            %%  "spray-client" % sprayV,
    "io.spray"            %%  "spray-json" % "1.3.2",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test",
    "org.specs2"          %%  "specs2-core"   % "2.3.11" % "test"
  )
}