package org.banno.streamstats.tweetprocessing

import org.banno.streamstats.BaseSpec
import twitter4j.Status
import org.mockito.Mockito._
import scala.concurrent.duration._
import concurrent.Await

class TweetProcessorTest extends BaseSpec {
  behavior of "A tweet processor"

  it should "return futures which invoke the registered tweet info extractors" in {
    val extractors = List(mock[CountExtractor], mock[EmojiExtractor])
    val processor = new TweetProcessor(extractors)
    val status = mock[Status]
    val tweetInfo = processor.process(status)
    val futures = tweetInfo.values

    futures.size should be(extractors.size)
    futures.foreach(Await.result(_, 1 second))
    extractors.foreach(verify(_).extractInfo(status))
  }

  it should "scale well since it is non-blocking" in {
    val numberOfExtractors = 10
    val numberOfTweets = 50
    val timeToExtractInfoInMs = 10

    val extractors = (1 to numberOfExtractors).map(i => new TweetInfoExtractor {
      override def extractInfo(status:Status):Any = {
        Thread.sleep(timeToExtractInfoInMs)
        1
      }
    }).toList

    // Depends on # of CPU cores, etc.
    // val expectedTime = (numberOfStats * numberOfTweets * timeToComputeAStatInMs) / ?

    val processor = new TweetProcessor(extractors)
    val tweets = (1 to numberOfTweets).map( i => mock[Status])
    val elapsed = benchmark({
      val results = tweets.map(processor.process(_))
      results.foreach(r => r.values.foreach(Await.result(_, 200 seconds)))
    })

    println("elapsed ms: " + elapsed)
    println("tweets/s: " + numberOfTweets / (elapsed / 1000))

    }
}
