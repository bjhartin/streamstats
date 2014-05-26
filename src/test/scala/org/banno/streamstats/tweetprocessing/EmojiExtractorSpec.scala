package org.banno.streamstats.tweetprocessing

import org.banno.streamstats.BaseSpec
import twitter4j.Status
import org.mockito.Mockito._

class EmojiExtractorSpec extends BaseSpec {
  behavior of "An EmojiExtractor"

  def extractor = new EmojiExtractor()

  it should "ensure JVM using UTF-8 encoding" in {
    System.getProperty("file.encoding") should be("UTF-8")
  }

  it should "extract all emojis from a tweet" in {
    val status = mock[Status]
    when(status.getText).thenReturn("This is a tweet with two emojis \u2708 and \u270F")
    val emoji1 = Emoji.find(0x2708.toChar).get
    val emoji2 = Emoji.find(0x270F.toChar).get
    extractor.extractInfo(status) should be(List(emoji1, emoji2))
  }

  it should "not fail when no emojis present" in {
    val status = mock[Status]
    when(status.getText).thenReturn("foo")
    extractor.extractInfo(status) should be(Nil)
  }

  it should "perform reasonably well" in {
    val status = mock[Status]
    val tweets = 100
    when(status.getText).thenReturn(("X" * 130) + "\u2708 and \u270F") // Use X*130 to get big tweet
    val tps = tweetsPerSecond(tweets, extractor.extractInfo(status))
    tps should be > 1000.0
    println(s"Tweets/s: $tps")
  }

}
