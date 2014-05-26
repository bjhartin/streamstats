package org.banno.streamstats.tweetprocessing

import twitter4j.Status
import com.twitter.Extractor
import collection.JavaConverters._

class HashTagExtractor extends TweetInfoExtractor {
  def twitterTextExtractor = new Extractor() // Will change to val if not fast enough

  override def extractInfo(status: Status): Any = {
    twitterTextExtractor.extractHashtags(status.getText).asScala.toList
  }
}