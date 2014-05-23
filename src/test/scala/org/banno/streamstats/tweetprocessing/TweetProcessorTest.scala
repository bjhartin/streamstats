package org.banno.streamstats.tweetprocessing

import org.banno.streamstats.BaseSpec
import twitter4j.Status
import org.mockito.Mockito._
import scala.concurrent.duration._
import concurrent.Await

class TweetProcessorTest extends BaseSpec {
  behavior of "A tweet processor"

  it should "return futures which invoke the registered tweet info extractors" in {
    val extractors = List(mock[TweetInfoExtractor], mock[TweetInfoExtractor])
    val processor = new TweetProcessor(extractors)
    val status = mock[Status]
    val futures = processor.process(status)

    futures.size should be(extractors.size)
    futures.foreach(Await.result(_, 1 second))


    extractors.foreach(verify(_).extractInfo(status))
  }
}
