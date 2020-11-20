package foo


object FooRunner extends App {
  println(Foo.message)
}

object Foo {
  val message = "Hello World"
}