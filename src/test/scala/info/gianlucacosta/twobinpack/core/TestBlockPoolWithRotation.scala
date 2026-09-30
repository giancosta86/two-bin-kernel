package info.gianlucacosta.twobinpack.core

import info.gianlucacosta.twobinpack.test.SimpleTestData.{HorizontalBlockDimension, SquareBlockDimension}


class TestBlockPoolWithRotation extends BlockPoolTestBase(canRotateBlocks = true) {
  "Creating an empty block pool" should "be allowed" in {
    BlockPool.create(
      canRotateBlocks = true
    )
  }


  "Creating a block pool with a quantity = 0" should "fail" in {
    intercept[IllegalArgumentException] {
      BlockPool.create(
        canRotateBlocks = true,

        HorizontalBlockDimension -> 5,

        SquareBlockDimension -> 0
      )
    }
  }


  "Creating a block pool with a quantity < 0" should "fail" in {
    intercept[IllegalArgumentException] {
      BlockPool.create(
        canRotateBlocks = true,

        HorizontalBlockDimension -> 5,

        SquareBlockDimension -> -6
      )
    }
  }


  "Blocks" should "include only horizontal draft dimensions, but with summed quantities" in {
    TestBlockPool.blocks should be(
      Map(
        HorizontalBlockDimension -> 22,
        SquareBlockDimension -> 13
      )
    )
  }


  "Block dimensions" should "include only horizontal dimensions" in {
    TestBlockPool.blockDimensions should be(
      Set(
        HorizontalBlockDimension,
        SquareBlockDimension
      )
    )
  }


  "Total blocks count" should "count all the draft blocks" in {
    TestBlockPool.totalBlockCount should be(
      TestDraftBlocks.map(_._2).sum
    )
  }
}
