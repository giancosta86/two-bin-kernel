package info.gianlucacosta.twobinpack.core

import info.gianlucacosta.twobinpack.test.SimpleTestData.{HorizontalBlockDimension, SquareBlockDimension, VerticalBlockDimension}

class TestBlockDimension extends QuantizedDimension2DTestBase[BlockDimension] {
  override protected val horizontalDimension: BlockDimension =
    HorizontalBlockDimension

  override protected val squareDimension: BlockDimension =
    SquareBlockDimension

  override protected val verticalDimension: BlockDimension =
    VerticalBlockDimension


  override protected def createDimension(width: Int, height: Int): BlockDimension =
    BlockDimension(
      width,
      height
    )


  "A block dimension" should "NOT have width == 0" in {
    intercept[IllegalArgumentException] {
      BlockDimension(
        0,
        9
      )
    }
  }


  "A block dimension" should "NOT have height == 0" in {
    intercept[IllegalArgumentException] {
      BlockDimension(
        17,
        0
      )
    }
  }


  "Block dimensions" should "be sorted" in {
    val smallerHorizontalBlockDimension =
      HorizontalBlockDimension.copy(width = HorizontalBlockDimension.width - 1)

    val biggerVerticalBlockDimension =
      VerticalBlockDimension.copy(height = VerticalBlockDimension.height + 1)

    val biggestSquareBlockDimension =
      BlockDimension(VerticalBlockDimension.height * 10, VerticalBlockDimension.height * 10)

    smallerHorizontalBlockDimension should be < HorizontalBlockDimension

    HorizontalBlockDimension should be < SquareBlockDimension

    SquareBlockDimension should be < VerticalBlockDimension

    VerticalBlockDimension should be < biggerVerticalBlockDimension

    biggerVerticalBlockDimension should be < biggestSquareBlockDimension
  }
}
