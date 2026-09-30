package info.gianlucacosta.twobinpack.core

/**
  * An ordered list of distinct problems
  *
  * @param problems The problems in the bundle. All the problems must be distinct
  */
case class ProblemBundle(problems: List[Problem]) {
  require(
    problems.distinct == problems,
    "The problems in a problem bundle must be unique"
  )
}
