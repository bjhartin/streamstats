package org.banno.streamstats

import statistics.Statistic
import tweetprocessing.TweetProcessor
import twitter4j.{StallWarning, StatusDeletionNotice, Status, StatusListener}


case class BlockingStatusListener(tweetProcessor:TweetProcessor, statistics:List[Statistic]) extends StatusListener {
  def onStatus(status: Status) {
    statistics.foreach(s => s.compute(tweetProcessor.process(status)))
  }

  def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}
  def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}
  def onException(ex: Exception) {throw ex}
  def onScrubGeo(p1: Long, p2: Long) {}
  def onStallWarning(p1: StallWarning) {}
}
