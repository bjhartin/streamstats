package org.banno.streamstats.statistics

import mutable.CurrentStats
import org.banno.streamstats.tweetprocessing._

object Statistics {
  val twitterPhotoRegexp = "http://(pic\\.twitter|instagram)\\.com/.+".r

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

  def tweetsWithPhotoUrls(ti:TweetInfo) = {
    CurrentStats.tweetsWithPhotoUrls += (ti(classOf[UrlExtractor]) match {
      case Nil => 0
      case urls: List[String] => countPhotoUrls(urls)
    })
  }

  def all: List[Stat] = List(totalTweets _, tweetsWithEmojis _, tweetsWithUrls _)

  private def countPhotoUrls(urls:List[String]): Int = {
    urls.foldLeft(0)((count, url) => {
      (if(twitterPhotoRegexp.findFirstIn(url) == None) 0 else 1) + count
    })
  }
}
