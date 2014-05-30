package org.banno.streamstats.statuslisteners

import org.banno.streamstats.BaseSpec
import org.banno.streamstats.tweetprocessing._
import twitter4j.Status
import org.mockito.Mockito._
import org.banno.streamstats.tweetprocessing.TweetProcessor

class ParallelizedStatusListenerSpec extends BaseSpec {
  behavior of "A ParallelizedStatusListenerSpec"

  it should "process a Status" in {
    val tweetProcessor = mock[TweetProcessor]
    val statistic1 = mock[Function1[TweetInfo, Unit]]
    val status = mock[Status]
    val listener = new ParallelizedStatusListener(tweetProcessor, List(statistic1))
    val tweetInfo = mock[TweetInfo]

    when(tweetProcessor.process(status)).thenReturn(tweetInfo)

    listener.onStatus(status)

    verify(tweetProcessor).process(status)
    verify(statistic1).apply(tweetInfo)
  }

  it should "throw an error which occurs during parallel processing" in {
    val tweetProcessor = mock[TweetProcessor]
    val statistic1 = mock[Function1[TweetInfo, Unit]]
    val status = mock[Status]
    val tweetInfo = mock[TweetInfo]
    val listener = new ParallelizedStatusListener(tweetProcessor, List(statistic1))

    when(tweetProcessor.process(status)).thenReturn(tweetInfo)
    when(statistic1.apply(tweetInfo)).thenThrow(new IllegalStateException("Boom!"))

    val thrown = intercept[IllegalStateException] {
      listener.onStatus(status)
    }
    thrown.getMessage should be("Boom!")
  }
}
