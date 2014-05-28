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

  it should "Compute total tweets" in {
    val s = Statistics.totalTweets()
    s(tweetInfo())
    s(tweetInfo())
    CurrentStats.totalTweets should be(2)
  }

  it should "Compute number of tweets with emojis" in {
    val emoji1 = Emoji.allEmoji(0x00AE)
    val emoji2 = Emoji.allEmoji(0x00AE)

    val s = Statistics.tweetsWithEmojis()
    s(tweetInfo())
    s(tweetInfo(List(emoji1)))
    s(tweetInfo(List(emoji2)))
    CurrentStats.tweetsWithEmojis should be(2)
  }

  it should "Compute number of tweets with urls" in {
    val s = Statistics.tweetsWithUrls()
    s(tweetInfo(Nil))
    s(tweetInfo(Nil, List("http://learnyouahaskell.com")))
    s(tweetInfo(Nil, List("http://fpcomplete.com")))
    CurrentStats.tweetsWithUrls should be(2)
  }

}
