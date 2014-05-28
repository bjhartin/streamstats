package org.banno.streamstats.statistics.mutable

import org.banno.streamstats.tweetprocessing.Emoji

object CurrentStats {
  var totalTweets = 0
  var tweetsWithUrls = 0
  var tweetsWithPhotoUrls = 0
  var tweetsWithEmojis = 0
  var startTime = System.currentTimeMillis()

  val emojiFrequency =  collection.mutable.HashMap[Emoji, Int]()
  val hashTagFrequency = collection.mutable.HashMap[String, Int]()
  val domainFrequency = collection.mutable.HashMap[String, Int]()

  def tweetsPerSecond: Double = totalTweets.toDouble / ((System.currentTimeMillis() - startTime) / 1000)
  def tweetsPerMinute: Double = tweetsPerSecond.toDouble / 60
  def tweetsPerHour: Double = tweetsPerMinute.toDouble / 60
  def percentageOfTweetsWithEmojis: Double = tweetsWithEmojis.toDouble / totalTweets
  def percentageOfTweetsWithUrls: Double = tweetsWithUrls.toDouble / totalTweets
  def percentageOfTweetsWithPhotoUrls: Double = tweetsWithPhotoUrls.toDouble / totalTweets
  def uniqueEmoji:Int = emojiFrequency.size
  def uniqueDomains:Int = domainFrequency.size
  def uniqueHashTags:Int = hashTagFrequency.size

  def reset() {
    totalTweets = 0
    tweetsWithUrls = 0
    tweetsWithEmojis = 0
    tweetsWithPhotoUrls = 0

    startTime = System.currentTimeMillis()
    emojiFrequency.clear()
    hashTagFrequency.clear()
    domainFrequency.clear()
  }

  override def toString():String = {
    s"totalTweets: $totalTweets\n" +
    s"tweetsWithUrls: $tweetsWithUrls\n" +
    s"tweetsWithPhotoUrls: $tweetsWithPhotoUrls\n" +
    s"uniqueDomains: $uniqueDomains\n" +
    s"uniqueHashTags: $uniqueHashTags\n" +
    s"uniqueEmoji: $uniqueEmoji\n" +
    s"tweetsPerSecond: $tweetsPerSecond"
  }
}
