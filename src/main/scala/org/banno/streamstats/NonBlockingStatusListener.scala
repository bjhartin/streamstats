package org.banno.streamstats

import statistics.Statistic
import twitter4j.{StallWarning, StatusDeletionNotice, Status, StatusListener}
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.util.{Success, Failure}
import scala.concurrent.duration._

case class NonBlockingStatusListener(statistics:List[Statistic]) extends StatusListener {
  var computed = 0

  def onStatus(status: Status) {
    // Since implementing java void method, cannot return futures directly and then map over them
    val futures = statistics.map(s => {future {s.compute(status)}})
    futures.foreach(f => {
      Await.result(f, 200 seconds)
      computed += 1
    })
  }

  def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}
  def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}
  def onException(ex: Exception) {throw ex}
  def onScrubGeo(p1: Long, p2: Long) {}
  def onStallWarning(p1: StallWarning) {}
}
