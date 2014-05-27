package org.banno.streamstats.statistics

import java.util.{Calendar, Date}
import collection.mutable
import org.banno.streamstats.tweetprocessing.Emoji

object CurrentStats {
  var totalTweets = 0
  val startTime = System.currentTimeMillis()
  val emojiFrequency =  mutable.HashMap[Emoji, Int]() // Frequency != 0
  val hashTagFrequency = mutable.HashMap[String, Int]()
  val urlCount = 0
  val photoUrlCount = 0
  val domainFrequency = mutable.HashMap[String, Int]()

  def tweetsPerSecond: Double = totalTweets / ((System.currentTimeMillis() - startTime) / 1000)
  def tweetsPerMinute: Double = tweetsPerSecond / 60
  def tweetsPerHour: Double = tweetsPerHour / 60
}
