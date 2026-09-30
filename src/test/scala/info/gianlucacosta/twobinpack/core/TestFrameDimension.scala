package info.gianlucacosta.twobinpack.core

import info.gianlucacosta.twobinpack.test.SimpleTestData.{HorizontalFrameDimension, SquareFrameDimension, VerticalFrameDimension}

class TestFrameDimension extends QuantizedDimension2DTestBase[FrameDimension] {
  override protected val horizontalDimension: FrameDimension =
    HorizontalFrameDimension

  override protected val squareDimension: FrameDimension =
    SquareFrameDimension

  override protected val verticalDimension: FrameDimension =
    VerticalFrameDimension

  override protected def createDimension(width: Int, height: Int): FrameDimension =
    FrameDimension(
      width,
      height
    )

  "A frame dimension" should "NOT have width == 0" in {
    intercept[IllegalArgumentException] {
      FrameDimension(
        0,
        9
      )
    }
  }


  "A frame dimension" should "NOT have height == 0" in {
    intercept[IllegalArgumentException] {
      FrameDimension(
        17,
        0
      )
    }
  }
}
