package org.banno.streamstats

import statistics.Statistic
import tweetprocessing.TweetProcessor
import twitter4j.{StallWarning, StatusDeletionNotice, Status, StatusListener}
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration._

case class NonBlockingStatusListener(tweetProcessor:TweetProcessor, statistics:List[Statistic]) extends StatusListener {
  def onStatus(status: Status) {
    // Since implementing java void method, cannot return futures directly and then map over them
    val futures = statistics.map(s => {Future {
      val tweetInfo = tweetProcessor.process(status)
      s.compute(tweetInfo)
    }})
    futures.foreach(f => {
      Await.result(f, 10 seconds)
    })
  }

  def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}
  def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}
  def onException(ex: Exception) {throw ex}
  def onScrubGeo(p1: Long, p2: Long) {}
  def onStallWarning(p1: StallWarning) {}
}
