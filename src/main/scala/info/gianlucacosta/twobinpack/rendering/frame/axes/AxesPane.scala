package info.gianlucacosta.twobinpack.rendering.frame.axes

import info.gianlucacosta.twobinpack.rendering.frame.Frame

import scalafx.scene.layout.{BorderPane, VBox}

/**
  * Pane adding axes around a Frame
  *
  * @param frame
  */
class AxesPane(
                frame: Frame,
                showVerticalAxis: Boolean = true,
                showHorizontalAxis: Boolean = true
              ) extends BorderPane {
  if (showVerticalAxis) {
    left =
      new VerticalAxis(frame)
  }


  if (showHorizontalAxis) {
    center =
      new VBox {
        children = List(
          frame,
          new HorizontalAxis(frame)
        )
      }
  }
}
