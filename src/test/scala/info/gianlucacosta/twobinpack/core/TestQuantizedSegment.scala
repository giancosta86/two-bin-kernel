package info.gianlucacosta.twobinpack.core

import org.scalatest.{FlatSpec, Matchers}

class TestQuantizedSegment extends FlatSpec with Matchers {
  private val maxTestIterations =
    500


  private val maxSubsegmentSize =
    9


  "Partitioning" should "keep the original segment length" in {
    runPartitionTests((initialSegment, partitionSegments) => {
      val partitionLength =
        partitionSegments
          .map(_.length)
          .sum

      partitionLength should be(initialSegment.length)
    })
  }


  "Partitioning" should "create non-empty subsegments" in {
    runPartitionTests((initialSegment, partitionSegments) => {
      partitionSegments.foreach(segment => {
        segment.length should be >= 1
      })
    })
  }


  "Partitioning" should "create subsegments not longer than their allowed size" in {
    runPartitionTests((initialSegment, partitionSegments) => {
      partitionSegments.foreach(segment => {
        segment.length should be <= maxSubsegmentSize
      })
    })
  }


  private def runPartitionTests(callback: (QuantizedSegment, List[QuantizedSegment]) => Unit): Unit = {
    val initialSegment =
      QuantizedSegment(59)


    Range.inclusive(1, maxTestIterations).foreach { _ =>
      val partitionSegments =
        initialSegment.createRandomPartition(maxSubsegmentSize)


      callback(
        initialSegment,
        partitionSegments
      )
    }
  }
}
