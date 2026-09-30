package info.gianlucacosta.twobinpack.core

import info.gianlucacosta.twobinpack.test.SimpleTestData.{ProblemA, ProblemB}
import org.scalatest.{FlatSpec, Matchers}


class TestProblemBundle extends FlatSpec with Matchers {
  "An empty problem bundle" should "be valid" in {
    ProblemBundle(List())
  }


  "A problem bundle with one problem" should "be valid" in {
    ProblemBundle(List(ProblemA))
  }


  "A problem bundle with two problems" should "be valid" in {
    ProblemBundle(List(
      ProblemA,
      ProblemB
    ))
  }


  "A problem bundle with a duplicated problem" should "NOT be valid" in {
    intercept[IllegalArgumentException] {
      ProblemBundle(List(
        ProblemA,
        ProblemB,
        ProblemA
      ))
    }
  }
}
