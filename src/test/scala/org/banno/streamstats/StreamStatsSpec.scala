package org.banno.streamstats

class StreamStatsSpec extends BaseSpec {
  it should "throw error if JVM not using UTF-8 encoding" in {
    withEncoding("Other", () => {
      val thrown = the [IllegalStateException] thrownBy StreamStats.main(Array())
      thrown.getMessage should be("file.encoding must be UTF-8")
    })
  }

  it should "Be happy with UTF-8 encoding" in {
    withEncoding("UTF-8", () => {
      StreamStats.main(Array())
    })
  }

  private def withEncoding(encoding:String, f: () => Any) {
    val oldEncoding = System.getProperty("file.encoding")
    try {
      System.setProperty("file.encoding", encoding)
      f()
    } finally {
      System.setProperty("file.encoding", oldEncoding)
    }
  }

//  it should "report an exception that occurs while processing a status" in {
//    val tweetProcessor = mock[TweetProcessor]
//    val metric1 = mock[Function1[TweetInfo, Unit]]
//    val status = mock[Status]
//    val listener = new NonBlockingStatusListener(tweetProcessor, List(metric1))
//    val tweetInfo = mock[TweetInfo]
//
//    // Need to delay the response of the tweet processor so that we can
//    // prove the method is non-blocking
//    when(tweetProcessor.process(status)).thenThrow(new IllegalStateException("Boom!"))
//
//    listener.onStatus(status)
//
//    // wait for the completion
//    val thrown = intercept[IllegalStateException] {
//      Await.result(Future.sequence(listener.statusProcessingFutures), Duration.Inf)
//    }
//    thrown.getMessage should be("Boom!")
//
//    verify(tweetProcessor).process(status)
//  }

}
