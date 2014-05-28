package org.banno.streamstats.statistics.mutable

import org.banno.streamstats.tweetprocessing.Emoji

object CurrentStats {
  var totalTweets = 0
  var urlCount = 0
  var photoUrlCount = 0
  var startTime = System.currentTimeMillis()

  val emojiFrequency =  collection.mutable.HashMap[Emoji, Int]()
  val hashTagFrequency = collection.mutable.HashMap[String, Int]()
  val domainFrequency = collection.mutable.HashMap[String, Int]()

  def tweetsPerSecond: Double = totalTweets / ((System.currentTimeMillis() - startTime) / 1000)
  def tweetsPerMinute: Double = tweetsPerSecond / 60
  def tweetsPerHour: Double = tweetsPerMinute / 60

  def reset() {
    totalTweets = 0
    urlCount = 0
    photoUrlCount = 0

    startTime = System.currentTimeMillis()
    emojiFrequency.clear()
    hashTagFrequency.clear()
    domainFrequency.clear()
  }
}
