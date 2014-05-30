package org.banno.streamstats

import statistics._
import mutable.CurrentStats
import statuslisteners.BlockingStatusListener
import statuslisteners.mutable.NonBlockingStatusListener
import statuslisteners.mutable.NonBlockingStatusListener
import statuslisteners.ParallelizedStatusListener
import statuslisteners.{ParallelizedStatusListener, BlockingStatusListener}
import tweetprocessing._
import tweetprocessing.TweetProcessor
import tweetprocessing.TweetProcessor
import twitter4j._
import org.mockito.Mockito._

class PerformanceSpec extends BaseSpec {
  behavior of "Performance benchmarks"

  def tweetProcessor = new TweetProcessor(TweetInfoExtractor.all)
  def hashTagEntities(hashtags:List[String]) = {
    hashtags.map(tag => {
      val mockHashtagEntity = mock[HashtagEntity]
      when(mockHashtagEntity.getText).thenReturn(tag)
      mockHashtagEntity
    }).toArray
  }

  def urlEntities(urls:List[String])={
    urls.map(url => {
      val mockUrlEntity = mock[URLEntity]
      when(mockUrlEntity.getExpandedURL).thenReturn(url)
      mockUrlEntity
    }).toArray
  }

  before {CurrentStats.reset()}

  def status = {
    val mockTweet = mock[Status]
    when(mockTweet.getText).thenReturn("\u2708 and \u270F and #tag1 and #tag2 and http://google.com and http://instagram.com/pic1")

    val urls = urlEntities(List("http://google.com", "http://instagram.com/pic1"))
    val hashtags = hashTagEntities(List("tag1", "tag2"))

    when(mockTweet.getURLEntities).thenReturn(urls)
    when(mockTweet.getHashtagEntities).thenReturn(hashtags)
    mockTweet
  }

  it should "benchmark the blocking listener" in {
    val tweetsPerSecond = runPerformanceTest(new BlockingStatusListener(tweetProcessor, Statistics.all), 200)
    tweetsPerSecond should be > 100.0
  }

  it should "benchmark the parallelized listener" in {
    val tweetsPerSecond = runPerformanceTest(new ParallelizedStatusListener(tweetProcessor, Statistics.all), 200)
    tweetsPerSecond should be > 100.0
  }

  it should "benchmark the non-blocking listener" in {
    val listener = new NonBlockingStatusListener(tweetProcessor, Statistics.all)
    val tweetsPerSecond = runPerformanceTest(listener, 200, listener.waitForCompletion _)
    tweetsPerSecond should be > 100.0
  }

  it should "benchmark the blocking listener with slow metrics" in {
    val tweetsPerSecond = runPerformanceTest(new BlockingStatusListener(tweetProcessor, slowMetrics(5)), 200)
    tweetsPerSecond should be > 30.0
  }

  it should "benchmark the parallelized listener with slow metrics" in {
    val tweetsPerSecond = runPerformanceTest(new ParallelizedStatusListener(tweetProcessor, slowMetrics(5)), 200)
    tweetsPerSecond should be > 100.0
  }

  it should "benchmark the non-blocking listener with slow metrics" in {
    val listener = new NonBlockingStatusListener(tweetProcessor, slowMetrics(5))
    val tweetsPerSecond = runPerformanceTest(listener, 200, listener.waitForCompletion _)
    tweetsPerSecond should be > 30.0
  }

  it should "benchmark the blocking listener with the real stream" in {
    val tweetsPerSecond = testAgainstRealStream(new BlockingStatusListener(tweetProcessor, Statistics.all))
    tweetsPerSecond should be > 20.0
  }

  it should "benchmark the parallelized listener with the real stream" in {
    val tweetsPerSecond = testAgainstRealStream(new ParallelizedStatusListener(tweetProcessor, Statistics.all))
    tweetsPerSecond should be > 20.0
  }

  it should "benchmark the non-blocking listener with the real stream" in {
    val listener = new NonBlockingStatusListener(tweetProcessor, Statistics.all)
    val tweetsPerSecond = testAgainstRealStream(listener, listener.waitForCompletion _)
    tweetsPerSecond should be > 20.0
  }

  def runPerformanceTest(listener: StatusListener, numberOfTweets: Int, wait: () => Any = () => {}):Double = {
    val tweets = (1 to numberOfTweets).map( i => status)
    val elapsed = benchmark({
      tweets.foreach(listener.onStatus(_))
      wait()
    })

    CurrentStats.totalTweets should be(numberOfTweets)
    CurrentStats.tweetsWithEmoji should be(numberOfTweets)
    CurrentStats.tweetsWithUrls should be(numberOfTweets)
    CurrentStats.tweetsWithPhotoUrls should be(numberOfTweets)
    CurrentStats.emojiFrequency(Emoji.allEmoji(0x2708)) should be(numberOfTweets)
    CurrentStats.emojiFrequency(Emoji.allEmoji(0x270F)) should be(numberOfTweets)
    CurrentStats.hashtagFrequency("tag1") should be(numberOfTweets)
    CurrentStats.hashtagFrequency("tag2") should be(numberOfTweets)
    CurrentStats.domainFrequency("google.com") should be(numberOfTweets)
    CurrentStats.domainFrequency("instagram.com") should be(numberOfTweets)

    println("elapsed ms: " + elapsed)
    println("tweets/s: " + CurrentStats.tweetsPerSecond())
    CurrentStats.tweetsPerSecond()
  }

  def testAgainstRealStream(listener: StatusListener, wait: () => Any = () => {}):Double = {
    val elapsed = benchmark {
      val twitterStream = new TwitterStreamFactory(Configuration.create).getInstance
      twitterStream.addListener(listener)
      println("Beginning to sample for five seconds...")
      twitterStream.sample()
      Thread.sleep(5000)
      println("Cleaning up and shutting down")
      twitterStream.cleanUp()
      twitterStream.shutdown()
      println("Done")
      wait()
    }

    println("elapsed ms: " + elapsed)
    println("tweets/s: " + CurrentStats.tweetsPerSecond())
    println("results:\n\n")
    println(CurrentStats.toString())
    CurrentStats.tweetsPerSecond()
  }

  private def slowMetrics(metricOverheadInMs:Int):List[Stat] = {
    Statistics.all.map(f => (ti: TweetInfo) => {
      Thread.sleep(metricOverheadInMs)
      f(ti)
    })
  }
}
