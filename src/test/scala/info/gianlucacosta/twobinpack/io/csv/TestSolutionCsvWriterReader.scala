package info.gianlucacosta.twobinpack.io.csv

import java.io.{BufferedReader, StringReader, StringWriter, Writer}

import info.gianlucacosta.twobinpack.core.Solution
import info.gianlucacosta.twobinpack.io.WriterReaderTestBase
import info.gianlucacosta.twobinpack.test.SimpleTestData

class TestSolutionCsvWriterReader extends WriterReaderTestBase[Solution, SolutionCsvWriter, SolutionCsvReader] {
  override protected def createOriginalObject(): Solution = {
    SimpleTestData.SolutionA1
  }


  override protected def createWriter(targetWriter: Writer): SolutionCsvWriter =
    new SolutionCsvWriter(targetWriter)


  override protected def exportObject(writer: SolutionCsvWriter, originalObject: Solution): Unit =
    writer.writeSolution(originalObject)


  override protected def createReader(sourceReader: BufferedReader): SolutionCsvReader =
    new SolutionCsvReader(
      sourceReader,
      (problemId, solutionId) => SimpleTestData.ProblemA
    )


  override protected def importObject(reader: SolutionCsvReader): Solution =
    reader.readSolution().get


  "Reading twice from a reader holding two solutions" should "return both solutions" in {
    val problem =
      SimpleTestData.ProblemA


    val solution1 =
      SimpleTestData.SolutionA1

    val solution2 =
      SimpleTestData.SolutionA2

    val stringWriter =
      new StringWriter

    val solutionWriter =
      new SolutionCsvWriter(stringWriter)


    solutionWriter.writeSolution(solution1)
    solutionWriter.writeSolution(solution2)

    solutionWriter.close()


    val solutionReader =
      new SolutionCsvReader(
        new BufferedReader(
          new StringReader(
            stringWriter.toString
          )
        ),

        (_, _) => problem
      )


    val firstRetrievedSolution =
      solutionReader.readSolution()

    firstRetrievedSolution should be(Some(solution1))


    val secondRetrievedSolution =
      solutionReader.readSolution()

    secondRetrievedSolution should be(Some(solution2))
  }


  "Reading twice from a reader holding just one solution" should "return None the second time" in {
    val problem =
      SimpleTestData.ProblemB

    val solution =
      SimpleTestData.SolutionB1

    val stringWriter =
      new StringWriter

    val solutionWriter =
      new SolutionCsvWriter(stringWriter)


    solutionWriter.writeSolution(solution)

    solutionWriter.close()


    val solutionReader =
      new SolutionCsvReader(
        new BufferedReader(
          new StringReader(
            stringWriter.toString
          )
        ),

        (_, _) => problem
      )

    val firstRetrievedSolution =
      solutionReader.readSolution()

    firstRetrievedSolution should be(Some(solution))


    val secondRetrievedSolution =
      solutionReader.readSolution()

    secondRetrievedSolution should be(None)
  }
}
