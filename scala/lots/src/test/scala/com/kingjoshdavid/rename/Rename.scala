package com.kingjoshdavid.rename

class Rename {
  def foo(s: String): Unit = {
    println(s)
    println(Rename.VARIABLE)
  }
}

object Rename {
  val VARIABLE = "Hello"
}
