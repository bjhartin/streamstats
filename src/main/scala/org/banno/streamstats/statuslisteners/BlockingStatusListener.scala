package org.banno.streamstats.statuslisteners

import org.banno.streamstats.statistics.Stat
import twitter4j.{StallWarning, StatusDeletionNotice, Status, StatusListener}
import org.banno.streamstats.tweetprocessing.TweetProcessor

case class BlockingStatusListener(tweetProcessor:TweetProcessor, statistics:List[Stat]) extends BaseStatusListener {
  def onStatus(status: Status) {
    statistics.foreach(s => s(tweetProcessor.process(status)))
  }
}
