package org.banno.streamstats.tweetprocessing

import org.banno.streamstats.BaseSpec
import twitter4j.Status
import twitter4j.URLEntity
import org.mockito.Mockito._

class TweetInfoExtractorSpec extends BaseSpec {
  it should "return 1 for the count" in {
    TweetInfoExtractor.count.extract(mock[Status]) should be(1)
  }

  it should "extract all emoji from a tweet" in {
    val status = mock[Status]
    when(status.getText).thenReturn("This is a tweet with two emoji \u2708 and \u270F")
    val emoji1 = Emoji.find(0x2708.toChar).get
    val emoji2 = Emoji.find(0x270F.toChar).get
    TweetInfoExtractor.emoji.extract(status) should be(List(emoji1, emoji2))
  }

  it should "not fail when no emoji present" in {
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
    val urlEntity1 = mock[URLEntity]
    when(urlEntity1.getExpandedURL).thenReturn("http://google.com")
    val urlEntity2 = mock[URLEntity]
    when(urlEntity2.getExpandedURL).thenReturn("http://bing.com")

    val urlEntities = Array(urlEntity1, urlEntity2)
    when(status.getURLEntities()).thenReturn(urlEntities)
    TweetInfoExtractor.urls.extract(status) should be(List("http://google.com", "http://bing.com"))
  }

  it should "not fail when no urls present" in {
    val status = mock[Status]
    when(status.getURLEntities()).thenReturn(Array[URLEntity]())
    TweetInfoExtractor.urls.extract(status) should be(Nil)
  }
}
