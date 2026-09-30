package info.gianlucacosta.twobinpack.io

import java.time.Duration

import com.thoughtworks.xstream.converters.{Converter, MarshallingContext, UnmarshallingContext}
import com.thoughtworks.xstream.io.{HierarchicalStreamReader, HierarchicalStreamWriter}

class DurationConverter extends Converter {
  override def marshal(source: scala.Any, writer: HierarchicalStreamWriter, context: MarshallingContext): Unit = {
    val duration =
      source.asInstanceOf[Duration]

    writer.setValue(duration.getSeconds.toString)
  }

  override def unmarshal(reader: HierarchicalStreamReader, context: UnmarshallingContext): AnyRef = {
    val seconds =
      reader.getValue.toLong

    Duration.ofSeconds(seconds)
  }

  override def canConvert(targetClass: Class[_]): Boolean =
    targetClass == classOf[Duration]
}
