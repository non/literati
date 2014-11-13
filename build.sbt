name := "pnwscala-2014"

scalaVersion := "2.11.4"

libraryDependencies += "org.spire-math" %% "spire" % "0.8.2"

scalacOptions ++= Seq(
  "-Yinline-warnings",
  "-deprecation",
  "-unchecked",
  //"-optimize",
  "-feature",
  "-language:experimental.macros",
  "-language:higherKinds",
  "-language:implicitConversions")

sourceGenerators in Compile <+= sourceManaged in Compile map { dir =>
  (file("slides") * "*.md").get.map { f =>
    val s = f.getName
    val i = s.lastIndexOf(".")
    val base = if (i < 0) s else s.substring(0, i)
    val basename = base + ".scala"
    println(s"generating $basename")
    val output = IO.reader(f) { br =>
      val sb = new StringBuilder
      sb.append(s"object $base {\n")
      IO.foldLines(br, false) { (inc, line) =>
        if (inc) {
          if (line == "```") { sb.append("\n"); false }
          else { sb.append("  " + line + "\n"); true }
        } else {
          if (line == "```scala") { sb.append("\n"); true }
          else { sb.append("  // " + line + "\n"); false }
        }
      }
      sb.append("}\n")
      sb.toString
    }
    val dest = dir / basename
    IO.write(dest, output)
    dest
  }
}

lazy val render = taskKey[Unit]("Renders Markdown slides to HTML")

render := {
  val b1 = IO.read(file("guts") / "boiler1.html")
  val b2 = IO.read(file("guts") / "boiler2.html")
  (file("slides") * "*.md").get.foreach { f =>
    val s = f.getName
    val i = s.lastIndexOf(".")
    val base = if (i < 0) s else s.substring(0, i)
    val basename = base + ".html"
    println(s"rendering $basename")
    val md = IO.read(f)
    IO.write(file("html") / basename, b1 + "\n" + md + "\n" + b2)
  }
}
