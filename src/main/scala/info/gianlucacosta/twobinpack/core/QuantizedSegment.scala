package info.gianlucacosta.twobinpack.core

import scala.annotation.tailrec
import scala.util.Random

/**
  * A segment whose length is a multiple of a basic "quantum" unit
  *
  * @param length The quantized length of the segment
  */
case class QuantizedSegment(length: Int) {
  require(
    length >= 0,
    "The segment length must be >= 0"
  )

  /**
    * Splits this segment into a random list of subsegments, each having random length <= maxSubsegmentLength
    *
    * @param maxSubsegmentLength The maximum length of every subsegment
    * @return The list of random subsegments
    */
  def createRandomPartition(maxSubsegmentLength: Int): List[QuantizedSegment] = {
    require(
      maxSubsegmentLength > 0,
      "Subsegments must have maximum length > 0"
    )

    createRandomPartition(maxSubsegmentLength, 0, Nil)
  }

  @tailrec
  private def createRandomPartition(
                                     maxSubsegmentLength: Int,
                                     cumulatedSubsegmentsLength: Int,
                                     cumulatedSubsegments: List[QuantizedSegment]): List[QuantizedSegment] = {
    if (cumulatedSubsegmentsLength == length)
      cumulatedSubsegments //As segments are random, we do not need to reverse here
    else {
      val availableLength =
        length - cumulatedSubsegmentsLength

      val currentMaxLength: Int =
        math.min(
          maxSubsegmentLength,
          availableLength
        )

      val currentLength: Int =
        Random.nextInt(currentMaxLength) + 1

      val currentSegment =
        QuantizedSegment(currentLength)


      createRandomPartition(
        maxSubsegmentLength,
        cumulatedSubsegmentsLength + currentLength,
        currentSegment :: cumulatedSubsegments
      )
    }
  }
}
