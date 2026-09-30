package info.gianlucacosta.twobinpack.core

/**
  * Utility object for block positioning
  */
object BlockPositioning {
  /**
    * Given a map of available blocks, returns its filtered version where:
    * <ul>
    * <li>
    * The dimension of the block does not exceed the max dimension allowed
    * </li>
    *
    * <li>
    * placing the block at the given anchor will not make it overlap any of the
    * existing blocks
    * </li>
    * </ul>
    *
    * @param availableBlocks The map of blocks that could be placed. All the quantities must be > 0
    * @param maxDimension    The maximum dimension allowed for the block to place (for example, because of a Knapsack frame)
    * @param anchor          The potential anchor of the new block
    * @param existingBlocks  The existing anchored blocks
    * @return The filtered map of available blocks satisfying the constraints
    */
  def getCompatibleBlocks(
                           availableBlocks: Map[BlockDimension, Int],
                           maxDimension: BlockDimension,
                           anchor: QuantizedPoint2D,
                           existingBlocks: Set[AnchoredBlock]
                         ): Map[AnchoredBlock, Int] = {
    availableBlocks
      .filter {
        case (blockDimension, quantity) =>
          require(
            quantity > 0,
            s"Block ${blockDimension} has quantity ${quantity} <= 0"
          )

          (blockDimension.width <= maxDimension.width) &&
            (blockDimension.height <= maxDimension.height)
      }
      .map {
        case (blockDimension, quantity) =>
          val potentialBlock =
            AnchoredBlock(
              blockDimension,
              anchor
            )

          potentialBlock -> quantity
      }
      .filter {
        case (potentialBlock, quantity) =>
          !existingBlocks.exists(existingBlock =>
            existingBlock.overlaps(potentialBlock)
          )
      }
  }
}
