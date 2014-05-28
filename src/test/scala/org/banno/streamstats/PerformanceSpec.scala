package org.banno.streamstats

import statistics._
import mutable.CurrentStats
import tweetprocessing._
import tweetprocessing.TweetProcessor
import twitter4j.{TwitterStreamFactory, Status, StatusListener}
import org.mockito.Mockito._

class PerformanceSpec extends BaseSpec {
  behavior of "The system overall"

  def tweetInfoExtractors = List(new CountExtractor, new EmojiExtractor, new HashTagExtractor, new UrlExtractor)
  def tweetProcessor = new TweetProcessor(tweetInfoExtractors)

  before {CurrentStats.reset}

  def status = {
    val mockTweet = mock[Status]
    when(mockTweet.getText).thenReturn("\u2708 and \u270F and #tag1 and #tag2 and http://google.com and http://instagram.com/pic1")
    mockTweet
  }

  it should "warm things up" in {
    // The first runs are slower than subsequent runs due to initial class loading, JRE
    // optimizations, etc.
    runPerformanceTest(new NonBlockingStatusListener(tweetProcessor, Metrics.all), 50)
    CurrentStats.reset()
    runPerformanceTest(new BlockingStatusListener(tweetProcessor, Metrics.all), 50)
  }

  it should "perform well with the non-blocking listener" in {
    val tweetsPerSecond = runPerformanceTest(new NonBlockingStatusListener(tweetProcessor, Metrics.all), 200)
    tweetsPerSecond should be > 50.0
  }

  it should "perform well with the blocking listener" in {
    val tweetsPerSecond = runPerformanceTest(new BlockingStatusListener(tweetProcessor, Metrics.all), 200)
    tweetsPerSecond should be > 50.0
  }

  it should "perform well with the non-blocking listener with the real stream" in {
    val twitterStream = new TwitterStreamFactory(Configuration.create).getInstance
    twitterStream.addListener(new BlockingStatusListener(tweetProcessor, Metrics.all))
    println("Beginning to sample for ten seconds...")
    twitterStream.sample
    Thread.sleep(10000)
    println("Cleaning up and shutting down")
    twitterStream.cleanUp
    twitterStream.shutdown
    println("Done")
    println(CurrentStats.toString())

  }

  def runPerformanceTest(listener: StatusListener, numberOfTweets: Int):Double = {
    val tweets = (1 to numberOfTweets).map( i => status)
    val elapsed = benchmark(tweets.foreach(listener.onStatus(_)))

    CurrentStats.totalTweets should be(numberOfTweets)
    CurrentStats.tweetsWithEmojis should be(numberOfTweets)
    CurrentStats.tweetsWithUrls should be(numberOfTweets)
    CurrentStats.tweetsWithPhotoUrls should be(numberOfTweets)
    CurrentStats.emojiFrequency(Emoji.allEmoji(0x2708)) should be(numberOfTweets)
    CurrentStats.emojiFrequency(Emoji.allEmoji(0x270F)) should be(numberOfTweets)
    CurrentStats.hashTagFrequency("tag1") should be(numberOfTweets)
    CurrentStats.hashTagFrequency("tag2") should be(numberOfTweets)
    CurrentStats.domainFrequency("google.com") should be(numberOfTweets)
    CurrentStats.domainFrequency("instagram.com") should be(numberOfTweets)


    println("elapsed ms: " + elapsed)
    println("tweets/s: " + numberOfTweets / (elapsed / 1000))
    elapsed
  }
}
