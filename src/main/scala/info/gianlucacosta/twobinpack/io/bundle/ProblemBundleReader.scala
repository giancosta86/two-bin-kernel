package info.gianlucacosta.twobinpack.io.bundle

import java.io.BufferedReader

import info.gianlucacosta.helios.io.DecoratorReader
import info.gianlucacosta.twobinpack.core.ProblemBundle
import info.gianlucacosta.twobinpack.io.ModelXStream

/**
  * Reader providing a dedicated method for reading problem bundles from XML
  *
  * @param sourceReader
  */
class ProblemBundleReader(sourceReader: BufferedReader) extends DecoratorReader(sourceReader) {
  def readProblemBundle(): ProblemBundle = {
    ModelXStream.fromXML(this).asInstanceOf[ProblemBundle]
  }
}
