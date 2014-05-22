package org.banno.streamstats

import statistics.{TotalTweets, BlockingStatusListener}
import twitter4j.Status
import org.scalatest.{Matchers, FlatSpec}
import org.scalatest.mock.MockitoSugar

class BlockingStatusListenerSpec extends BaseSpec {
  behavior of "A blocking status listener"

  it should "recompute all registered statistics when a tweet is received from the stream" in {
    val totalTweets = new TotalTweets()
    val stats = List(totalTweets)
    val listener = new BlockingStatusListener(stats)
    val status = mock[Status]

    totalTweets.value should be(0) // Sanity check

    listener.onStatus(status)
    listener.onStatus(status)

    totalTweets.value should be(2)
  }
}
