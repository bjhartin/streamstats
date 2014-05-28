package org.banno.streamstats.statistics.mutable

import org.banno.streamstats.tweetprocessing.Emoji
import java.text.SimpleDateFormat

object CurrentStats {
  var totalTweets = 0
  var tweetsWithUrls = 0
  var tweetsWithPhotoUrls = 0
  var tweetsWithEmojis = 0
  var startTime = System.currentTimeMillis()
  val timeFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")


  val emojiFrequency =  collection.mutable.HashMap[Emoji, Int]()
  val hashTagFrequency = collection.mutable.HashMap[String, Int]()
  val domainFrequency = collection.mutable.HashMap[String, Int]()

  def tweetsPerSecond: Double = totalTweets.toDouble / ((System.currentTimeMillis() - startTime) / 1000.0)
  def tweetsPerMinute: Double = tweetsPerSecond.toDouble / 60.0
  def tweetsPerHour: Double = tweetsPerMinute.toDouble / 60.0
  def percentageOfTweetsWithEmoji: Double = tweetsWithEmojis.toDouble / totalTweets
  def percentageOfTweetsWithUrls: Double = tweetsWithUrls.toDouble / totalTweets
  def percentageOfTweetsWithPhotoUrls: Double = tweetsWithPhotoUrls.toDouble / totalTweets
  def uniqueEmoji:Int = emojiFrequency.size
  def uniqueDomains:Int = domainFrequency.size
  def uniqueHashTags:Int = hashTagFrequency.size
  def top3Emoji = topN[Emoji](emojiFrequency, 3)
  def top3Hashtags = topN(hashTagFrequency, 3)
  def top3Domains = domainFrequency.toList.sortBy(_._2).reverse.take(3)

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
    (s"totalTweets: $totalTweets\n" +
    ("percentageOfTweetsWithUrls: %.1f%%\n" format percentageOfTweetsWithUrls * 100) +
    ("percentageOfTweetsWithPhotoUrls: %.1f%%\n" format percentageOfTweetsWithPhotoUrls * 100) +
    ("percentageOfTweetsWithEmoji: %.1f%%\n" format percentageOfTweetsWithEmoji * 100) +
    s"top3Emoji: ${top3Emoji.map(e => e._1.char + " (" + e._2 + ")").mkString(", ")}\n"  +
    s"top3Hashtags: ${top3Hashtags.map(e => e._1 + " (" + e._2 + ")").mkString(", ")}\n" +
    s"top3Domains: ${top3Domains.map(e => e._1 + " (" + e._2 + ")").mkString(", ")}\n" +
    s"startTime: ${timeFormat.format(startTime)}\n" +
    ("tweetsPerSecond: %.1f" format tweetsPerSecond)).replaceAll("NaN", "0.0")
  }

  private def topN[T](map:collection.mutable.HashMap[T, Int], n:Int) = map.toList.sortBy(_._2).reverse.take(n)
}
