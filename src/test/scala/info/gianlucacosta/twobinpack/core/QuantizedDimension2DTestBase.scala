package info.gianlucacosta.twobinpack.core

import org.scalatest.{FlatSpec, Matchers}

abstract class QuantizedDimension2DTestBase[T <: QuantizedDimension2D[T]] extends FlatSpec with Matchers {
  protected val horizontalDimension: T
  protected val squareDimension: T
  protected val verticalDimension: T

  protected def createDimension(width: Int, height: Int): T

  "isSquare" should "be true for a square dimension" in {
    squareDimension.isSquare should be(true)
  }

  "isSquare" should "be false for a rectangular dimension" in {
    horizontalDimension.isSquare should be(false)
  }


  "rotation" should "work for a rectangular dimension" in {
    horizontalDimension.rotate() should be(
      createDimension(
        horizontalDimension.height,
        horizontalDimension.width
      )
    )
  }

  "rotation" should "leave a square dimension unaltered" in {
    squareDimension.rotate() should be(squareDimension)
  }


  "area" should "be correct" in {
    createDimension(
      13,
      7
    ).area should be(91)
  }


  "isHorizontal" should "be true for a horizontal dimension" in {
    horizontalDimension.isHorizontal should be(true)
  }

  "isHorizontal" should "be false for a vertical dimension" in {
    verticalDimension.isHorizontal should be(false)
  }

  "isHorizontal" should "be true for a square dimension" in {
    squareDimension.isHorizontal should be(true)
  }
}
