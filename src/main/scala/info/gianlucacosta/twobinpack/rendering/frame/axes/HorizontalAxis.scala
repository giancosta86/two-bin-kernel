package info.gianlucacosta.twobinpack.rendering.frame.axes

import info.gianlucacosta.twobinpack.rendering.frame.Frame

import scalafx.Includes._
import scalafx.scene.canvas.GraphicsContext

/**
  * Horizontal axis
  *
  * @param referenceFrame The reference frame
  */
private class HorizontalAxis(referenceFrame: Frame) extends AxisBase(referenceFrame) {
  width <==
    referenceFrame.quantizedWidth * referenceFrame.resolution

  height <==
    referenceFrame.resolution


  protected override def drawLabels(gc: GraphicsContext): Unit = {
    Range(
      0,
      referenceFrame.quantizedWidth()
    ).foreach(leftIndex => {
      val leftOrdinal =
        leftIndex + 1

      val resolution =
        referenceFrame.resolution()

      graphicsContext2D.font =
        labelFont()

      gc.fillText(
        leftOrdinal.toString,
        leftIndex * resolution + resolution / 2,
        height() / 2
      )
    })
  }
}