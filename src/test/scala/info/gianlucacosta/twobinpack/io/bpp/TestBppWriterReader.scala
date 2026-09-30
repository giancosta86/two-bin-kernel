package info.gianlucacosta.twobinpack.io.bpp

import java.io.{BufferedReader, Writer}

import info.gianlucacosta.twobinpack.core.Problem
import info.gianlucacosta.twobinpack.io.WriterReaderTestBase
import info.gianlucacosta.twobinpack.test.SimpleTestData.BppProblem


class TestBppWriterReader extends WriterReaderTestBase[Problem, BppProblemWriter, BppProblemReader] {
  override protected def createOriginalObject(): Problem =
    BppProblem

  override protected def createWriter(targetWriter: Writer): BppProblemWriter =
    new BppProblemWriter(targetWriter)

  override protected def exportObject(writer: BppProblemWriter, originalObject: Problem): Unit =
    writer.writeBppProblem(originalObject)

  override protected def createReader(sourceReader: BufferedReader): BppProblemReader =
    new BppProblemReader(sourceReader)

  override protected def importObject(reader: BppProblemReader): Problem =
    reader.readBppProblem(
      BppProblem.frameTemplate.initialDimension.width,
      BppProblem.timeLimitOption,
      BppProblem.name,
      BppProblem.id
    )
}
