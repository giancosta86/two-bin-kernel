package info.gianlucacosta.twobinpack.core

import info.gianlucacosta.twobinpack.test.SimpleTestData.{AllBlockDimensions, HorizontalBlockDimension, UniqueBlockDimensions, VerticalBlockDimension}
import org.scalatest.{FlatSpec, Matchers}

import scalafx.scene.paint.Color

class TestColorPalette extends FlatSpec with Matchers {
  private val colorPool =
    List(
      Color.Red,
      Color.Green,
      Color.Blue
    )


  "The returned color" should "be the same for a dimension and its rotated version, if rotation is enabled" in {
    val colorPalette =
      ColorPalette(
        BlockPool.create(
          canRotateBlocks = true,
          AllBlockDimensions
            .map(_ -> Int.MaxValue)
            .toMap
        ),
        colorPool
      )

    colorPalette.getColor(HorizontalBlockDimension) should be(colorPalette.getColor(VerticalBlockDimension))
  }


  "The returned color" should "NOT be the same for a dimension and its rotated version, if rotation is not enabled" in {
    val colorPalette =
      ColorPalette(
        BlockPool.create(
          canRotateBlocks = false,
          AllBlockDimensions
            .map(_ -> Int.MaxValue)
            .toMap
        ),
        colorPool
      )

    colorPalette.getColor(HorizontalBlockDimension) should not be colorPalette.getColor(VerticalBlockDimension)
  }


  "Requesting the color of a rotated dimension" should "fail is rotation is not enabled" in {
    val colorPalette =
      ColorPalette(
        BlockPool.create(
          canRotateBlocks = false,
          UniqueBlockDimensions
            .map(_ -> Int.MaxValue)
            .toMap
        ),
        colorPool
      )

    intercept[NoSuchElementException] {
      colorPalette.getColor(VerticalBlockDimension)
    }
  }
}
