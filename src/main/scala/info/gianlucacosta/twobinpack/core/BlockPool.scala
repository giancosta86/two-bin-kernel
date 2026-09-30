package info.gianlucacosta.twobinpack.core

import scala.annotation.tailrec

object BlockPool {
  /**
    * Syntactic sugar to create a block pool
    *
    * @param canRotateBlocks
    * @param draftBlocks
    * @return
    */
  def create(canRotateBlocks: Boolean, draftBlocks: (BlockDimension, Int)*): BlockPool =
    create(canRotateBlocks, draftBlocks.toMap)


  /**
    * Creates a block pool.
    *
    * Firstly, all the quantities in the map of draft blocks must be > 0.
    *
    * Then:
    *
    * <ul>
    * <li>
    * if rotation is disabled, the block pool will contain the very map of draft blocks
    * </li>
    *
    * <li>
    * if rotation is enabled, the block pool will only contain horizontal blocks (that
    * is, blocks having height <= width): vertical blocks will be counted as horizontal
    * blocks
    * </li>
    * </ul>
    *
    * Such choice optimizes and simplifies the model - leaving the creation of vertical
    * copies of each block to other classes - for example, BlockGallery.
    *
    * @param canRotateBlocks true if the user can rotate blocks
    * @param draftBlocks     The suggested pool of blocks, which might be internally optimized
    * @return The block pool
    */
  def create(canRotateBlocks: Boolean, draftBlocks: Map[BlockDimension, Int]): BlockPool = {
    draftBlocks.foreach {
      case (dimension, quantity) =>
        require(
          quantity > 0,
          s"Blocks having dimension ${dimension} are ${quantity} <= 0"
        )
    }

    val actualBlocks: Map[BlockDimension, Int] =
      if (canRotateBlocks)
        getBlocksMadeHorizontal(draftBlocks)
      else
        draftBlocks


    new BlockPool(
      canRotateBlocks,
      actualBlocks
    )
  }


  private def getBlocksMadeHorizontal(
                                       draftBlocks: Map[BlockDimension, Int]
                                     ): Map[BlockDimension, Int] = {
    getBlocksMadeHorizontal(
      Map(),
      draftBlocks.toList
    )
  }

  @tailrec
  private def getBlocksMadeHorizontal(
                                       cumulatedBlocks: Map[BlockDimension, Int],
                                       remainingDraftBlocks: List[(BlockDimension, Int)]
                                     ): Map[BlockDimension, Int] = {
    remainingDraftBlocks match {
      case Nil =>
        cumulatedBlocks

      case (dimension, quantity) :: tailRemaningDraftBlocks =>
        val horizontalDimension =
          if (dimension.isHorizontal)
            dimension
          else
            dimension.rotate()

        val cumulatedQuantity =
          cumulatedBlocks.getOrElse(horizontalDimension, 0)

        val totalQuantity =
          quantity + cumulatedQuantity

        val newCumulatedBlocks: Map[BlockDimension, Int] =
          cumulatedBlocks
            .updated(
              horizontalDimension,
              totalQuantity
            )

        getBlocksMadeHorizontal(
          newCumulatedBlocks,
          tailRemaningDraftBlocks
        )
    }
  }
}


/**
  * The optimized source of blocks for a problem.
  *
  * Can only be created via the dedicated factory methods in the companion object.
  *
  * @param canRotateBlocks true if the user can rotate blocks
  * @param blocks          A map of (block dimension -> quantity) showing the available blocks
  */
case class BlockPool private(canRotateBlocks: Boolean, blocks: Map[BlockDimension, Int]) {
  /**
    * The set of block dimensions available in the pool.
    *
    * When rotation is enabled, only horizontal dimensions will be shown, according
    * to the algorithm of the block pool creation method.
    */
  @transient
  lazy val blockDimensions: Set[BlockDimension] =
  blocks
    .keySet


  /**
    * The total number of blocks in the pool.
    */
  @transient
  lazy val totalBlockCount: Int =
  blocks
    .values
    .sum
}
