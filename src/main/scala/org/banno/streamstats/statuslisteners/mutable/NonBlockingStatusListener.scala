package org.banno.streamstats.statuslisteners.mutable

import org.banno.streamstats.statistics.Stat
import twitter4j.Status
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration._
import org.banno.streamstats.tweetprocessing.TweetProcessor
import org.banno.streamstats.statuslisteners.BaseStatusListener

case class NonBlockingStatusListener(tweetProcessor:TweetProcessor, statistics:List[Stat]) extends BaseStatusListener {
  var statusProcessingFutures = collection.mutable.ListBuffer[Future[Any]]()

  def onStatus(status: Status) {
    statusProcessingFutures += Future {
      statistics.foreach(s => {
        val tweetInfo = tweetProcessor.process(status) // Faster with this inside the loop (!)
        s(tweetInfo)
      })
    }
  }

  def waitForCompletion() {
    val futureList = Future.sequence(statusProcessingFutures)
    Await.ready(futureList, Duration.Inf)
  }
}