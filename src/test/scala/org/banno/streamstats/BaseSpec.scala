package org.banno.streamstats

import org.scalatest.{BeforeAndAfter, Matchers, FlatSpec}
import org.scalatest.mock.MockitoSugar

class BaseSpec extends FlatSpec with Matchers with MockitoSugar with BeforeAndAfter
{
  def benchmark(f: => Any):Double = {
    val start = System.nanoTime()
    f
    val end = System.nanoTime()
    (end - start) / 1e6
  }

  def opsPerSecond(ops: Int, f: => Any):Double = {
    val elapsed = benchmark {
      (1 to ops).foreach {i =>
        f
      }
    }
    ops / (elapsed / 1000)
  }
}
