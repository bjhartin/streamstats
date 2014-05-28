package org.banno.streamstats.statistics

import mutable.CurrentStats
import org.banno.streamstats.BaseSpec
import org.banno.streamstats.tweetprocessing.{EmojiExtractor, Emoji, TweetInfoExtractor}

class StatisticsSpec extends BaseSpec {
  behavior of "Statistics functions"

  before {CurrentStats.reset}

  def tweetInfo(emoji:List[Emoji]) = {
    Map[Class[_ <: TweetInfoExtractor], Any](
      classOf[EmojiExtractor] -> emoji
    )
  }

  it should "Compute total tweets" in {
    val s = Statistics.totalTweets()
    s(tweetInfo(Nil))
    s(tweetInfo(Nil))
    CurrentStats.totalTweets should be(2)
  }

  it should "Compute number of emojis" in {
    val emoji1 = Emoji.allEmoji(0x00AE)
    val emoji2 = Emoji.allEmoji(0x00AE)

    val s = Statistics.tweetsWithEmojis()
    s(tweetInfo(Nil))
    s(tweetInfo(List(emoji1)))
    s(tweetInfo(List(emoji2)))
    CurrentStats.tweetsWithEmojis should be(2)
  }

}
