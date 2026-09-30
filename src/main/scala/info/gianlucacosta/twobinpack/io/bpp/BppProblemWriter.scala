package info.gianlucacosta.twobinpack.io.bpp

import java.io.Writer

import info.gianlucacosta.helios.io.DecoratorWriter
import info.gianlucacosta.twobinpack.core.Problem

/**
  * Writer providing a dedicated method for writing BPP problems to text files
  *
  * @param targetWriter
  */
class BppProblemWriter(targetWriter: Writer) extends DecoratorWriter(targetWriter) {
  def writeBppProblem(problem: Problem): Unit = {
    require(problem.isBinPacking)

    targetWriter.write(problem.frameTemplate.blockPool.totalBlockCount.toString)
    targetWriter.write("\r\n")

    targetWriter.write(problem.frameTemplate.initialDimension.height.toString)
    targetWriter.write("\r\n")

    problem.frameTemplate.blockPool.blocks
      .toList
      .sortBy {
        case (blockDimension, quantity) =>
          blockDimension
      }
      .reverse
      .flatMap {
        case (blockDimension, quantity) =>
          List.fill(quantity)(blockDimension)
      }
      .zipWithIndex
      .foreach {
        case (blockDimension, index) =>
          targetWriter.write(blockDimension.height.toString)
          targetWriter.write("\r\n")
      }

    targetWriter.write("\r\n")
  }
}
