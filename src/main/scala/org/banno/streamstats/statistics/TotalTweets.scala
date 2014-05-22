package org.banno.streamstats.statistics

import twitter4j.Status

class TotalTweets extends Statistic {
  var value:Int = 0

  override def compute(status:Status) {
    value += 1
  }
}
