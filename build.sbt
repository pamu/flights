val scalaV = "2.11.8"

//============== Client ================
//======================================

val diodeVersion = "1.1.2"

lazy val client = (project in file("client")).settings(
  scalaVersion := scalaV,
  scalaJSUseMainModuleInitializer := true,
  libraryDependencies ++= Seq(
    "org.scala-js"                      %%% "scalajs-dom"    % "0.9.1",
    "com.github.japgolly.scalajs-react" %%% "core"           % "1.0.0",
    "com.github.japgolly.scalajs-react" %%% "extra"          % "1.0.0",
    "io.suzaku"                         %%% "diode"          % diodeVersion,
    "io.suzaku"                         %%% "diode-devtools" % diodeVersion,
    "io.suzaku"                         %%% "diode-react"    % diodeVersion,
    "io.suzaku"                         %%% "boopickle"      % "1.2.6",
    "org.webjars" % "materializecss" % "0.97.0" //css
  )
).enablePlugins(ScalaJSPlugin, ScalaJSWeb).
  dependsOn(sharedJs)

//============== Shared code between server and client ==============
//===================================================================

lazy val shared = (crossProject.crossType(CrossType.Pure) in file("shared")).
  settings(
    scalaVersion := scalaV,
    libraryDependencies ++= Seq(
      "joda-time" % "joda-time" % "2.9.9"
    )
  ).jsConfigure(_ enablePlugins ScalaJSWeb)

lazy val sharedJvm = shared.jvm
lazy val sharedJs = shared.js

//====================== Server ===========================
//=========================================================

lazy val server = (project in file("server")).settings(
  scalaVersion := scalaV,
  scalaJSProjects := Seq(client),
  pipelineStages in Assets := Seq(scalaJSPipeline),
  pipelineStages := Seq(digest, gzip),
  // triggers scalaJSPipeline when using compile or continuous compilation
  compile in Compile := ((compile in Compile) dependsOn scalaJSPipeline).value,
  libraryDependencies ++= Seq(
    "com.vmunier" %% "scalajs-scripts" % "1.0.0",
    specs2 % Test
  ),
  // Compile the project before generating Eclipse files, so that generated .scala or .class files for views and routes are present
  EclipseKeys.preTasks := Seq(compile in Compile)
).enablePlugins(PlayScala)
  .dependsOn(sharedJvm)

//========================== Load server by default when sbt is started on command line ====================
// loads the server project at sbt startup
onLoad in Global := (Command.process("project server", _: State)) compose (onLoad in Global).value