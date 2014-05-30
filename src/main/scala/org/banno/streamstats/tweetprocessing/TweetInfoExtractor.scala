package org.banno.streamstats.tweetprocessing

import twitter4j.Status
import com.twitter.Extractor
import collection.JavaConverters._

object TweetInfoExtractor {
  // By using Twitter's text tag extraction API, I believe I can avoid writing a lot of
  // edge-case tests for hashtag and url formats.  If interested, you can see the details (and tests)
  // at https://github.com/twitter/twitter-text-java/tree/master/tests/com/twitter.
  val twitterTextExtractor = new Extractor()

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
