package info.gianlucacosta.twobinpack.io.repositories

import java.util.UUID

import info.gianlucacosta.twobinpack.core.Problem

/**
  * Repository simplifying common storage operations on Problem instances
  */
trait ProblemRepository {
  /**
    * Returns all the problem names, sorted
    *
    * @return
    */
  def findAllNamesSorted(): Iterable[String]

  /**
    * Finds a problem given its ID
    *
    * @param id The problem ID
    * @return The problem - or None, if it was not found
    */
  def findById(id: UUID): Option[Problem]

  /**
    * Finds a problem given its name
    *
    * @param name The problem name
    * @return The problem - or None, if it was not found
    */
  def findByName(name: String): Option[Problem]

  /**
    * Adds a new problem to the repository
    *
    * @param problem The problem to add. It must not be in the repository
    */
  def add(problem: Problem): Unit

  /**
    * Updates an existing problem
    *
    * @param problem The problem to update. A previous copy must already be in the repository
    */
  def update(problem: Problem): Unit

  /**
    * Removes the problem having the given name
    *
    * @param name Name of the problem to remove
    */
  def removeByName(name: String): Unit


  /**
    * Removes all the problems from the repository
    */
  def removeAll()


  /**
    * Total count of problems in the repository
    *
    * @return
    */
  def count(): Long
}
