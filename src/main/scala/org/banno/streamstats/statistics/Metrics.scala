package org.banno.streamstats.statistics

import mutable.CurrentStats
import org.banno.streamstats.tweetprocessing._
import java.net.URI

object Metrics {
  def trackTweets(ti:TweetInfo) = {CurrentStats.totalTweets +=1} // Can't use _ for some reason

  def trackEmojis(ti:TweetInfo) = {
   (ti("emoji") match {
      case Nil => 0
      case emoji:List[Emoji] => {
        CurrentStats.tweetsWithEmojis += 1
        emoji.foreach(e =>
          CurrentStats.emojiFrequency(e) = CurrentStats.emojiFrequency.getOrElse(e, 0) + 1
        )
      }
    })
  }

  def trackUrls(ti:TweetInfo) = {
    (ti("urls") match {
      case Nil => 0
      case urls:List[String] => {
        CurrentStats.tweetsWithUrls += 1
        CurrentStats.tweetsWithPhotoUrls += countPhotoUrls(urls)
        urls.map(extractDomain).foreach(d => {
           CurrentStats.domainFrequency(d) = CurrentStats.domainFrequency.getOrElse(d, 0) + 1
          }
        )
      }
    })
  }

  def trackHashTags(ti:TweetInfo) = {
    (ti("hashtags") match {
      case Nil => 0
      case tags:List[String] => {
        tags.foreach(t =>
          CurrentStats.hashTagFrequency(t) = CurrentStats.hashTagFrequency.getOrElse(t, 0) + 1
        )
      }
    })
  }

  def all: List[Stat] = List(trackTweets _, trackEmojis _, trackUrls _, trackHashTags _)

  private val twitterPhotoRegexp = "http://(pic\\.twitter|instagram)\\.com/.+".r

  private def countPhotoUrls(urls:List[String]): Int = {
    urls.foldLeft(0)((count, url) => {
      (if(twitterPhotoRegexp.findFirstIn(url) == None) 0 else 1) + count
    })
  }
  private def extractDomain(url:String):String = {
    val parts = new URI(url).getHost.split("\\.")
    parts.reverse.take(2).reverse.mkString(".")
  }
}
