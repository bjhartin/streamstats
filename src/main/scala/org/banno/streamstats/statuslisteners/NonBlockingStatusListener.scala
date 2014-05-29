package org.banno.streamstats.statuslisteners

import org.banno.streamstats.statistics.Stat
import twitter4j.Status
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration._
import org.banno.streamstats.tweetprocessing.TweetProcessor

case class NonBlockingStatusListener(tweetProcessor:TweetProcessor, statistics:List[Stat]) extends BaseStatusListener {
  var statusProcessingFutures = collection.mutable.ListBuffer[Future[Any]]()

  def onStatus(status: Status) {
    statusProcessingFutures += Future {
      statistics.foreach(s => {
        // It gets slower if I pull tweetInfo out of the loop!
        // Penalty for maintaining the binding to the local variable?
        val tweetInfo = tweetProcessor.process(status)
        s(tweetInfo)
      })
    }
  }

  def waitForCompletion() {
    val futureList = Future.sequence(statusProcessingFutures)
    Await.ready(futureList, Duration.Inf)
  }
}