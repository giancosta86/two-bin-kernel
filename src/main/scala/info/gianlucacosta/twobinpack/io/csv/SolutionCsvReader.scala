package info.gianlucacosta.twobinpack.io.csv

import java.io.BufferedReader
import java.util.UUID

import info.gianlucacosta.twobinpack.core._
import info.gianlucacosta.twobinpack.io.MissingProblemException
import info.gianlucacosta.twobinpack.io.repositories.ProblemRepository

/**
  * Reader providing a dedicated method for reading solutions from CSV
  *
  * @param sourceReader     The source reader
  * @param problemRetriever A function (problemId, solutionId) => Problem,
  *                         used to retrieve the Problem object when
  *                         instantiating the Solution
  */
class SolutionCsvReader(
                         sourceReader: BufferedReader,
                         problemRetriever: (UUID, UUID) => Problem
                       ) extends SolutionCsvReaderBase(sourceReader) {

  /**
    * Simplified constructor, employing a ProblemRepository to retrieve problems
    *
    * @param sourceReader
    * @param problemRepository
    * @return
    */
  def this(
            sourceReader: BufferedReader,
            problemRepository: ProblemRepository
          ) = this(
    sourceReader,
    (problemId, solutionId) =>
      problemRepository.findById(problemId).getOrElse(
        throw new MissingProblemException(
          s"Solution '${solutionId}' is related to the problem having id '${problemId}', which is not in the repository"
        )
      )
  )


  override def readSolution(): Option[Solution] = {
    val uuidLine: String =
      sourceReader.readLine()

    if (uuidLine != null) {
      val solutionId =
        UUID.fromString(
          uuidLine.trim
        )

      val problemId =
        UUID.fromString(
          sourceReader.readLine().trim
        )


      val problem: Problem =
        problemRetriever(problemId, solutionId)


      val solverOptionLine =
        sourceReader.readLine().trim

      val solverOption =
        if (solverOptionLine.nonEmpty)
          Some(solverOptionLine)
        else
          None

      //It was not present in the first version of the document format
      val elapsedTimeOption =
        None


      val blocks =
        parseAnchoredBlocks()


      Some(
        Solution(
          problem,
          solverOption,
          elapsedTimeOption,
          blocks,
          solutionId
        )
      )
    } else
      None
  }
}
