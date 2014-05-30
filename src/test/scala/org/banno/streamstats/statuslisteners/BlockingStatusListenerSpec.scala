package org.banno.streamstats.statuslisteners

import org.banno.streamstats.BaseSpec
import org.banno.streamstats.tweetprocessing._
import twitter4j.Status
import org.mockito.Mockito._
import org.banno.streamstats.tweetprocessing.TweetProcessor

class BlockingStatusListenerSpec extends BaseSpec {
  behavior of "A blocking status listener"

  it should "process a Status" in {
    val tweetProcessor = mock[TweetProcessor]
    val statistic1 = mock[Function1[TweetInfo, Unit]]
    val status = mock[Status]
    val listener = new BlockingStatusListener(tweetProcessor, List(statistic1))
    val tweetInfo = mock[TweetInfo]

    when(tweetProcessor.process(status)).thenReturn(tweetInfo)

    listener.onStatus(status)

    verify(tweetProcessor).process(status)
    verify(statistic1).apply(tweetInfo)
  }
}
