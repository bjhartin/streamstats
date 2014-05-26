package org.banno.streamstats.tweetprocessing

import twitter4j.Status

class UrlExtractor extends TweetInfoExtractor {
  def extractInfo(status: Status) = {
    Thread.sleep(10)
    1
  }
}