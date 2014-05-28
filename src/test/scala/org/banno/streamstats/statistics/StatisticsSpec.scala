package org.banno.streamstats.statistics

import mutable.CurrentStats
import org.banno.streamstats.BaseSpec
import org.banno.streamstats.tweetprocessing._

class StatisticsSpec extends BaseSpec {
  behavior of "Statistics functions"

  before {CurrentStats.reset}

  def tweetInfo(emoji:List[Emoji] = Nil, urls:List[String] = Nil, hashtags: List[String] = Nil) = {
    Map[Class[_ <: TweetInfoExtractor], Any](
      classOf[EmojiExtractor] -> emoji,
      classOf[UrlExtractor] -> urls,
      classOf[HashTagExtractor] -> hashtags
    )
  }

  it should "Track total tweets" in {
    val s = Statistics.totalTweets _
    s(tweetInfo())
    s(tweetInfo())
    CurrentStats.totalTweets should be(2)
  }

  it should "Track number of tweets with emojis" in {
    val emoji1 = Emoji.allEmoji(0x00AE)
    val emoji2 = Emoji.allEmoji(0x00AE)

    val s = Statistics.tweetsWithEmojis _
    s(tweetInfo())
    s(tweetInfo(List(emoji1)))
    s(tweetInfo(List(emoji2)))
    CurrentStats.tweetsWithEmojis should be(2)
  }

  it should "Compute percentage of tweets with emojis" in {
    CurrentStats.totalTweets = 4
    CurrentStats.tweetsWithEmojis = 2
    CurrentStats.percentageOfTweetsWithEmojis should be(0.5 +- 0.0001)
  }

  it should "Track number of tweets with urls" in {
    val s = Statistics.tweetsWithUrls _
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

  it should "Compute percentage of tweets per hour, minute and second" in {
    Thread.sleep(1000)
    CurrentStats.totalTweets = 4

    val tps = CurrentStats.tweetsPerSecond
    tps should be(4.0 +- 0.0001)
    CurrentStats.tweetsPerMinute should be (4.0/60 +- 0.0001)
    CurrentStats.tweetsPerHour should be (4.0/(60*60) +- 0.0001)
  }



}
