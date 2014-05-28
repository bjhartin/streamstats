package org.banno.streamstats.statistics

import mutable.CurrentStats
import org.banno.streamstats.tweetprocessing.TweetInfo

class TotalTweets extends Statistic {
  override def compute(tweetInfo:TweetInfo) {
    CurrentStats.totalTweets += 1
  }
}
