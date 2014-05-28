package org.banno.streamstats.tweetprocessing

import org.banno.streamstats.BaseSpec
import twitter4j.Status
import org.mockito.Mockito._
import scala.concurrent.duration._
import concurrent.Await

class TweetProcessorSpec extends BaseSpec {
  behavior of "A tweet processor"

  it should "invoke the extractors and return the result" in {
    val extractors = List(mock[TweetInfoExtractor], mock[TweetInfoExtractor])
    val processor = new TweetProcessor(extractors)
    val status = mock[Status]
    val tweetInfo = processor.process(status)

    tweetInfo.size should be(extractors.size)
    extractors.foreach(verify(_).extract(status))
  }

  it should "scale well since it is non-blocking" in {
    val numberOfExtractors = 10
    val numberOfTweets = 200
    val timeToExtractInfoInMs = 1

    val extractors = (1 to numberOfExtractors).map(i => new TweetInfoExtractor("extractor", status =>
      Thread.sleep(timeToExtractInfoInMs)
    )).toList

    val processor = new TweetProcessor(extractors)
    val tweets = (1 to numberOfTweets).map( i => mock[Status])
    val elapsed = benchmark({
      tweets.foreach(processor.process(_))
    })

    println("elapsed ms: " + elapsed)
    println("tweets/s: " + numberOfTweets / (elapsed / 1000))

  }
}
