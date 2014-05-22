package org.banno.streamstats.statistics

import twitter4j.{StallWarning, StatusDeletionNotice, Status, StatusListener}


case class BlockingStatusListener(statistics:List[Statistic]) extends StatusListener {
  def onStatus(status: Status) {
    statistics.foreach(_.compute(status))
  }

  def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}
  def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}
  def onException(ex: Exception) {throw ex}
  def onScrubGeo(p1: Long, p2: Long) {}
  def onStallWarning(p1: StallWarning) {}
}
