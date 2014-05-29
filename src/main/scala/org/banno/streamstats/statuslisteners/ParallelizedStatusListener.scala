package org.banno.streamstats.statuslisteners

import org.banno.streamstats.statistics.Stat
import twitter4j.Status
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration._
import org.banno.streamstats.tweetprocessing.TweetProcessor

case class ParallelizedStatusListener(tweetProcessor:TweetProcessor, statistics:List[Stat]) extends BaseStatusListener {
  def onStatus(status: Status) {
    val tweetInfo = tweetProcessor.process(status)
    val futures = statistics.map(s => {Future {s(tweetInfo)}})
    val futureList = Future.sequence(futures)
    Await.ready(futureList, Duration.Inf)
  }
}
