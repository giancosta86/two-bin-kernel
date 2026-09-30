package info.gianlucacosta.twobinpack.core

import info.gianlucacosta.twobinpack.test.SimpleTestData.{HorizontalBlockDimension, SquareBlockDimension, VerticalBlockDimension}
import org.scalatest.{FlatSpec, Matchers}

abstract class BlockPoolTestBase(canRotateBlocks: Boolean) extends FlatSpec with Matchers {
  protected val TestDraftBlocks = Set(
    HorizontalBlockDimension -> 5,
    SquareBlockDimension -> 13,
    VerticalBlockDimension -> 17
  )


  protected val TestBlockPool =
    BlockPool.create(
      canRotateBlocks,
      TestDraftBlocks.toList: _*
    )
}
