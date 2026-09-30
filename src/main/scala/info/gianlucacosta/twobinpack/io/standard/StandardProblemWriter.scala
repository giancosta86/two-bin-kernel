package info.gianlucacosta.twobinpack.io.standard

import java.io.Writer

import info.gianlucacosta.helios.io.DecoratorWriter
import info.gianlucacosta.twobinpack.core.StandardProblem

/**
  * Writer providing a dedicated method for writing standard problems to text files
  *
  * @param targetWriter
  */
class StandardProblemWriter(targetWriter: Writer) extends DecoratorWriter(targetWriter) {
  def writeStandardProblem(standardProblem: StandardProblem): Unit = {
    val blockDimensionsCount =
      standardProblem.blocks.size

    targetWriter.write(blockDimensionsCount.toString)
    targetWriter.write("\r\n")

    targetWriter.write(standardProblem.initialFrameDimension.height.toString)
    targetWriter.write(" ")
    targetWriter.write(standardProblem.initialFrameDimension.width.toString)
    targetWriter.write("\r\n")

    standardProblem.blocks
      .toList
      .sortBy {
        case (blockDimension, quantity) =>
          blockDimension
      }
      .flatMap {
        case (blockDimension, quantity) =>
          List.fill(quantity)(blockDimension)
      }
      .zipWithIndex
      .foreach {
        case (blockDimension, index) =>
          val blockOrdinal =
            index + 1

          targetWriter.write(blockOrdinal.toString)
          targetWriter.write(" ")
          targetWriter.write(blockDimension.width.toString)
          targetWriter.write(" ")
          targetWriter.write(blockDimension.height.toString)
          targetWriter.write("\r\n")
      }

    targetWriter.write("\r\n")
  }
}
