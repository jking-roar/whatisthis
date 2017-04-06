package com.kingjoshdavid.rename

class Usages {
  def wow(): Unit = {
    val x = Rename.VARIABLE
    val y: Rename = new Rename()
    y.foo(Rename.VARIABLE + x)

  }
}
