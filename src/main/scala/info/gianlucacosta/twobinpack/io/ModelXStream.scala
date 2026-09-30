package info.gianlucacosta.twobinpack.io

import java.util.UUID

import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.io.xml.StaxDriver
import com.thoughtworks.xstream.security.{NoTypePermission, NullPermission, PrimitiveTypePermission}
import info.gianlucacosta.twobinpack.core._

/**
  * Dedicated XStream object providing cleaner XML files and strict security checks
  */
private object ModelXStream extends XStream(new StaxDriver) {
  initSecurity()
  initAliases()


  private def initSecurity(): Unit = {
    addPermission(NoTypePermission.NONE)

    addPermission(NullPermission.NULL)
    addPermission(PrimitiveTypePermission.PRIMITIVES)

    allowTypesByWildcard(Array(
      "info.gianlucacosta.twobinpack.**",
      "scala.collection.**",
      "java.time.**"
    ))


    allowTypes(Array[Class[_]](
      Class.forName("scala.Some"),
      None.getClass,

      Class.forName("javafx.scene.paint.Color"),
      Class.forName("scalafx.scene.paint.Color"),

      classOf[UUID]
    ))

    registerConverter(new DurationConverter)
  }


  private def initAliases(): Unit = {
    alias("bundle", classOf[ProblemBundle])

    alias("problem", classOf[Problem])

    alias("frameDimension", classOf[FrameDimension])
    alias("blockDimension", classOf[BlockDimension])

    alias("point", classOf[QuantizedPoint2D])


    alias("some", Class.forName("scala.Some"))
    alias("none", None.getClass)

    alias("color", Class.forName("javafx.scene.paint.Color"))
    alias("fxColor", Class.forName("scalafx.scene.paint.Color"))

    alias("uuid", classOf[UUID])
  }
}
