package com.kingjoshdavid.silly

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, ObjectInput, ObjectInputStream, ObjectOutputStream}
import java.lang.reflect.Field

class NothingIsThere extends Serializable {
  var nothing: Nothing = _
}
class VoidIsThere extends Serializable {
  var void: Void = _
}

object NothingIsThereRunner extends App {
  {
    val holder = new VoidIsThere()

    val baos = new ByteArrayOutputStream()
    val oos = new ObjectOutputStream(baos)
    oos.writeObject(holder)
    val array: Array[Byte] = baos.toByteArray
    println(new String(array))
    println(holder.void)
  }
  {
    val holder = new NothingIsThere()

    val baos = new ByteArrayOutputStream()
    val oos = new ObjectOutputStream(baos)
    oos.writeObject(holder)
    val array: Array[Byte] = baos.toByteArray
    println(new String(array))
    val nothingField: Field = classOf[NothingIsThere].getDeclaredField("nothing")
    nothingField.setAccessible(true)
    //    println(holder.nothing)
    val what = nothingField.get(holder)
    println(what)
  }

}
