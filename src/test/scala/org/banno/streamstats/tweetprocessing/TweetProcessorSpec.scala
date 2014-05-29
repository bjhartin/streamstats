package org.banno.streamstats.tweetprocessing

import org.banno.streamstats.BaseSpec
import twitter4j.Status
import org.mockito.Mockito._
import scala.concurrent.duration._
import concurrent.Await

class TweetProcessorSpec extends BaseSpec {
  behavior of "A tweet processor"

  it should "invoke the extractors and return the result" in {
    val extractors = List(mock[TweetInfoExtractor])
    val processor = new TweetProcessor(extractors)
    val status = mock[Status]
    val tweetInfo = processor.process(status)

    extractors.foreach(verify(_).extract(status))
    tweetInfo.size should be(1)
  }
}
