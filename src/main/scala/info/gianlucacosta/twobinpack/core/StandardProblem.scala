package info.gianlucacosta.twobinpack.core

import java.time.Duration
import java.util.UUID

import scalafx.scene.paint.Color

/**
  * A standard two-dimensional packing problem - containing a reduced subset of information
  * in comparison with a default Problem
  *
  * @param initialFrameDimension The initial frame dimension
  * @param blocks                The available blocks
  */
case class StandardProblem(
                            initialFrameDimension: FrameDimension,
                            blocks: Map[BlockDimension, Int]
                          ) {
  def this(sourceProblem: Problem) = this(
    sourceProblem.frameTemplate.initialDimension,
    sourceProblem.frameTemplate.blockPool.blocks
  )

  def toProblem(
                 id: UUID,
                 name: String,
                 frameMode: FrameMode,
                 blockColorsPool: List[Color],
                 canRotateBlocks: Boolean,
                 resolution: Int,
                 timeLimitOption: Option[Duration]
               ): Problem = {

    val blockPool: BlockPool =
      BlockPool.create(
        canRotateBlocks,
        blocks
      )


    val frameTemplate =
      FrameTemplate(
        initialFrameDimension,
        frameMode,
        blockPool,
        blockColorsPool,
        resolution
      )


    Problem(
      frameTemplate,
      timeLimitOption,
      name,
      id
    )
  }
}