package org.banno.streamstats

import statistics.{TotalTweets, Statistic}
import twitter4j.Status

class NonBlockingStatusListenerSpec extends BaseSpec {
  it should "recompute all registered statistics when a tweet is received from the stream" in {
    val totalTweets = new TotalTweets()
    val stats = List(totalTweets)
    val listener = new NonBlockingStatusListener(stats)
    val tweet = mock[Status]

    totalTweets.value should be(0) // Sanity check

    listener.onStatus(tweet)
    listener.onStatus(tweet)

    totalTweets.value should be(2)
  }

  it should "scale well since it is non-blocking" in {
    val numberOfStats = 10
    val numberOfTweets = 50
    val timeToComputeAStatInMs = 10

    val stats = (1 to numberOfStats).map(i => new Statistic {
      override def compute(status:Status) = {
        Thread.sleep(timeToComputeAStatInMs)
      }
    }).toList

    // Depends on # of CPU cores, etc.
    // val expectedTime = (numberOfStats * numberOfTweets * timeToComputeAStatInMs).toDouble

    val listener = new NonBlockingStatusListener(stats)
    val tweets = (1 to numberOfTweets).map( i => mock[Status])
    val elapsed = benchmark(tweets.foreach(listener.onStatus(_)))
    listener.computed should be(numberOfStats * numberOfTweets)

    println("elapsed ms: " + elapsed)
    println("tweets/s: " + numberOfTweets / (elapsed / 1000))
  }
}
