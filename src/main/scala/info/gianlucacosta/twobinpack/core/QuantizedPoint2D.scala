package info.gianlucacosta.twobinpack.core

/**
  * Point whose coordinates are multiples of a basic "quantum" unit in a
  * standard 0-based coordinate system having left-right and top-bottom axes
  *
  * @param left
  * @param top
  */
case class QuantizedPoint2D(
                             left: Int,
                             top: Int
                           ) {
  override def toString: String =
    s"(${left}; ${top})"
}