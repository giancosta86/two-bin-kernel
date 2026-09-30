package info.gianlucacosta.twobinpack.rendering.frame.axes

import javafx.beans.property.SimpleObjectProperty

import info.gianlucacosta.twobinpack.rendering.frame.Frame

import scalafx.Includes._
import scalafx.beans.binding.Bindings
import scalafx.geometry.VPos
import scalafx.scene.canvas.{Canvas, GraphicsContext}
import scalafx.scene.paint.Color
import scalafx.scene.text.{Font, TextAlignment}

/**
  * Base class for frame axes
  *
  * @param referenceFrame The reference frame
  */
private abstract class AxisBase(referenceFrame: Frame) extends Canvas {
  graphicsContext2D.textAlign =
    TextAlignment.Center

  graphicsContext2D.textBaseline =
    VPos.Center

  graphicsContext2D.fill =
    Color.Gray


  width.onChange {
    render()
  }

  height.onChange {
    render()
  }


  protected val labelFont =
    new SimpleObjectProperty[Font]

  labelFont <==
    Bindings.createObjectBinding(
      () => {
        Font(
          "Arial",
          referenceFrame.resolution() / 3
        )
      },

      referenceFrame.resolution
    )


  private def render(): Unit = {
    graphicsContext2D.clearRect(
      0,
      0,
      width(),
      height()
    )

    drawLabels(graphicsContext2D)
  }


  protected def drawLabels(gc: GraphicsContext): Unit
}
