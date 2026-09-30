package info.gianlucacosta.twobinpack.core

import scalafx.scene.paint.Color

/**
  * Provides colors for blocks, via its <i>getColor()</i> method.
  *
  * When rotation is enabled, it ensures that the horizontal and vertical version
  * of the same rectangular block have the same color; otherwise, they will have different
  * colors (as they actually are different block dimensions), provided that the color pool is
  * big enough: colors are assigned by cyclically iterating on the color pool.
  *
  * Furthermore, colors are deterministically assigned by pairing the list of colors with
  * the list of sorted dimensions.
  *
  * @param blockPool The related block pool
  * @param colorPool A list of colors
  */
case class ColorPalette(
                         blockPool: BlockPool,
                         colorPool: List[Color]
                       ) {

  private val colorMap: Map[BlockDimension, Color] =
    blockPool
      .blockDimensions
      .toList
      .sorted
      .zipWithIndex
      .flatMap {
        case (blockDimension, index) =>
          val color =
            colorPool(index % colorPool.length)

          if (blockPool.canRotateBlocks)
            List(
              blockDimension -> color,
              blockDimension.rotate() -> color
            )
          else List(
            blockDimension -> color
          )
      }.toMap


  def getColor(blockDimension: BlockDimension): Color =
    colorMap(blockDimension)
}
