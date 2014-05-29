package org.banno.streamstats.statuslisteners

import twitter4j.{StallWarning, StatusDeletionNotice, StatusListener}

trait BaseStatusListener extends StatusListener {
  def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}
  def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}
  def onException(ex: Exception) {throw ex}
  def onScrubGeo(p1: Long, p2: Long) {}
  def onStallWarning(p1: StallWarning) {}
}
