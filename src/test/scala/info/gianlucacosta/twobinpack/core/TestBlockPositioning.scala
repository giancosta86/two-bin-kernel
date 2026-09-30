package info.gianlucacosta.twobinpack.core

import org.scalatest.{FlatSpec, Matchers}

class TestBlockPositioning extends FlatSpec with Matchers {
  private val potentialGallery =
    new BlockGallery(
      BlockPool.create(
        canRotateBlocks = true,

        BlockDimension(
          1,
          1
        ) -> 1,

        BlockDimension(
          4,
          2
        ) -> 1,

        BlockDimension(
          8,
          3
        ) -> 1,

        BlockDimension(
          13,
          4
        ) -> 1,

        BlockDimension(
          1,
          9
        ) -> 1
      )
    )


  private val existingBlocks = Set(
    AnchoredBlock(
      BlockDimension(
        6,
        1
      ),
      QuantizedPoint2D(
        2,
        1
      )
    ),

    AnchoredBlock(
      BlockDimension(
        1,
        9
      ),
      QuantizedPoint2D(
        1,
        9
      )
    ),

    AnchoredBlock(
      BlockDimension(
        3,
        1
      ),
      QuantizedPoint2D(
        2,
        7
      )
    ),

    AnchoredBlock(
      BlockDimension(
        1,
        2
      ),
      QuantizedPoint2D(
        12,
        2
      )
    )
  )


  private val potentialAnchor =
    QuantizedPoint2D(
      3,
      5
    )


  "getCompatibleBlocks()" should "keep track of existing blocks" in {
    val compatibleBlocks =
      BlockPositioning.getCompatibleBlocks(
        potentialGallery.availableBlocks,

        BlockDimension.Max,

        potentialAnchor,

        existingBlocks
      )


    compatibleBlocks should be(Map(
      AnchoredBlock(
        BlockDimension(
          1,
          1
        ),
        potentialAnchor
      ) -> 1,

      AnchoredBlock(
        BlockDimension(
          4,
          2
        ),
        potentialAnchor
      ) -> 1,


      AnchoredBlock(
        BlockDimension(
          2,
          4
        ),
        potentialAnchor
      ) -> 1,

      AnchoredBlock(
        BlockDimension(
          8,
          3
        ),
        potentialAnchor
      ) -> 1,


      AnchoredBlock(
        BlockDimension(
          9,
          1
        ),
        potentialAnchor
      ) -> 1
    ))
  }


  "getCompatibleBlocks()" should "keep track of maxDimension" in {
    val compatibleBlocks =
      BlockPositioning.getCompatibleBlocks(
        potentialGallery.availableBlocks,

        BlockDimension(
          2,
          4
        ),

        potentialAnchor,

        existingBlocks
      )

    compatibleBlocks should be(Map(
      AnchoredBlock(
        BlockDimension(
          1,
          1
        ),
        potentialAnchor
      ) -> 1,


      AnchoredBlock(
        BlockDimension(
          2,
          4
        ),
        potentialAnchor
      ) -> 1
    ))
  }


  "getCompatibleBlocks()" should "keep track of available quantity" in {
    val reducedPotentialGallery =
      potentialGallery.removeBlock(
        BlockDimension(
          4,
          2
        )
      )

    val compatibleBlocks =
      BlockPositioning.getCompatibleBlocks(
        reducedPotentialGallery.availableBlocks,

        BlockDimension.Max,

        potentialAnchor,

        existingBlocks
      )

    compatibleBlocks should be(Map(
      AnchoredBlock(
        BlockDimension(
          1,
          1
        ),
        potentialAnchor
      ) -> 1,


      AnchoredBlock(
        BlockDimension(
          8,
          3
        ),
        potentialAnchor
      ) -> 1,

      AnchoredBlock(
        BlockDimension(
          9,
          1
        ),
        potentialAnchor
      ) -> 1
    ))
  }


  "getCompatibleBlocks()" should "require quantities are > 0" in {
    val availableBlocks =
      Map(
        BlockDimension(
          1,
          1
        ) -> 0
      )

    intercept[IllegalArgumentException] {
      BlockPositioning.getCompatibleBlocks(
        availableBlocks,

        BlockDimension.Max,

        potentialAnchor,

        existingBlocks
      )
    }
  }
}
