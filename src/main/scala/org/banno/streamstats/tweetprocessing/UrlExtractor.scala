package org.banno.streamstats.tweetprocessing

import twitter4j.Status
import com.twitter.Extractor
import collection.JavaConverters._

class UrlExtractor extends TweetInfoExtractor {
  def twitterTextExtractor = new Extractor() // Will change to val if not fast enough

  override def extractInfo(status: Status): Any = {
    twitterTextExtractor.extractURLs(status.getText).asScala.toList
  }
}