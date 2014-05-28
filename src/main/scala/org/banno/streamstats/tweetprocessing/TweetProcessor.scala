package org.banno.streamstats.tweetprocessing

import twitter4j.Status
import concurrent.ExecutionContext
import ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration._

case class TweetProcessor(extractors: List[TweetInfoExtractor]) {
  def process(status: Status): TweetInfo = {
    // Run the tweet information extractors without blocking
    val futures = extractors.map(e => (e.getClass(), Future {e.extractInfo(status)}))
    futures.map(e => (e._1, Await.result(e._2, 10 seconds))).toMap
  }
}
