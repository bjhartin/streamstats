package org.banno.streamstats.statistics

import twitter4j.Status
import org.banno.streamstats.tweetprocessing.TweetInfo

class TotalTweets extends Statistic {
  override def compute(tweetInfo:TweetInfo) {
    CurrentStats.totalTweets += 1
  }
}
