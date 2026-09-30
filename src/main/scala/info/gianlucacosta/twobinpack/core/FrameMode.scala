package info.gianlucacosta.twobinpack.core

object FrameMode {
  val Knapsack =
    FrameMode("Knapsack")

  val Strip =
    FrameMode("Strip")


  val All =
    List(
      Knapsack,
      Strip
    )


  def getByName(name: String): FrameMode =
    All.find(_.name == name).get
}


/**
  * A frame mode - that is, how the frame should react to user input
  *
  * @param name
  */
case class FrameMode private(name: String) {
  override def toString: String =
    name
}
