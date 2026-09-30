package info.gianlucacosta.twobinpack.io.bpp

import java.io.BufferedReader
import java.time.Duration
import java.util.UUID

import info.gianlucacosta.helios.io.DecoratorReader
import info.gianlucacosta.twobinpack.core._


/**
  * Reader providing a dedicated method for reading BPP problems from text files
  *
  * @param sourceReader
  */
class BppProblemReader(sourceReader: BufferedReader) extends DecoratorReader(sourceReader) {
  def readBppProblem(
                      initialFrameWidth: Int,
                      timeLimitOption: Option[Duration],
                      name: String,
                      id: UUID
                    ): Problem = {
    val totalBlockCount =
      sourceReader.readLine().toInt

    val frameLine =
      sourceReader.readLine().trim

    val initialFrameHeight =
      frameLine.toInt


    val initialFrameDimension =
      FrameDimension(
        initialFrameWidth,
        initialFrameHeight
      )


    val blockDimensions: Map[BlockDimension, Int] =
      parseLineBlock(trimmedLine => {
        val blockWidth =
          1

        val blockHeight =
          trimmedLine.toInt


        BlockDimension(
          blockWidth,
          blockHeight
        )
      })
        .groupBy(blockdimension => blockdimension)
        .map {
          case (blockDimension, groupedBlocks) =>
            blockDimension -> groupedBlocks.length
        }


    val blockPool =
      BlockPool.create(
        canRotateBlocks = false,
        draftBlocks = blockDimensions
      )

    require(
      blockPool.totalBlockCount == totalBlockCount,
      "The declared total block count does not match the actual number of declared blocks"
    )

    val frameTemplate =
      FrameTemplate(
        initialFrameDimension,
        FrameMode.Strip,
        blockPool,
        FrameTemplate.SuggestedBlockColorsPool,
        Problem.SuggestedResolution
      )


    val problem =
      Problem(
        frameTemplate,
        timeLimitOption,
        name,
        id
      )

    require(
      problem.isBinPacking,
      "The problem is not a Bin Packing Problem"
    )

    problem
  }
}
