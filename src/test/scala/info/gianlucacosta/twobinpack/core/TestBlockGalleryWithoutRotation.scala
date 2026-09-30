package info.gianlucacosta.twobinpack.core

import info.gianlucacosta.twobinpack.test.SimpleTestData.{HorizontalBlockDimension, SquareBlockDimension, VerticalBlockDimension}
import org.scalatest.{FlatSpec, Matchers}


class TestBlockGalleryWithoutRotation extends FlatSpec with Matchers {
  private val galleryWithoutRotatedBlocks =
    new BlockGallery(
      BlockPool.create(
        canRotateBlocks = false,

        HorizontalBlockDimension -> 110,

        SquareBlockDimension -> 120
      )
    )


  private val galleryWithRotatedBlocks =
    new BlockGallery(
      BlockPool.create(
        canRotateBlocks = false,

        HorizontalBlockDimension -> 130,

        SquareBlockDimension -> 140,

        VerticalBlockDimension -> 150
      )
    )


  "The gallery without rotated blocks" should "only show its construction blocks" in {
    galleryWithoutRotatedBlocks.availableBlocks should be(Map(
      HorizontalBlockDimension -> 110,

      SquareBlockDimension -> 120
    ))
  }


  "The gallery with rotated blocks" should "only show its construction blocks" in {
    galleryWithRotatedBlocks.availableBlocks should be(Map(
      HorizontalBlockDimension -> 130,

      SquareBlockDimension -> 140,

      VerticalBlockDimension -> 150
    ))
  }


  "The gallery without rotated blocks" should "decrease the quantity of the given block dimension" in {
    galleryWithoutRotatedBlocks.removeBlock(
      HorizontalBlockDimension
    ).availableBlocks should be(Map(
      HorizontalBlockDimension -> 109,

      SquareBlockDimension -> 120
    ))
  }


  "The gallery with rotated blocks" should "decrease just the quantity of the given block dimension" in {
    galleryWithRotatedBlocks.removeBlock(
      HorizontalBlockDimension
    ).availableBlocks should be(Map(
      HorizontalBlockDimension -> 129,

      SquareBlockDimension -> 140,

      VerticalBlockDimension -> 150
    ))
  }


  "The gallery without rotated blocks" should "not decrease a quantity below 0" in {
    val galleryWithNoHorizontalBlocks =
      Range(0, 110).foldLeft(galleryWithoutRotatedBlocks) {
        case (latestGallery, _) =>
          latestGallery.removeBlock(HorizontalBlockDimension)
      }

    intercept[IllegalArgumentException] {
      galleryWithNoHorizontalBlocks.removeBlock(
        HorizontalBlockDimension
      )
    }
  }

  "The gallery with rotated blocks" should "not decrease a quantity below 0" in {
    val galleryWithNoHorizontalBlocks =
      Range(0, 130).foldLeft(galleryWithRotatedBlocks) {
        case (latestGallery, _) =>
          latestGallery.removeBlock(HorizontalBlockDimension)
      }

    intercept[IllegalArgumentException] {
      galleryWithNoHorizontalBlocks.removeBlock(
        HorizontalBlockDimension
      )
    }
  }


  "The gallery without rotated blocks" should "increase the quantity of the given block dimension" in {
    galleryWithoutRotatedBlocks
      .removeBlock(
        HorizontalBlockDimension
      )
      .removeBlock(
        HorizontalBlockDimension
      )
      .addBlock(
        HorizontalBlockDimension
      ).availableBlocks should be(Map(
      HorizontalBlockDimension -> 109,

      SquareBlockDimension -> 120
    ))
  }


  "The gallery with rotated blocks" should "increase just the quantity of the given block dimension" in {
    galleryWithRotatedBlocks
      .removeBlock(
        HorizontalBlockDimension
      )
      .removeBlock(
        HorizontalBlockDimension
      )
      .addBlock(
        HorizontalBlockDimension
      ).availableBlocks should be(Map(
      HorizontalBlockDimension -> 129,

      SquareBlockDimension -> 140,

      VerticalBlockDimension -> 150
    ))
  }


  "The gallery without rotated blocks" should "not increase a quantity above the initial level" in {
    intercept[IllegalArgumentException] {
      galleryWithoutRotatedBlocks.addBlock(
        HorizontalBlockDimension
      )
    }
  }


  "The gallery with rotated blocks" should "not increase a quantity above the initial level" in {
    intercept[IllegalArgumentException] {
      galleryWithRotatedBlocks.addBlock(
        HorizontalBlockDimension
      )
    }
  }
}
