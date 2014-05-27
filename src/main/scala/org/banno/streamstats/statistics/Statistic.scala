package org.banno.streamstats.statistics

import org.banno.streamstats.tweetprocessing.TweetInfo

trait Statistic {
  def compute(tweetInfo:TweetInfo)
}
