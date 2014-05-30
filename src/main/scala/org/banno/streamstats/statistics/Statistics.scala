package org.banno.streamstats.statistics

import mutable.CurrentStats
import org.banno.streamstats.tweetprocessing._
import java.net.URI

object Statistics {
  def trackTweets(ti:TweetInfo) = {
    synchronized {CurrentStats.totalTweets += 1}}

  def trackEmoji(ti:TweetInfo) = {
    (ti("emoji") match {
      case Nil =>
      case emoji => {
        synchronized {
          CurrentStats.tweetsWithEmoji += 1
          emoji.asInstanceOf[List[Emoji]].foreach(e =>
            CurrentStats.emojiFrequency(e) = CurrentStats.emojiFrequency.getOrElse(e, 0) + 1
          )
        }
      }
    })
  }

  def trackUrls(ti:TweetInfo) = {
    (ti("urls") match {
      case Nil => 0
      case urls => {
        synchronized {
          CurrentStats.tweetsWithUrls += 1
          CurrentStats.tweetsWithPhotoUrls += countPhotoUrls(urls.asInstanceOf[List[String]])
          urls.asInstanceOf[List[String]].map(extractDomain).foreach(d => {
             CurrentStats.domainFrequency(d) = CurrentStats.domainFrequency.getOrElse(d, 0) + 1
            }
          )
        }
      }
    })
  }

  def trackHashTags(ti:TweetInfo) = {
    (ti("hashtags") match {
      case Nil => 0
      case tags => {
        tags.asInstanceOf[List[String]].foreach(t =>
          synchronized {
            CurrentStats.hashtagFrequency(t) = CurrentStats.hashtagFrequency.getOrElse(t, 0) + 1
          }
        )
      }
    })
  }

  def all: List[Stat] = List(trackTweets _, trackEmoji _, trackUrls _, trackHashTags _)

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
