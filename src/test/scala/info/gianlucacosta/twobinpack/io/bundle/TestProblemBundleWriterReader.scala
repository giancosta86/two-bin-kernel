package info.gianlucacosta.twobinpack.io.bundle

import java.io.{BufferedReader, Writer}

import info.gianlucacosta.twobinpack.core._
import info.gianlucacosta.twobinpack.io.WriterReaderTestBase
import info.gianlucacosta.twobinpack.test.SimpleTestData

class TestProblemBundleWriterReader extends WriterReaderTestBase[ProblemBundle, ProblemBundleWriter, ProblemBundleReader] {
  override protected def createOriginalObject(): ProblemBundle = {
    ProblemBundle(List(
      SimpleTestData.ProblemA,
      SimpleTestData.ProblemB
    ))
  }


  override protected def createWriter(targetWriter: Writer): ProblemBundleWriter =
    new ProblemBundleWriter(targetWriter)


  override protected def exportObject(writer: ProblemBundleWriter, originalObject: ProblemBundle): Unit =
    writer.writeProblemBundle(originalObject)


  override protected def createReader(sourceReader: BufferedReader): ProblemBundleReader =
    new ProblemBundleReader(sourceReader)


  override protected def importObject(reader: ProblemBundleReader): ProblemBundle =
    reader.readProblemBundle()
}
