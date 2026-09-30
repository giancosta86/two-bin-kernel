package info.gianlucacosta.twobinpack.io.standard

import java.io.{BufferedReader, Writer}

import info.gianlucacosta.twobinpack.core.StandardProblem
import info.gianlucacosta.twobinpack.io.WriterReaderTestBase
import info.gianlucacosta.twobinpack.test.SimpleTestData

class TestStandardProblemWriterReader extends WriterReaderTestBase[StandardProblem, StandardProblemWriter, StandardProblemReader] {
  override protected def createOriginalObject(): StandardProblem =
    new StandardProblem(
      SimpleTestData.ProblemA
    )

  override protected def createWriter(targetWriter: Writer): StandardProblemWriter =
    new StandardProblemWriter(targetWriter)

  override protected def exportObject(writer: StandardProblemWriter, originalObject: StandardProblem): Unit =
    writer.writeStandardProblem(originalObject)

  override protected def createReader(sourceReader: BufferedReader): StandardProblemReader =
    new StandardProblemReader(sourceReader)

  override protected def importObject(reader: StandardProblemReader): StandardProblem =
    reader.readStandardProblem()
}
