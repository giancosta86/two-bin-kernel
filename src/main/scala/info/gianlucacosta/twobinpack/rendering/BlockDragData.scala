package info.gianlucacosta.twobinpack.rendering

import info.gianlucacosta.twobinpack.core.BlockDimension

/**
  * Block-related information during drag&drop of the block
  *
  * @param blockDimension The dimension of the block
  * @param quantity       Its quantity in the gallery
  */
private case class BlockDragData(
                                  blockDimension: BlockDimension,
                                  quantity: Int
                                ) extends Serializable
