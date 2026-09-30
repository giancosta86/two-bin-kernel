package info.gianlucacosta.twobinpack.core

/**
  * Dimension whose width and height are multiples of a basic "quantum" unit
  */
trait QuantizedDimension2D[T <: QuantizedDimension2D[T]] {
  this: T =>
  def width: Int

  def height: Int

  def createNew(width: Int, height: Int): T

  @transient
  lazy val isSquare: Boolean =
    width == height

  @transient
  lazy val area: Int =
    width * height


  @transient
  lazy val isHorizontal: Boolean =
    height <= width


  def rotate(): T =
    createNew(
      height,
      width
    )

  override def toString: String =
    s"${width} x ${height}"
}
