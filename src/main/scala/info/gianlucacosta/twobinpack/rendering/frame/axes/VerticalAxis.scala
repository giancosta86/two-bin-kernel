package info.gianlucacosta.twobinpack.rendering.frame.axes

import info.gianlucacosta.twobinpack.rendering.frame.Frame

import scalafx.Includes._
import scalafx.scene.canvas.GraphicsContext

/**
  * Vertical axis
  *
  * @param referenceFrame The reference frame
  */
private class VerticalAxis(referenceFrame: Frame) extends AxisBase(referenceFrame) {
  width <==
    referenceFrame.resolution

  height <==
    referenceFrame.quantizedHeight * referenceFrame.resolution


  protected override def drawLabels(gc: GraphicsContext): Unit = {
    Range(
      0,
      referenceFrame.quantizedHeight()
    ).foreach(topIndex => {
      val topOrdinal =
        topIndex + 1

      graphicsContext2D.font =
        labelFont()

      val resolution =
        referenceFrame.resolution()

      gc.fillText(
        topOrdinal.toString,
        width() / 2,
        topIndex * resolution + resolution / 2
      )
    })
  }
}
