package info.gianlucacosta.twobinpack.core

import scalafx.scene.paint.Color

object FrameTemplate {
  /**
    * A default pool of fairly pleasant colors
    */
  val SuggestedBlockColorsPool: List[Color] = List(
    Color.Yellow,
    Color.Orchid,
    Color.LightGreen,
    Color.PaleTurquoise,
    Color.Red,
    Color.DodgerBlue,
    Color.Orange,
    Color.HotPink,
    Color.ForestGreen,
    Color.DarkViolet,
    Color.FireBrick,
    Color.Teal,
    Color.LightPink,
    Color.OliveDrab,
    Color.Fuchsia,
    Color.Plum,
    Color.Khaki
  )
}

/**
  * Model information for creating a Frame
  *
  * @param initialDimension The initial dimension.
  *                         In Knapsack mode, the dimension remains constant,
  *                         whereas in Strip the width can arbitrarily grow
  * @param frameMode        The frame mode
  * @param blockPool        The available blocks
  * @param blockColorsPool  The pool of colors used to paint the blocks
  * @param resolution       How many pixels are used to render a quantum unit (both horizontally and vertically)
  */
case class FrameTemplate(
                          initialDimension: FrameDimension,
                          frameMode: FrameMode,
                          blockPool: BlockPool,
                          blockColorsPool: List[Color],
                          resolution: Int
                        ) {
  require(
    blockColorsPool.nonEmpty,
    "At least one color must be provided"
  )

  require(
    Problem.MinResolution <= resolution && resolution <= Problem.MaxResolution,
    s"The resolution must be in the range [${Problem.MinResolution}..${Problem.MaxResolution}]"
  )

  @transient
  lazy val blockGallery: BlockGallery =
    new BlockGallery(
      blockPool
    )

  @transient
  lazy val colorPalette: ColorPalette =
    ColorPalette(
      blockPool,
      blockColorsPool
    )
}
