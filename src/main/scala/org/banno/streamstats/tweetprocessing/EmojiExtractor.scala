package org.banno.streamstats.tweetprocessing

import twitter4j.Status

class EmojiExtractor extends TweetInfoExtractor {
  def extractInfo(status: Status) = {
    Thread.sleep(10)
    1
  }
}
