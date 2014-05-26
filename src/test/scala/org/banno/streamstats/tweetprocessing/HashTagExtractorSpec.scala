package org.banno.streamstats.tweetprocessing

import org.banno.streamstats.BaseSpec
import twitter4j.Status
import org.mockito.Mockito._

class HashTagExtractorSpec extends BaseSpec {
  behavior of "A HashTagExtractor"

  def extractor = new HashTagExtractor()

  // By using Twitter's text tag extraction API, I believe I can avoid writing a lot of
  // edge-case tests for hashtag formats.  If interested, you can see the details (and tests)
  // of hashtag format details at
  // https://github.com/twitter/twitter-text-java/tree/master/tests/com/twitter.

  it should "extract all hash tags from a tweet using Twitter's text extractor" in {
    val status = mock[Status]
    when(status.getText()).thenReturn("This is a #test which is #notreal")
    extractor.extractInfo(status) should be(List("test", "notreal"))
  }

  it should "not fail when no hash tags present" in {
    val status = mock[Status]
    when(status.getText()).thenReturn("foo")
    extractor.extractInfo(status) should be(Nil)
  }

  it should "perform reasonably well" in {
    val status = mock[Status]
    val tweets = 10000
    when(status.getText()).thenReturn("This is a #test which is #notreal")

    val tps = tweetsPerSecond(tweets, extractor.extractInfo(status))
    tps should be > 1000.0
    println(s"Tweets/s: $tps")
  }
}
