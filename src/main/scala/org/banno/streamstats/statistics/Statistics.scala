package org.banno.streamstats.statistics

import mutable.CurrentStats
import org.banno.streamstats.tweetprocessing._

object Statistics {
  def totalTweets(ti:TweetInfo) = {CurrentStats.totalTweets +=1} // Can't use _ for some reason

  def tweetsWithEmojis(ti:TweetInfo) = {
      CurrentStats.tweetsWithEmojis += (ti(classOf[EmojiExtractor]) match {
      case Nil => 0
      case _ => 1
    })
  }

  def tweetsWithUrls(ti:TweetInfo) = {
    CurrentStats.tweetsWithUrls += (ti(classOf[UrlExtractor]) match {
      case Nil => 0
      case _ => 1
    })
  }

  def all: List[Stat] = List(totalTweets _, tweetsWithEmojis _, tweetsWithUrls _)
}
