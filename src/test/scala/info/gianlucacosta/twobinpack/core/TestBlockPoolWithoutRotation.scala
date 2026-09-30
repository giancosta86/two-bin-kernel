package info.gianlucacosta.twobinpack.core

import info.gianlucacosta.twobinpack.test.SimpleTestData.{HorizontalBlockDimension, SquareBlockDimension}

class TestBlockPoolWithoutRotation extends BlockPoolTestBase(canRotateBlocks = false) {
  "Creating an empty block pool" should "be allowed" in {
    BlockPool.create(
      canRotateBlocks = false
    )
  }


  "Creating a block pool with a quantity = 0" should "fail" in {
    intercept[IllegalArgumentException] {
      BlockPool.create(
        canRotateBlocks = false,

        HorizontalBlockDimension -> 10,

        SquareBlockDimension -> 0
      )
    }
  }


  "Creating a block pool with a quantity < 0" should "fail" in {
    intercept[IllegalArgumentException] {
      BlockPool.create(
        canRotateBlocks = false,

        HorizontalBlockDimension -> 5,

        SquareBlockDimension -> -6
      )
    }
  }


  "Blocks" should "include all the draft blocks" in {
    TestBlockPool.blocks should be(
      TestDraftBlocks.toMap
    )
  }


  "Block dimensions" should "include all the draft dimensions" in {
    TestBlockPool.blockDimensions should be(
      TestDraftBlocks.map(_._1)
    )
  }


  "Total blocks count" should "count all the draft blocks" in {
    TestBlockPool.totalBlockCount should be(
      TestDraftBlocks.map(_._2).sum
    )
  }
}
