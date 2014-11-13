## Literati

This is a simple project. The idea is to build slides which work with
[Remark.js](https://github.com/gnab/remark) or
[Deckset](http://www.decksetapp.com/), and then use a code-generator
within [SBT](http://www.scala-sbt.org/) to make sure your code
examples compile.

The goal here is to support *literate* Scala programming from within
Markdown documents, hence the name of this project.

### Motivation

While preparing for [PNWScala](http://pnwscala.org/2014/index.html) I
realized that it would be nice to make sure all my code examples
compiled. I also wanted to easily load my examples into the REPL to
play with interactively. I decided that it would be easy enough to
generate valid Scala source files by transforming the
[Markdown](http://daringfireball.net/projects/markdown/syntax) slides.

(You might also recognize building a general framework to support
writing slides as a clever way to procrastinate on writing
slides. Guilty as charged.)

You should easily be able to fork this project to help prepare your
own Scala slides.

### Usage

The expectation is that you'll want to fork this repostiory and start
editing it for your own talk. Here is a handy step-by-step guide of
what you can do:

1. Edit `build.sbt` to add any library dependencies you need.
2. Create your slides:
  * Slides should go in the `slides/` directory.
  * File names should end in `.md`.
  * (This is the time-consuming part.)
3. Launch SBT
4. Run `render` to construct HTML slides
  * Every `xyz.md` file is transformed into `html/xyz.html`.
  * Open these HTML files in your browser to start a presentation.
5. Run `compile` to compile your code.
  * Generated code for `xyz.md` is located at `code/xyz.scala`.
6. Run `console` to launch a REPL:
  * Every `xyz.md` file is translated to an `xyz` object.
  * Use `import xyz._` to import methods from `xyz.md`
  * Top-level code will run when the `xyz` object is referenced.

(Eventually, it might be nice to support this kind of code-generation
via an SBT plugin. If that's something you're interested in working
on, please get in touch!)

### Gripes

I got interested in this whole workflow via Deckset, which makes it
easy to create really excellent-looking slides from very simple
Markdown files. Despite being a closed-source application, I was
prepared to admit that it was better than anything else available.

However, I'm really not happy with Deckset's ability to
syntax-highlight Scala. Since it uses [highlight.js](https://highlightjs.org/) I figured it
would be a simple matter to patch that library and improve my slides.
(And in fact [I did so](https://github.com/isagalaev/highlight.js/pull/475).)

However, Deckset does not give users the ability to define (or
improve) syntax highlighting support. And due to Apple's ridiculous
application-signing, it is impossible to manually-patch
`Deckset.app/Contents/Resources/highlight.pack.js` with a custom
build. *Ugh*.

Fortunately, remark.js seems to make it just as easy to define slides,
and while the text doesn't end up looking as nice as in Deckset, the
code examples are *waaaay* better.

### Copyright and License

All code is available to you under the MIT license, available at
http://opensource.org/licenses/mit-license.php and also in the
[COPYING](COPYING) file.

Copyright Erik Osheim, 2014.
