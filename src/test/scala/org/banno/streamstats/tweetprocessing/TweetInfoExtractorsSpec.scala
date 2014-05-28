package org.banno.streamstats.tweetprocessing

import org.banno.streamstats.BaseSpec
import twitter4j.Status
import org.mockito.Mockito._

class TweetInfoExtractorsSpec extends BaseSpec {

  it should "return 1 for the count" in {
    TweetInfoExtractor.count.extract(mock[Status]) should be(1)
  }

  it should "ensure JVM using UTF-8 encoding" in {
    System.getProperty("file.encoding") should be("UTF-8")
  }

  it should "extract all emojis from a tweet" in {
    val status = mock[Status]
    when(status.getText).thenReturn("This is a tweet with two emojis \u2708 and \u270F")
    val emoji1 = Emoji.find(0x2708.toChar).get
    val emoji2 = Emoji.find(0x270F.toChar).get
    TweetInfoExtractor.emoji.extract(status) should be(List(emoji1, emoji2))
  }

  it should "not fail when no emojis present" in {
    val status = mock[Status]
    when(status.getText).thenReturn("foo")
    TweetInfoExtractor.emoji.extract(status) should be(Nil)
  }

  it should "extract all hash tags from a tweet using Twitter's text extractor" in {
    val status = mock[Status]
    when(status.getText()).thenReturn("This is a #test which is #notreal")
    TweetInfoExtractor.hashtags.extract(status) should be(List("test", "notreal"))
  }

  it should "not fail when no hash tags present" in {
    val status = mock[Status]
    when(status.getText()).thenReturn("foo")
    TweetInfoExtractor.hashtags.extract(status) should be(Nil)
  }

  it should "extract all urls from a tweet using Twitter's text extractor" in {
    val status = mock[Status]
    when(status.getText()).thenReturn("http://google.com and http://bing.com are search engines")
    TweetInfoExtractor.urls.extract(status) should be(List("http://google.com", "http://bing.com"))
  }

  it should "not fail when no urls present" in {
    val status = mock[Status]
    when(status.getText()).thenReturn("foo")
    TweetInfoExtractor.urls.extract(status) should be(Nil)
  }
}
