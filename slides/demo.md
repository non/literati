## Demo

This is a test of the *literate Scala* machinery.  This should end up
in `target/scala-2.11/src_managed/main/foo.scala` as a comment.

### Code Examples

These should be compiled as actual, working Scala code. Here are some
imports to get out of the way.

```scala
import scala.{specialized => sp}
import spire.algebra._
import spire.implicits._
import spire.math._
```

*Whew!* Now that that's settled, let's write some code!

```scala
def pythagorean[A: Field: NRoot](x: A, y: A): A =
  (x ** 2 + y ** 2).sqrt
  
def quadratic[A: Field: NRoot](a: A, b: A, c: A): (A, A) = {
  val q = (b ** 2 - 4 * a * c).sqrt
  val t = 2 * a
  ((-b + q) / t, (-b - q) / t)
}
```

Awesome!
