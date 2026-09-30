package info.gianlucacosta.twobinpack.io

import java.io._

import org.scalatest.{FlatSpec, Matchers}

abstract class WriterReaderTestBase[TObject, TWriter <: Writer, TReader <: Reader] extends FlatSpec with Matchers {
  "Exporting to a Writer and importing from a Reader" should "return the original object" in {
    val originalObject =
      createOriginalObject()

    val restoredObject =
      saveAndRestore(originalObject)

    restoredObject should be(originalObject)
  }


  "Exporting and importing twice" should "return the original object" in {
    val originalObject =
      createOriginalObject()

    val restoredObject =
      saveAndRestore(originalObject)

    val secondLevelRestoredObject =
      saveAndRestore(restoredObject)


    secondLevelRestoredObject should be(originalObject)
  }


  private def saveAndRestore(originalObject: TObject): TObject = {
    val stringWriter =
      new StringWriter()

    val writer =
      createWriter(stringWriter)

    exportObject(writer, originalObject)


    val sourceString =
      stringWriter.toString

    println("-----------------")
    println(sourceString)
    println("-----------------")


    val stringReader =
      new StringReader(sourceString)

    val reader =
      createReader(
        new BufferedReader(
          stringReader
        )
      )


    importObject(reader)
  }


  protected def createOriginalObject(): TObject

  protected def createWriter(targetWriter: Writer): TWriter

  protected def exportObject(writer: TWriter, originalObject: TObject)

  protected def createReader(sourceReader: BufferedReader): TReader

  protected def importObject(reader: TReader): TObject
}
