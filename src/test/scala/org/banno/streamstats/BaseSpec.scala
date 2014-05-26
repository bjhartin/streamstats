package org.banno.streamstats

import org.scalatest.{Matchers, FlatSpec}
import org.scalatest.mock.MockitoSugar

class BaseSpec extends FlatSpec with Matchers with MockitoSugar
{
  def benchmark(f: => Any):Double = {
    val start = System.nanoTime()
    f
    val end = System.nanoTime()
    (end - start) / 1e6
  }

  def tweetsPerSecond(tweets: Int, f: => Any):Double = {
    val elapsed = benchmark {
      (1 to tweets).foreach {i =>
        f
      }
    }
    tweets / (elapsed / 1000)
  }
}
