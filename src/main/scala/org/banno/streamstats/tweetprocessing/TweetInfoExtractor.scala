package org.banno.streamstats.tweetprocessing

import twitter4j.Status

trait TweetInfoExtractor {
  def extractInfo(status:Status): Any
}
