package org.banno.streamstats.statistics.mutable

import org.banno.streamstats.BaseSpec
import org.banno.streamstats.tweetprocessing.Emoji
import java.util.Calendar

class CurrentStatsSpec extends BaseSpec {
  behavior of "A CurrentStats"

  before {CurrentStats.reset()}


  /*
  def tweetsPerSecond: Double = totalTweets.toDouble / ((System.currentTimeMillis() - startTime) / 1000.0)
  def tweetsPerMinute: Double = tweetsPerSecond / 60.0
  def tweetsPerHour: Double = tweetsPerMinute / 60.0
    */

  it should "compute percentage of tweets per hour, minute and second" in {
    CurrentStats.totalTweets = 10
    val endTime = CurrentStats.startTime + 2000
    CurrentStats.tweetsPerSecond(endTime) should be(5.0 +- 0.0001)
    CurrentStats.tweetsPerMinute(endTime) should be((5.0 / 60 )+- 0.0001)
    CurrentStats.tweetsPerHour(endTime) should be((5.0 / (60 * 60))+- 0.0001)
  }

  it should "compute percentage of tweets with urls" in {
    CurrentStats.totalTweets = 10
    CurrentStats.tweetsWithUrls = 5
    CurrentStats.percentageOfTweetsWithUrls should be(0.5 +- 0.0001)
  }

  it should "compute percentage of tweets with photo urls" in {
    CurrentStats.totalTweets = 10
    CurrentStats.tweetsWithPhotoUrls = 5
    CurrentStats.percentageOfTweetsWithPhotoUrls should be(0.5 +- 0.0001)
  }

  it should "compute percentage of tweets with emoji" in {
    CurrentStats.totalTweets = 10
    CurrentStats.tweetsWithEmoji = 5
    CurrentStats.percentageOfTweetsWithEmoji should be (0.5 +- 0.0001)
  }

  it should "compute top domains" in {
    CurrentStats.domainFrequency.put("twitter.com", 4)
    CurrentStats.domainFrequency.put("google.com", 30)
    CurrentStats.domainFrequency.put("yahoo.com", 1)
    CurrentStats.domainFrequency.put("flickr.com", 20)
    CurrentStats.domainFrequency.put("instagram.com", 11)

    CurrentStats.top3Domains should be(List(("google.com", 30),
                                           ("flickr.com", 20),
                                           ("instagram.com", 11)))
    CurrentStats.uniqueDomains should be(5)


  }

  it should "compute top hashtags" in {
    CurrentStats.hashtagFrequency.put("red", 4)
    CurrentStats.hashtagFrequency.put("blue", 30)
    CurrentStats.hashtagFrequency.put("green", 1)
    CurrentStats.hashtagFrequency.put("yellow", 20)
    CurrentStats.hashtagFrequency.put("orange", 11)

    CurrentStats.top3Hashtags should be(List(("blue", 30),
      ("yellow", 20),
      ("orange", 11)))
    CurrentStats.uniqueHashtags should be(5)

  }

  it should "compute top emoji" in {
    CurrentStats.totalTweets = 10
    CurrentStats.emojiFrequency.put(Emoji.allEmoji(0x00AE), 4)
    CurrentStats.emojiFrequency.put(Emoji.allEmoji(0x203C), 30)
    CurrentStats.emojiFrequency.put(Emoji.allEmoji(0x2049), 1)
    CurrentStats.emojiFrequency.put(Emoji.allEmoji(0x2122), 20)
    CurrentStats.emojiFrequency.put(Emoji.allEmoji(0x2139), 11)

    CurrentStats.top3Emoji should be(List((Emoji.allEmoji(0x203C), 30),
      (Emoji.allEmoji(0x2122), 20),
      (Emoji.allEmoji(0x2139), 11)))
    CurrentStats.uniqueEmoji should be(5)
  }

  it should "Reset all stats" in {
    CurrentStats.totalTweets = 1
    CurrentStats.domainFrequency.put("google.com", 1)
    CurrentStats.emojiFrequency.put(Emoji.allEmoji.values.head, 1)
    CurrentStats.hashtagFrequency.put("awesome", 1)
    CurrentStats.tweetsWithPhotoUrls = 1
    CurrentStats.tweetsWithUrls = 3
    CurrentStats.tweetsWithEmoji = 1
    val startTime = CurrentStats.startTime

    CurrentStats.reset()

    CurrentStats.totalTweets should be(0)
    CurrentStats.domainFrequency.size should be(0)
    CurrentStats.emojiFrequency.size should be(0)
    CurrentStats.hashtagFrequency.size should be(0)
    CurrentStats.tweetsWithPhotoUrls should be(0)
    CurrentStats.tweetsWithUrls should be(0)
    CurrentStats.tweetsWithEmoji should be(0)
  }

  it should "Report all stats" in {
    CurrentStats.totalTweets = 5
    CurrentStats.domainFrequency.put("google.com", 3)
    CurrentStats.domainFrequency.put("flickr.com", 2)
    CurrentStats.domainFrequency.put("instagram.com", 1)
    CurrentStats.emojiFrequency.put(Emoji.allEmoji(0x00AE), 4)
    CurrentStats.emojiFrequency.put(Emoji.allEmoji(0x2195), 2)
    CurrentStats.emojiFrequency.put(Emoji.allEmoji(0x2196), 1)
    CurrentStats.hashtagFrequency.put("ruby", 3)
    CurrentStats.hashtagFrequency.put("scala", 4)
    CurrentStats.hashtagFrequency.put("haskell", 1)
    CurrentStats.tweetsWithPhotoUrls = 1
    CurrentStats.tweetsWithUrls = 3
    CurrentStats.tweetsWithEmoji = 2


    Thread.sleep(500)  // Needed so tweets/s isn't 'Infinity'
    val report = CurrentStats.toString()
    val lines = report.split("\n").toList

    lines(0) should be("totalTweets: 5")
    lines(1) should be("percentageOfTweetsWithUrls: 60.0%")
    lines(2) should be("percentageOfTweetsWithPhotoUrls: 20.0%")
    lines(3) should be("percentageOfTweetsWithEmoji: 40.0%")
    lines(4) should be("top3Emoji: \u00AE (4), \u2195 (2), \u2196 (1)")
    lines(5) should be("top3Hashtags: scala (4), ruby (3), haskell (1)")
    lines(6) should be("top3Domains: google.com (3), flickr.com (2), instagram.com (1)")
    lines(7) should fullyMatch regex "startTime: \\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}"
    lines(8) should fullyMatch regex "tweetsPerSecond: \\d+\\.\\d+"
  }

  it should "Report sensibly when no tweets" in {
    Thread.sleep(500)  // Needed so tweets/s isn't 'Infinity'
    val report = CurrentStats.toString()
    println(report)
    val lines = report.split("\n").toList

    lines(0) should be("totalTweets: 0")
    lines(1) should be("percentageOfTweetsWithUrls: 0.0%")
    lines(2) should be("percentageOfTweetsWithPhotoUrls: 0.0%")
    lines(3) should be("percentageOfTweetsWithEmoji: 0.0%")
    lines(4) should be("top3Emoji: ")
    lines(5) should be("top3Hashtags: ")
    lines(6) should be("top3Domains: ")
    lines(7) should fullyMatch regex "startTime: \\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}"
    lines(8) should be("tweetsPerSecond: 0.0")
  }
}
