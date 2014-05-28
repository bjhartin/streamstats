package org.banno.streamstats

import statistics._
import mutable.CurrentStats
import tweetprocessing._
import org.mockito.Mockito._
import twitter4j.Status

class NonBlockingStatusListenerSpec extends BaseSpec {
  def tweetInfoExtractors =
    List(new CountExtractor, new EmojiExtractor, new HashTagExtractor, new UrlExtractor)

  def tweetProcessor = new TweetProcessor(tweetInfoExtractors)

  def status = {
    val mockTweet = mock[Status]
    when(mockTweet.getText).thenReturn("\u2708 and \u270F and #tag1 and #tag2 and http://url1 and http://url2")
    mockTweet
  }

  def statistics = List(new TotalTweets, new TopDomains, new TopEmojis, new TopHashtags, new TweetsWithEmojis, new TweetsWithUrls, new TweetsWithPhotoUrls)

  it should "recompute all registered statistics when a tweet is received from the stream" in {
    val listener = new NonBlockingStatusListener(tweetProcessor, statistics)
    CurrentStats.totalTweets should be(0) // Sanity check

    listener.onStatus(status)
    listener.onStatus(status)

    CurrentStats.totalTweets should be(2)
  }

  it should "run with the real statistics" in {
    val numberOfTweets = 200
    val listener = new NonBlockingStatusListener(tweetProcessor, statistics)
    val tweets = (1 to numberOfTweets).map(i => status)
    val elapsed = benchmark(tweets.foreach(listener.onStatus(_)))

    println("elapsed ms: " + elapsed)
    println("tweets/s: " + numberOfTweets / (elapsed / 1000))
  }

  it should "run with the real statistics 2" in {
    val numberOfTweets = 200
    val listener = new NonBlockingStatusListener(tweetProcessor, statistics)
    val tweets = (1 to numberOfTweets).map(i => status)
    val elapsed = benchmark(tweets.foreach(listener.onStatus(_)))

    println("elapsed ms: " + elapsed)
    println("tweets/s: " + numberOfTweets / (elapsed / 1000))
  }

//  it should "run with some really slow stats" in {
//    val numberOfTweets = 200
//    val numberOfStats = 5
//    val timeToComputeAStatInMs = 5
//    val slowStatistics = (1 to numberOfStats).map(i => new Statistic {
//      override def compute(tweetInfo: TweetInfo) = {
//        Thread.sleep(timeToComputeAStatInMs)
//      }
//    }).toList
//    val listener = new NonBlockingStatusListener(tweetProcessor, slowStatistics)
//    val tweets = (1 to numberOfTweets).map(i => status)
//    val elapsed = benchmark(tweets.foreach(listener.onStatus(_)))
//
//    println("elapsed ms: " + elapsed)
//    println("tweets/s: " + numberOfTweets / (elapsed / 1000))
//  }
}
