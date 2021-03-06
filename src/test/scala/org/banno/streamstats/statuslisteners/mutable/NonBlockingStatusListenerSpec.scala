package org.banno.streamstats.statuslisteners.mutable

import org.banno.streamstats.BaseSpec
import org.banno.streamstats.tweetprocessing.TweetInfo
import twitter4j.Status
import org.mockito.Mockito._
import org.banno.streamstats.tweetprocessing.TweetProcessor
import concurrent.{ExecutionContext, Await, Future}
import concurrent.duration.Duration
import ExecutionContext.Implicits.global
import org.mockito.stubbing.Answer
import org.mockito.invocation.InvocationOnMock
import org.banno.streamstats.statuslisteners.ParallelizedStatusListener

class NonBlockingStatusListenerSpec extends BaseSpec {
  behavior of "A non-blocking StatusListener"

  it should "process a Status without blocking" in {
    val tweetProcessor = mock[TweetProcessor]
    val statistic1 = mock[Function1[TweetInfo, Unit]]
    val status = mock[Status]
    val listener = new NonBlockingStatusListener(tweetProcessor, List(statistic1))
    val tweetInfo = mock[TweetInfo]

    // Need to delay the response of the tweet processor so that we can
    // prove the method is non-blocking
    when(tweetProcessor.process(status)).thenAnswer(new Answer[TweetInfo]() {
      override def answer(invocationOnMock:InvocationOnMock):TweetInfo = {
        Thread.sleep(500)
        tweetInfo
      }
    })

    listener.onStatus(status)

    // onStatus returned without blocking
    listener.statusProcessingFutures.head.isCompleted should be(false)
    verify(statistic1, never()).apply(tweetInfo)

    listener.waitForCompletion()

    verify(tweetProcessor).process(status)
    verify(statistic1).apply(tweetInfo)
  }

  it should "throw errors from futures" in {
    val tweetProcessor = mock[TweetProcessor]
    val statistic1 = mock[Function1[TweetInfo, Unit]]
    val status = mock[Status]
    val tweetInfo = mock[TweetInfo]
    val listener = new NonBlockingStatusListener(tweetProcessor, List(statistic1))

    when(tweetProcessor.process(status)).thenReturn(tweetInfo)
    when(statistic1.apply(tweetInfo)).thenThrow(new IllegalStateException("Boom!"))

    val thrown = intercept[IllegalStateException] {
      listener.onStatus(status)
      listener.waitForCompletion()
    }
    thrown.getMessage should be("Boom!")
  }
}
