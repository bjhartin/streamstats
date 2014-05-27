package org.banno.streamstats

import statistics._
import tweetprocessing._
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration._
import twitter4j.Status

class NonBlockingStatusListenerSpec extends BaseSpec {
  def tweetInfoExtractors =
    List(new CountExtractor, new EmojiExtractor, new HashTagExtractor, new UrlExtractor)

  def tweetProcessor = new TweetProcessor(tweetInfoExtractors)

  def statistics = List(new TotalTweets, new TopDomains, new TopEmojis, new TopHashtags, new TweetsWithEmojis, new TweetsWithUrls, new TweetsWithPhotoUrls)

    it should "recompute all registered statistics when a tweet is received from the stream" in {
      val listener = new NonBlockingStatusListener(tweetProcessor, statistics)
      val tweet = mock[Status]

      CurrentStats.totalTweets should be(0) // Sanity check

      listener.onStatus(tweet)
      listener.onStatus(tweet)

      CurrentStats.totalTweets should be(2)
    }

    it should "run with the real statistics" in {
      val numberOfTweets = 200

      val listener = new NonBlockingStatusListener(tweetProcessor, statistics)
      val tweets = (1 to numberOfTweets).map( i => mock[Status])
      val elapsed = benchmark(tweets.foreach(listener.onStatus(_)))

      println("elapsed ms: " + elapsed)
      println("tweets/s: " + numberOfTweets / (elapsed / 1000))
    }

    it should "run with the real statistics 2" in {
      val numberOfTweets = 200

      val listener = new NonBlockingStatusListener(tweetProcessor, statistics)
      val tweets = (1 to numberOfTweets).map( i => mock[Status])
      val elapsed = benchmark(tweets.foreach(listener.onStatus(_)))

      println("elapsed ms: " + elapsed)
      println("tweets/s: " + numberOfTweets / (elapsed / 1000))
    }

    it should "run with some really slow stats" in {
      val numberOfTweets = 200
      val numberOfStats = 5
      val timeToComputeAStatInMs = 5
      val slowStatistics = (1 to numberOfStats).map(i => new Statistic {
        override def compute(tweetInfo:TweetInfo) = {
          Thread.sleep(timeToComputeAStatInMs)
        }
      }).toList
      val listener = new NonBlockingStatusListener(tweetProcessor, slowStatistics)
      val tweets = (1 to numberOfTweets).map( i => mock[Status])
      val elapsed = benchmark(tweets.foreach(listener.onStatus(_)))

      println("elapsed ms: " + elapsed)
      println("tweets/s: " + numberOfTweets / (elapsed / 1000))
    }

  def slow1 = {
    Thread.sleep(2000); 1
  }

  def slow2 = {
    Thread.sleep(2000); 2
  }

  it should "test async/await 1" in {
    val elapsed = benchmark {
      val r1 = slow1
      val r2 = slow2
    }
    println(elapsed)

  }

  it should "test async/await 2" in {
    val elapsed = benchmark {
      val f1 = Future {
        slow1
      }
      val f2 = Future {
        slow2
      }
    }
    println(elapsed)

  }

  it should "test async/await 3" in {
    val elapsed = benchmark {
      val f1 = Future {
        slow1
      }
      val f2 = Future {
        slow2
      }


      val r1 = Await.result(f1, 20 seconds)
      val r2 = Await.result(f2, 20 seconds)
      println(r1 + r2)
    }
    println(elapsed)
  }

  it should "test async/await 5" in {
    val elapsed = benchmark {
      val l = List(Future {
        slow1
      }, Future {
        slow2
      })

      val r = l.map(Await.result(_, 20 seconds))
      r.foreach(println)
    }

    println(elapsed)
  }

}
