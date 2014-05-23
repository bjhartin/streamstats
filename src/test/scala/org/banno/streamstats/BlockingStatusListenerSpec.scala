package org.banno.streamstats

import statistics.{Statistic, TotalTweets, BlockingStatusListener}
import twitter4j.Status
import org.scalatest.{Matchers, FlatSpec}
import org.scalatest.mock.MockitoSugar

class BlockingStatusListenerSpec extends BaseSpec {
  behavior of "A blocking status listener"

  it should "recompute all registered statistics when a tweet is received from the stream" in {
    val totalTweets = new TotalTweets()
    val stats = List(totalTweets)
    val listener = new BlockingStatusListener(stats)
    val tweet = mock[Status]

    totalTweets.value should be(0) // Sanity check

    listener.onStatus(tweet)
    listener.onStatus(tweet)

    totalTweets.value should be(2)
  }

  it should "probably not scale very well since it blocks until stats are computed" in {
    val numberOfStats = 10
    val numberOfTweets = 50
    val timeToComputeAStatInMs = 10
    var statsComputed = 0

    val stats = (1 to numberOfStats).map(i => new Statistic {
      override def compute(status:Status) = {
        Thread.sleep(timeToComputeAStatInMs)
        statsComputed += 1
      }
    }).toList

    // Way too long.
    val expectedTime = (numberOfStats * numberOfTweets * timeToComputeAStatInMs).toDouble


    val listener = new BlockingStatusListener(stats)
    val tweets = (1 to numberOfTweets).map( i => mock[Status])
    val elapsed = benchmark(tweets.foreach(listener.onStatus(_)))
    statsComputed should be(numberOfStats * numberOfTweets)
    println(elapsed)
    elapsed should be(expectedTime +- (expectedTime * 0.2)) // 20% variance allowed before test fails
  }
}
