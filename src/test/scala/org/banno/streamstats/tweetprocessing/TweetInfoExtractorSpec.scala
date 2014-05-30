package org.banno.streamstats.tweetprocessing

import org.banno.streamstats.BaseSpec
import twitter4j.{HashtagEntity, Status, URLEntity}
import org.mockito.Mockito._
import scala.Array

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
    val hashtagEntity1 = mock[HashtagEntity]
    when(hashtagEntity1.getText).thenReturn("test")
    val hashtagEntity2 = mock[HashtagEntity]
    when(hashtagEntity2.getText).thenReturn("notreal")
    when(status.getHashtagEntities()).thenReturn(Array(hashtagEntity1, hashtagEntity2))
    TweetInfoExtractor.hashtags.extract(status) should be(List("test", "notreal"))
  }

  it should "not fail when no hash tags present" in {
    val status = mock[Status]
    when(status.getHashtagEntities()).thenReturn(Array[HashtagEntity]())
    TweetInfoExtractor.hashtags.extract(status) should be(Nil)
  }

  it should "not fail when hash tags are null" in { // Can happen due to twitter API internals
    val status = mock[Status]
    when(status.getHashtagEntities()).thenReturn(null)
    TweetInfoExtractor.hashtags.extract(status) should be(Nil)
  }

  it should "extract all urls from a tweet" in {
    val status = mock[Status]
    val urlEntity1 = mock[URLEntity]
    when(urlEntity1.getExpandedURL).thenReturn("http://google.com")
    val urlEntity2 = mock[URLEntity]
    when(urlEntity2.getExpandedURL).thenReturn("http://bing.com")


    when(status.getURLEntities()).thenReturn(Array(urlEntity1, urlEntity2))
    TweetInfoExtractor.urls.extract(status) should be(List("http://google.com", "http://bing.com"))
  }

  it should "not fail when no urls present" in {
    val status = mock[Status]
    when(status.getURLEntities()).thenReturn(Array[URLEntity]())
    TweetInfoExtractor.urls.extract(status) should be(Nil)
  }

  it should "not fail when urls are null" in { // Can happen due to twitter API internals
    val status = mock[Status]
    when(status.getURLEntities()).thenReturn(null)
    TweetInfoExtractor.urls.extract(status) should be(Nil)
  }
}
