package info.gianlucacosta.twobinpack.rendering.gallery

import info.gianlucacosta.twobinpack.core.BlockDimension

import scalafx.Includes._
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.Label
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color
import scalafx.scene.text.{Font, FontWeight}


private object BlockItemRenderer {
  /**
    * Font used by the description label
    */
  private val descriptionFont =
    Font.font(
      "Arial",
      FontWeight.Bold,
      14
    )
}

/**
  * Renders a block item (that is, block and the related label) in the gallery
  *
  * @param blockGalleryPane The reference BlockGalleryPane
  * @param blockDimension   The block dimension
  * @param quantity         The number of instances of the block in the gallery
  * @param color            The block color
  * @param labelText        The text to show in the label
  */
private class BlockItemRenderer(
                                 blockGalleryPane: BlockGalleryPane,
                                 blockDimension: BlockDimension,
                                 quantity: Int,
                                 color: Color,
                                 labelText: String
                               ) extends VBox {

  styleClass +=
    "blockItem"

  alignment =
    Pos.Center

  spacing =
    7

  padding =
    Insets(10)


  private val blockRenderer =
    new BlockRenderer(
      blockDimension,
      blockGalleryPane.resolution,
      color,
      quantity,
      blockGalleryPane.interactive()
    )


  private val descriptionLabel =
    new Label {
      text =
        labelText

      font =
        BlockItemRenderer.descriptionFont
    }


  children = List(
    blockRenderer,
    descriptionLabel
  )
}
