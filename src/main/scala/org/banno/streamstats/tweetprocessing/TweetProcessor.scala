package org.banno.streamstats.tweetprocessing

import twitter4j.Status
import concurrent.ExecutionContext
import ExecutionContext.Implicits.global
import scala.concurrent._

case class TweetProcessor(extractors: List[TweetInfoExtractor]) {
  def process(status: Status): TweetInfo = {
    // Run the tweet information extractors without blocking
    extractors.foreach(_.extractInfo(status))
    extractors.map(e => (e.getClass(), Future {e.extractInfo(status)})).toMap
  }
}
