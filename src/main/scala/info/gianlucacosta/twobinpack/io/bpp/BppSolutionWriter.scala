package info.gianlucacosta.twobinpack.io.bpp

import java.io.Writer

import info.gianlucacosta.helios.io.DecoratorWriter
import info.gianlucacosta.twobinpack.core.Solution

/**
  * Writes a BPP solution using a basic text format
  */
class BppSolutionWriter(targetWriter: Writer) extends DecoratorWriter(targetWriter) {
  def writeSolution(solution: Solution): Unit = {
    val blockSizesPerBin: Map[Int, List[Int]] =
      solution
        .blocks
        .groupBy(anchoredBlock => {
          val binOrdinal =
            anchoredBlock.left + 1

          binOrdinal
        })
        .map {
          case (binOrdinal, blockSet) =>
            val sortedBlockSizes =
              blockSet
                .map(_.dimension.height)
                .toList
                .sorted
                .reverse

            binOrdinal -> sortedBlockSizes
        }

    val maxBinOrdinalUsed =
      blockSizesPerBin
        .keys
        .max

    Range.inclusive(1, maxBinOrdinalUsed).foreach(binOrdinal => {
      val currentBinBlockSizes =
        blockSizesPerBin.getOrElse(
          binOrdinal,
          List()
        )

      targetWriter.write(s"${binOrdinal}) ${currentBinBlockSizes.mkString(" ")}\r\n")
    })


    targetWriter.write("\r\n")
  }
}
