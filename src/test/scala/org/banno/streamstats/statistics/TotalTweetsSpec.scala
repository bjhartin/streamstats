package org.banno.streamstats.statistics

import mutable.CurrentStats
import org.banno.streamstats.BaseSpec
import org.banno.streamstats.tweetprocessing.TweetInfoExtractor
import concurrent.Future

class TotalTweetsSpec extends BaseSpec {
  behavior of "A TotalTweets statistic"

  before {CurrentStats.reset}

  it should "Increment the number of total tweets" in {
    val tweetInfo = mock[Map[Class[_ <: TweetInfoExtractor], Any]]
    new TotalTweets().compute(tweetInfo)
    new TotalTweets().compute(tweetInfo)
    CurrentStats.totalTweets should be(2)
  }
}
