package info.gianlucacosta.twobinpack.io.standard

import java.io.BufferedReader

import info.gianlucacosta.helios.io.DecoratorReader
import info.gianlucacosta.twobinpack.core._


/**
  * Reader providing a dedicated method for reading standard problems from text files
  *
  * @param sourceReader
  */
class StandardProblemReader(sourceReader: BufferedReader) extends DecoratorReader(sourceReader) {
  def readStandardProblem(): StandardProblem = {
    //The first line is just skipped
    sourceReader.readLine()

    val frameLine =
      sourceReader.readLine().trim

    val frameParams =
      frameLine.split(raw"\s+")

    val frameHeight =
      frameParams(0).toInt

    val frameWidth =
      frameParams(1).toInt

    val initialFrameDimension =
      FrameDimension(
        frameWidth,
        frameHeight
      )


    val blockDimensions: Map[BlockDimension, Int] =
      parseLineBlock(trimmedLine => {
        val blockParams =
          trimmedLine.split(raw"\s+")


        val blockWidth =
          blockParams(1).toInt

        val blockHeight =
          blockParams(2).toInt


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


    StandardProblem(
      initialFrameDimension,
      blockDimensions
    )
  }
}
