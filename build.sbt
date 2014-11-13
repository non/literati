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
  (file(".") / "slides" * "*.md").get.map { f =>
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
