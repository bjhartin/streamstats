package org.banno.streamstats

import statistics._
import mutable.CurrentStats
import tweetprocessing._
import tweetprocessing.TweetProcessor
import twitter4j.{Status, StatusListener}
import org.mockito.Mockito._

class PerformanceSpec extends BaseSpec {
  behavior of "The system overall"

  def tweetInfoExtractors = List(new CountExtractor, new EmojiExtractor, new HashTagExtractor, new UrlExtractor)
  def tweetProcessor = new TweetProcessor(tweetInfoExtractors)
//  def statistics = List(new TotalTweets, new TopDomains, new TopEmojis, new TopHashtags, new TweetsWithEmojis, new TweetsWithUrls, new TweetsWithPhotoUrls)

  before {CurrentStats.reset}

  def status = {
    val mockTweet = mock[Status]
    when(mockTweet.getText).thenReturn("\u2708 and \u270F and #tag1 and #tag2 and http://url1 and http://url2")
    mockTweet
  }

  it should "warm things up" in {
    // The first runs are slower than subsequent runs due to initial class loading, JRE
    // optimizations, etc.
    runPerformanceTest(new NonBlockingStatusListener(tweetProcessor, Statistics.all), 50)
    CurrentStats.reset()
    runPerformanceTest(new BlockingStatusListener(tweetProcessor, Statistics.all), 50)
  }

  it should "perform well with the non-blocking listener" in {
    val tweetsPerSecond = runPerformanceTest(new NonBlockingStatusListener(tweetProcessor, Statistics.all), 200)
    tweetsPerSecond should be > 50.0
  }

  it should "perform well with the blocking listener" in {
    val tweetsPerSecond = runPerformanceTest(new BlockingStatusListener(tweetProcessor, Statistics.all), 200)
    tweetsPerSecond should be > 50.0
  }

  def runPerformanceTest(listener: StatusListener, numberOfTweets: Int):Double = {
    val tweets = (1 to numberOfTweets).map( i => status)
    val elapsed = benchmark(tweets.foreach(listener.onStatus(_)))

    CurrentStats.totalTweets should be(numberOfTweets)
    CurrentStats.tweetsWithEmojis should be(numberOfTweets)

    println("elapsed ms: " + elapsed)
    println("tweets/s: " + numberOfTweets / (elapsed / 1000))
    elapsed
  }
}
