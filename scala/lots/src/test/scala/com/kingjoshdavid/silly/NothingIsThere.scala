package com.kingjoshdavid.silly

import java.io.{ByteArrayOutputStream, ObjectOutputStream}

class NothingIsThere extends Serializable{
  var nothing: Nothing = _
}

object NothingIsThereRunner extends App {
  val nothingHolder = new NothingIsThere()

  val baos = new ByteArrayOutputStream()
  val oos = new ObjectOutputStream(baos)
  oos.writeObject(nothingHolder)
  private val array: Array[Byte] = baos.toByteArray
  println(new String(array))
}
