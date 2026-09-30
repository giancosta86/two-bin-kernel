package info.gianlucacosta.twobinpack.io.repositories

import java.util.UUID

import info.gianlucacosta.twobinpack.core.Solution

/**
  * Repository simplifying common storage operations on Solution instances
  */
trait SolutionRepository {
  /**
    * Returns all the solutions in the repository
    *
    * @return
    */
  def findAll(): Iterable[Solution]

  /**
    * Returns all the solutions related to the given problem
    *
    * @param problemName The problem name. It can be a name not in the repository
    * @return The solutions to the given problem, or an empty iterable if the problem is missing
    */
  def findAllByProblemName(problemName: String): Iterable[Solution]

  /**
    * Finds the solution having the given id
    *
    * @param id The solution id
    * @return The solution, or None - if it was not found
    */
  def findById(id: UUID): Option[Solution]

  /**
    * Add a solution to the repository
    *
    * @param solution The solution to add. It must not be in the repository
    */
  def add(solution: Solution): Unit


  /**
    * Removes all the solutions related to the given problem; if such problem is not
    * in the repository, nothing happens
    *
    * @param problemName The problem name. It can be a name not in the repository
    */
  def removeByProblemName(problemName: String)

  /**
    * Total count of solutions in the repository
    *
    * @return
    */
  def count(): Long
}
