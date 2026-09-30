package info.gianlucacosta.twobinpack.io.csv.v2

import java.time.Duration

import info.gianlucacosta.twobinpack.core.Solution
import info.gianlucacosta.twobinpack.test.SimpleTestData.{ProblemA, SolutionA1}

class TestSolutionCsvWriterReaderV2_WithSomeElapsedTime extends SolutionCsvWriterReaderV2TestBase {
  override protected def createOriginalObject(): Solution = {
    require(ProblemA.timeLimitOption.nonEmpty)
    require(ProblemA.timeLimitOption.get.getSeconds > 3)

    SolutionA1.copy(elapsedTimeOption = Some(Duration.ofSeconds(3)))
  }
}
