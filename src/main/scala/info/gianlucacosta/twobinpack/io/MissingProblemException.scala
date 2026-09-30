package info.gianlucacosta.twobinpack.io

/**
  * Thrown whenever a problem is not found
  *
  * @param message The exception message
  *
  */
class MissingProblemException(message: String)
  extends RuntimeException(message)
