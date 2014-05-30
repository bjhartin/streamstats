package org.banno.streamstats.statistics

import mutable.CurrentStats
import org.banno.streamstats.BaseSpec
import org.banno.streamstats.tweetprocessing._

class StatisticsSpec extends BaseSpec {
  behavior of "Statistics functions"

  before {CurrentStats.reset}

  def tweetInfo(emoji:List[Emoji] = Nil, urls:List[String] = Nil, hashtags: List[String] = Nil) = {
    Map("emoji" -> emoji,
        "urls" -> urls,
        "hashtags" -> hashtags
    )
  }

  it should "Track total tweets" in {
    val s = Statistics.trackTweets _
    s(tweetInfo())
    s(tweetInfo())
    CurrentStats.totalTweets should be(2)
  }

  it should "Track number of tweets with emoji" in {
    val emoji1 = Emoji.allEmoji(0x00AE)
    val emoji2 = Emoji.allEmoji(0x203C)

    val s = Statistics.trackEmoji _

    s(tweetInfo())
    s(tweetInfo(List(emoji1)))
    s(tweetInfo(List(emoji2)))

    CurrentStats.tweetsWithEmoji should be(2)
  }

  it should "Track emoji frequency" in {
    val emoji1 = Emoji.allEmoji(0x00AE)
    val emoji2 = Emoji.allEmoji(0x203C)
    val emoji3 = Emoji.allEmoji(0x2049)

    val s = Statistics.trackEmoji _

    s(tweetInfo())
    s(tweetInfo(List(emoji1)))
    s(tweetInfo(List(emoji2, emoji1)))
    s(tweetInfo(List(emoji2)))
    s(tweetInfo(List(emoji2)))

    CurrentStats.emojiFrequency(emoji1) should be(2)
    CurrentStats.emojiFrequency(emoji2) should be(3)
    CurrentStats.emojiFrequency.get(emoji3) should be(None)
  }

  it should "Compute percentage of tweets with emoji" in {
    CurrentStats.totalTweets = 4
    CurrentStats.tweetsWithEmoji = 2
    CurrentStats.percentageOfTweetsWithEmoji should be(0.5 +- 0.01)
  }

  it should "Track number of tweets with urls" in {
    val s = Statistics.trackUrls _

    s(tweetInfo(Nil))
    s(tweetInfo(Nil, List("http://learnyouahaskell.com")))
    s(tweetInfo(Nil, List("http://fpcomplete.com")))

    CurrentStats.tweetsWithUrls should be(2)
  }

  it should "Compute percentage of tweets with urls" in {
    CurrentStats.totalTweets = 4
    CurrentStats.tweetsWithUrls = 2
    CurrentStats.percentageOfTweetsWithUrls should be(0.5 +- 0.01)
  }

  it should "Track number of tweets with photo urls" in {
    val s = Statistics.trackUrls _

    s(tweetInfo(Nil, List("http://notapicture")))
    s(tweetInfo(Nil, List("http://pic.twitter.com")))
    s(tweetInfo(Nil, List("http://pic.twitter.com/pic1")))
    s(tweetInfo(Nil, List("http://instagram.com/pic2")))

    CurrentStats.tweetsWithPhotoUrls should be(2)
  }

  it should "Compute percentage of tweets with photo urls" in {
    CurrentStats.totalTweets = 4
    CurrentStats.tweetsWithPhotoUrls = 2
    CurrentStats.percentageOfTweetsWithPhotoUrls should be(0.5 +- 0.01)
  }

  it should "Track domain frequency" in {
    val s = Statistics.trackUrls _

    s(tweetInfo(Nil, List("http://en.wikipedia.org/wiki/Game")))
    s(tweetInfo(Nil, List("http://news.ycombinator.com")))
    s(tweetInfo(Nil, List("http://en.wikipedia.org/wiki/Rule")))

    CurrentStats.domainFrequency("ycombinator.com") should be(1)
    CurrentStats.domainFrequency("wikipedia.org") should be(2)
    CurrentStats.domainFrequency.get("other.com") should be(None)

  }

  it should "Track hashtag frequency" in {
    val s = Statistics.trackHashTags _

    s(tweetInfo())
    s(tweetInfo(Nil, Nil, List("foo", "bar")))
    s(tweetInfo(Nil, Nil, List("bar")))

    CurrentStats.hashtagFrequency("foo") should be(1)
    CurrentStats.hashtagFrequency("bar") should be(2)
    CurrentStats.hashtagFrequency.get("baz") should be(None)
  }
}
