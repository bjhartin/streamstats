package org.banno.streamstats.tweetprocessing

import twitter4j.Status
import concurrent.ExecutionContext
import ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration._

case class TweetProcessor(extractors: List[TweetInfoExtractor]) {
  def process(status: Status): TweetInfo = {
    val futures = extractors.map(e => (e.name, Future {e.extract(status)}))
    futures.map(e => (e._1, Await.result(e._2, 10 seconds))).toMap
  }
}
