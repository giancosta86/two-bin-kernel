package info.gianlucacosta.twobinpack.core

/**
  * Quantized dimension of a Frame
  */
case class FrameDimension(width: Int, height: Int) extends QuantizedDimension2D[FrameDimension] {
  require(
    width > 0,
    "Frame width must be > 0"
  )

  require(
    height > 0,
    "Frame height must be > 0"
  )


  override def createNew(width: Int, height: Int): FrameDimension =
    copy(
      width,
      height
    )
}
