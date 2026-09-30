package info.gianlucacosta.twobinpack.io.csv

import java.io.BufferedReader

import info.gianlucacosta.helios.io.DecoratorReader
import info.gianlucacosta.twobinpack.core.{AnchoredBlock, BlockDimension, QuantizedPoint2D, Solution}

abstract class SolutionCsvReaderBase(sourceReader: BufferedReader) extends DecoratorReader(sourceReader) {
  /**
    * Reads a solution from the CSV, throwing exceptions on error; in particular,
    * it should throw MissingProblemException if the problem referenced by the
    * solution cannot be found in the underlying storage.
    *
    * @return The solution read - or None if EOF was not reached
    */
  def readSolution(): Option[Solution]

  /**
    * Reads a line
    *
    * @return The line, or null on EOF
    */
  def readLine(): String =
    sourceReader.readLine()


  /**
    * Parses a block of lines, assuming each of them describes an AnchoredBlock
    *
    * @return The set of AnchoredBlock instances read from the file
    */
  protected def parseAnchoredBlocks(): Set[AnchoredBlock] =
    parseLineBlock(
      trimmedLine => {
        val blockParameters =
          trimmedLine.split(',')
            .map(_.toInt)

        AnchoredBlock(
          BlockDimension(
            blockParameters(0),
            blockParameters(1)
          ),

          QuantizedPoint2D(
            blockParameters(2),
            blockParameters(3)
          )
        )
      }
    ).toSet
}
