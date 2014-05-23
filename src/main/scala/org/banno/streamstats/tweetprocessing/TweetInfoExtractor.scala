package org.banno.streamstats.tweetprocessing

import twitter4j.Status

trait TweetInfoExtractor {
  // Default implementation is not realistic.  Will be removed soon.
  def extractInfo(status:Status):Any = {
    Thread.sleep(10)
    1
  }
}
