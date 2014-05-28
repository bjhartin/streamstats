package org.banno.streamstats.statistics.mutable

import org.banno.streamstats.BaseSpec
import org.banno.streamstats.tweetprocessing.Emoji

class CurrentStatsSpec extends BaseSpec {
  behavior of "A CurrentStats"

  it should "Reset all stats" in {
    CurrentStats.totalTweets = 1
    CurrentStats.domainFrequency.put("http://google.com", 1)
    CurrentStats.emojiFrequency.put(Emoji.allEmoji.values.head, 1)
    CurrentStats.hashTagFrequency.put("awesome", 1)
    CurrentStats.tweetsWithPhotoUrls = 1
    CurrentStats.tweetsWithUrls = 3
    CurrentStats.tweetsWithEmojis = 1
    val startTime = CurrentStats.startTime

    CurrentStats.reset()

    CurrentStats.totalTweets should be(0)
    CurrentStats.domainFrequency.size should be(0)
    CurrentStats.emojiFrequency.size should be(0)
    CurrentStats.hashTagFrequency.size should be(0)
    CurrentStats.tweetsWithPhotoUrls should be(0)
    CurrentStats.tweetsWithUrls should be(0)
    CurrentStats.tweetsWithEmojis should be(0)
  }
}
