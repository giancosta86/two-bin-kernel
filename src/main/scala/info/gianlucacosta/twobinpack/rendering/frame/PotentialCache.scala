package info.gianlucacosta.twobinpack.rendering.frame

import info.gianlucacosta.twobinpack.core.{AnchoredBlock, QuantizedPoint2D}

/**
  * Cache providing information computed when the user starts scrolling the
  * mouse wheel on a Frame cell, therefore requesting potential blocks
  * that are a subset of the block gallery.
  *
  * @param anchor                  The anchor point
  * @param compatibleBlocks        The anchored blocks that might exist at that point
  * @param currentBlockIndexOption The index of the current potential block seen by the user
  *                                (None, if compatibleBlocks is empty)
  */
private case class PotentialCache(
                                   anchor: QuantizedPoint2D,
                                   compatibleBlocks: List[(AnchoredBlock, Int)],
                                   currentBlockIndexOption: Option[Int]
                                 ) {
  /**
    * The current potential block. None if compatibleBlocks is empty
    */
  val potentialBlockOption: Option[AnchoredBlock] =
    currentBlockIndexOption.map(currentBlockIndex =>
      compatibleBlocks(currentBlockIndex)._1
    )


  /**
    * The remaining quantity (in the gallery) of the current potential block. None if compatibleBlocks is empty
    */
  val potentialBlockQuantityOption: Option[Int] =
    currentBlockIndexOption.map(currentBlockIndex =>
      compatibleBlocks(currentBlockIndex)._2
    )


  /**
    * Returns a new copy of the potential cache, in response to the user's mouse wheel scrolling
    *
    * @param scrollDelta The delta scrolled by the user
    * @return An updated copy of the cache
    */
  def scrollCompatibleBlocks(scrollDelta: Int): PotentialCache =
    copy(
      currentBlockIndexOption =
        currentBlockIndexOption.map(currentBlockIndex =>
          (
            currentBlockIndex + math.signum(scrollDelta) + compatibleBlocks.length
            ) % compatibleBlocks.length
        )
    )
}
