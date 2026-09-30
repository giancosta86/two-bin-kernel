package info.gianlucacosta.twobinpack.core

import java.time.Duration
import java.util.UUID

import info.gianlucacosta.helios.Includes._

object Problem {
  val MaxTimeLimit =
    Duration.ofDays(1)

  val MinResolution =
    1

  val MaxResolution =
    100

  val SuggestedResolution =
    30


  /**
    * Provides a suggested name for a problem given a few of its parameters
    *
    * @param frameTemplate The frame template
    * @return The suggested name
    */
  def getSuggestedName(frameTemplate: FrameTemplate): String = {
    val rotationString =
      if (frameTemplate.blockPool.canRotateBlocks)
        "R"
      else
        "NR"


    s"${frameTemplate.frameMode.name(0)} ${frameTemplate.initialDimension}, ${frameTemplate.blockPool.blockDimensions.size} D, ${frameTemplate.blockPool.totalBlockCount} B, ${rotationString}"
  }
}

/**
  * Two-dimensional packing problem.
  *
  * Problems are ordered by name.
  *
  * @param frameTemplate   The template underlying the problem's frame
  * @param timeLimitOption The optional time limit. Cannot exceed 1 day. If None, no time limit will be set
  * @param name            The problem name. Must not be empty
  * @param id              The unique ID
  */
case class Problem(
                    frameTemplate: FrameTemplate,
                    timeLimitOption: Option[Duration],
                    name: String,
                    id: UUID = UUID.randomUUID()
                  ) extends Ordered[Problem] {
  timeLimitOption.foreach(timeLimit => {
    require(
      timeLimit > Duration.ZERO,
      "The time limit must be a positive duration"
    )

    require(
      timeLimit <= Problem.MaxTimeLimit,
      "The time limit cannot exceed its maximum value"
    )
  })


  require(
    name.nonEmpty,
    "The problem name cannot be empty"
  )


  /**
    * True if the problem is a Bin Packing Problem (BPP)
    */
  lazy val isBinPacking: Boolean =
    (frameTemplate.frameMode == FrameMode.Strip) &&
      frameTemplate.blockPool.blockDimensions.forall(_.width == 1) &&
      (!frameTemplate.blockPool.canRotateBlocks)


  override def compare(that: Problem): Int =
    name.compareTo(that.name)


  override def toString: String =
    name
}
