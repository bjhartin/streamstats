package org.banno.streamstats.tweetprocessing

import twitter4j.Status

class CountExtractor extends TweetInfoExtractor{
  def extractInfo(status: Status): Any = 1
}
