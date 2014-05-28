package org.banno.streamstats.statistics

import mutable.CurrentStats
import org.banno.streamstats.BaseSpec
import org.banno.streamstats.tweetprocessing._

class MetricsSpec extends BaseSpec {
  behavior of "Metrics functions"

  before {CurrentStats.reset}

  def tweetInfo(emoji:List[Emoji] = Nil, urls:List[String] = Nil, hashtags: List[String] = Nil) = {
    Map("emoji" -> emoji,
        "urls" -> urls,
        "hashtags" -> hashtags
    )
  }

  it should "Track total tweets" in {
    val s = Metrics.trackTweets _
    s(tweetInfo())
    s(tweetInfo())
    CurrentStats.totalTweets should be(2)
  }

  it should "Compute percentage of tweets per hour, minute and second" in {
    Thread.sleep(1000)
    CurrentStats.totalTweets = 4

    val tps = CurrentStats.tweetsPerSecond
    tps should be(4.0 +- 0.001)
    CurrentStats.tweetsPerMinute should be (4.0/60 +- 0.001)
    CurrentStats.tweetsPerHour should be (4.0/(60*60) +- 0.001)
  }

  it should "Track number of tweets with emojis" in {
    val emoji1 = Emoji.allEmoji(0x00AE)
    val emoji2 = Emoji.allEmoji(0x203C)

    val s = Metrics.trackEmojis _

    s(tweetInfo())
    s(tweetInfo(List(emoji1)))
    s(tweetInfo(List(emoji2)))

    CurrentStats.tweetsWithEmojis should be(2)
  }

  it should "Track emoji frequency" in {
    val emoji1 = Emoji.allEmoji(0x00AE)
    val emoji2 = Emoji.allEmoji(0x203C)
    val emoji3 = Emoji.allEmoji(0x2049)

    val s = Metrics.trackEmojis _

    s(tweetInfo())
    s(tweetInfo(List(emoji1)))
    s(tweetInfo(List(emoji2, emoji1)))
    s(tweetInfo(List(emoji2)))
    s(tweetInfo(List(emoji2)))

    CurrentStats.emojiFrequency(emoji1) should be(2)
    CurrentStats.emojiFrequency(emoji2) should be(3)
    CurrentStats.emojiFrequency.get(emoji3) should be(None)
  }

  it should "Compute percentage of tweets with emojis" in {
    CurrentStats.totalTweets = 4
    CurrentStats.tweetsWithEmojis = 2
    CurrentStats.percentageOfTweetsWithEmojis should be(0.5 +- 0.0001)
  }

  it should "Track number of tweets with urls" in {
    val s = Metrics.trackUrls _

    s(tweetInfo(Nil))
    s(tweetInfo(Nil, List("http://learnyouahaskell.com")))
    s(tweetInfo(Nil, List("http://fpcomplete.com")))

    CurrentStats.tweetsWithUrls should be(2)
  }

  it should "Compute percentage of tweets with urls" in {
    CurrentStats.totalTweets = 4
    CurrentStats.tweetsWithUrls = 2
    CurrentStats.percentageOfTweetsWithUrls should be(0.5 +- 0.0001)
  }

  it should "Track number of tweets with photo urls" in {
    val s = Metrics.trackUrls _

    s(tweetInfo(Nil, List("http://notapicture")))
    s(tweetInfo(Nil, List("http://pic.twitter.com")))
    s(tweetInfo(Nil, List("http://pic.twitter.com/pic1")))
    s(tweetInfo(Nil, List("http://instagram.com/pic2")))

    CurrentStats.tweetsWithPhotoUrls should be(2)
  }

  it should "Compute percentage of tweets with photo urls" in {
    CurrentStats.totalTweets = 4
    CurrentStats.tweetsWithPhotoUrls = 2
    CurrentStats.percentageOfTweetsWithPhotoUrls should be(0.5 +- 0.0001)
  }

  it should "Track domain frequency" in {
    val s = Metrics.trackUrls _

    s(tweetInfo(Nil, List("http://en.wikipedia.org/wiki/Game")))
    s(tweetInfo(Nil, List("http://news.ycombinator.com")))
    s(tweetInfo(Nil, List("http://en.wikipedia.org/wiki/Rule")))

    CurrentStats.domainFrequency("ycombinator.com") should be(1)
    CurrentStats.domainFrequency("wikipedia.org") should be(2)
    CurrentStats.domainFrequency.get("other.com") should be(None)

  }

  it should "Track hashtag frequency" in {
    val s = Metrics.trackHashTags _

    s(tweetInfo())
    s(tweetInfo(Nil, Nil, List("foo", "bar")))
    s(tweetInfo(Nil, Nil, List("bar")))

    CurrentStats.hashTagFrequency("foo") should be(1)
    CurrentStats.hashTagFrequency("bar") should be(2)
    CurrentStats.hashTagFrequency.get("baz") should be(None)
  }
}
