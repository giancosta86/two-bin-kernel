package info.gianlucacosta.twobinpack.core


object BlockDimension {
  val Max: BlockDimension =
    BlockDimension(
      Int.MaxValue,
      Int.MaxValue
    )
}


/**
  * Dimension of a block
  */
case class BlockDimension(
                           width: Int,
                           height: Int
                         ) extends QuantizedDimension2D[BlockDimension] with Ordered[BlockDimension] {

  require(
    width > 0,
    "Block width must be > 0"
  )

  require(
    height > 0,
    "Block height must be > 0"
  )


  override def createNew(width: Int, height: Int): BlockDimension =
    copy(width, height)

  /**
    * To optimize space, block dimensions are sorted first by height, then by width
    *
    * @param that The other dimension
    * @return The comparison result
    */
  override def compare(that: BlockDimension): Int = {
    val heightComparison =
      height.compare(that.height)

    if (heightComparison != 0)
      heightComparison
    else
      width.compare(that.width)
  }
}
