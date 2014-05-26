package org.banno.streamstats.tweetprocessing

import org.banno.streamstats.BaseSpec
import twitter4j.Status
import org.mockito.Mockito._

class UrlProcessorSpec extends BaseSpec {
  behavior of "A UrlExtractor"

  def extractor = new UrlExtractor()

  // By using Twitter's text extraction API, I believe I can avoid writing a lot of
  // edge-case tests for url formats.  If interested, you can see the details (and tests)
  // of url format details at
  // https://github.com/twitter/twitter-text-java/tree/master/tests/com/twitter.

  it should "extract all urls from a tweet using Twitter's text extractor" in {
    val status = mock[Status]
    when(status.getText()).thenReturn("http://google.com and http://bing.com are search engines")
    extractor.extractInfo(status) should be(List("http://google.com", "http://bing.com"))
  }

  it should "not fail when no urls present" in {
    val status = mock[Status]
    when(status.getText()).thenReturn("foo")
    extractor.extractInfo(status) should be(Nil)
  }
}
