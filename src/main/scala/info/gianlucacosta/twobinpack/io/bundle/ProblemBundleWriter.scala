package info.gianlucacosta.twobinpack.io.bundle

import java.io.Writer

import com.thoughtworks.xstream.io.xml.PrettyPrintWriter
import info.gianlucacosta.helios.io.DecoratorWriter
import info.gianlucacosta.twobinpack.core.ProblemBundle
import info.gianlucacosta.twobinpack.io.ModelXStream


/**
  * Writer providing a dedicated method for writing problem bundles as pretty-printed XML
  *
  * @param targetWriter
  */
class ProblemBundleWriter(targetWriter: Writer) extends DecoratorWriter(targetWriter) {
  def writeProblemBundle(problemBundle: ProblemBundle): Unit = {
    val prettyPrintWriter =
      new PrettyPrintWriter(this)

    ModelXStream.marshal(problemBundle, prettyPrintWriter)
  }
}
