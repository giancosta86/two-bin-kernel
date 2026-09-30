package info.gianlucacosta.twobinpack.test

import java.time.Duration

import info.gianlucacosta.twobinpack.core._

private[gianlucacosta] object SimpleTestData {
  val ProblemA =
    Problem(
      FrameTemplate(
        FrameDimension(
          20,
          15
        ),

        FrameMode.Knapsack,

        BlockPool.create(
          canRotateBlocks = true,

          BlockDimension(
            1,
            1
          ) -> 5,

          BlockDimension(
            3,
            4
          ) -> 2
        ),

        FrameTemplate.SuggestedBlockColorsPool,

        30
      ),

      Some(Duration.ofMinutes(5)),

      "ProblemA"
    )


  val SolutionA1 =
    Solution(
      ProblemA,
      Some("Test user"),
      None,

      Set(
        AnchoredBlock(
          BlockDimension(
            1,
            1
          ),

          QuantizedPoint2D(
            0,
            0
          )
        )
      )
    )


  val SolutionA2 =
    Solution(
      ProblemA,
      None,
      None,

      Set(
        AnchoredBlock(
          BlockDimension(
            1,
            1
          ),

          QuantizedPoint2D(
            11,
            9
          )
        ),

        AnchoredBlock(
          BlockDimension(
            4,
            3
          ),

          QuantizedPoint2D(
            5,
            6
          )
        )
      )
    )


  val ProblemB =
    Problem(
      FrameTemplate(
        FrameDimension(
          15,
          12
        ),

        FrameMode.Strip,

        BlockPool.create(
          canRotateBlocks = false,

          BlockDimension(
            1,
            1
          ) -> 2,

          BlockDimension(
            8,
            1
          ) -> 3
        ),

        FrameTemplate.SuggestedBlockColorsPool,

        30
      ),

      None,

      "ProblemB"
    )


  val SolutionB1 =
    Solution(
      ProblemB,
      None,
      None,

      Set(
        AnchoredBlock(
          BlockDimension(
            1,
            1
          ),

          QuantizedPoint2D(
            6,
            10
          )
        ),

        AnchoredBlock(
          BlockDimension(
            8,
            1
          ),

          QuantizedPoint2D(
            3,
            11
          )
        )
      )
    )


  val BppProblem =
    Problem(
      FrameTemplate(
        FrameDimension(
          1,
          15
        ),

        FrameMode.Strip,

        BlockPool.create(
          canRotateBlocks = false,

          BlockDimension(
            1,
            1
          ) -> 5,

          BlockDimension(
            1,
            4
          ) -> 2
        ),

        FrameTemplate.SuggestedBlockColorsPool,

        30
      ),

      Some(Duration.ofMinutes(5)),

      "BPP problem"
    )


  val HorizontalBlockDimension =
    BlockDimension(
      8,
      3
    )


  val SquareBlockDimension =
    BlockDimension(
      5,
      5
    )


  val VerticalBlockDimension =
    HorizontalBlockDimension.rotate()


  val AllBlockDimensions =
    Set(
      HorizontalBlockDimension,
      SquareBlockDimension,
      VerticalBlockDimension
    )


  val UniqueBlockDimensions =
    Set(
      HorizontalBlockDimension,
      SquareBlockDimension
    )


  val HorizontalFrameDimension =
    FrameDimension(
      8,
      3
    )


  val SquareFrameDimension =
    FrameDimension(
      5,
      5
    )


  val VerticalFrameDimension =
    HorizontalFrameDimension.rotate()
}
