package org.banno.streamstats.tweetprocessing

import twitter4j.Status

object TweetInfoExtractor {
  def count = TweetInfoExtractor("count", status => 1)

  def emoji = TweetInfoExtractor("emoji", status =>
    status.getText.map(Emoji.find(_)).flatten.toList
  )

  def hashtags = TweetInfoExtractor("hashtags", status =>
    status.getHashtagEntities match {
      case null => Nil
      case entities => entities.map(_.getText()).toList
    }
  )

  def urls = TweetInfoExtractor("urls", status => {
    status.getURLEntities match {
      case null => Nil
      case entities => entities.map(_.getExpandedURL()).toList
    }
  })

  def all(): List[TweetInfoExtractor] = List(count, emoji, hashtags, urls)
}

case class TweetInfoExtractor(name: String, extractor: Status => Any) {
  def extract(status:Status):Any = extractor(status)
}
