package info.gianlucacosta.twobinpack.io.csv.v2

import java.io.Writer

import info.gianlucacosta.helios.io.DecoratorWriter
import info.gianlucacosta.twobinpack.core.Solution

/**
  * Writer providing a dedicated method for writing solutions to CSV - according to version 2 of the document format
  *
  * @param targetWriter
  */
class SolutionCsvWriter2(targetWriter: Writer) extends DecoratorWriter(targetWriter) {
  def writeSolution(solution: Solution): Unit = {
    targetWriter.write(solution.id.toString)
    targetWriter.write("\r\n")

    targetWriter.write(solution.problem.id.toString)
    targetWriter.write("\r\n")

    targetWriter.write(solution.solverOption.getOrElse(""))
    targetWriter.write("\r\n")

    targetWriter.write(
      solution.elapsedTimeOption
        .map(_.getSeconds.toString)
        .getOrElse("")
    )
    targetWriter.write("\r\n")

    solution.blocks.foreach(block => {
      targetWriter.write(block.dimension.width.toString)
      targetWriter.write(",")
      targetWriter.write(block.dimension.height.toString)
      targetWriter.write(",")
      targetWriter.write(block.anchor.left.toString)
      targetWriter.write(",")
      targetWriter.write(block.anchor.top.toString)
      targetWriter.write("\r\n")
    })

    targetWriter.write("\r\n")
  }
}
