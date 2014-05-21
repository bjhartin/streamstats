package org.banno.streamstats.statistics

object TotalTweets {
  var totalTweets = 0
}

class TotalTweets extends IntStatistic {
  override def compute():Int = {
    TotalTweets.totalTweets += 1
  }
}
