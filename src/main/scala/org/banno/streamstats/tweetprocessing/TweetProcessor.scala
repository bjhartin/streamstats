package org.banno.streamstats.tweetprocessing

import twitter4j.Status
import concurrent.ExecutionContext
import ExecutionContext.Implicits.global
import scala.concurrent._

case class TweetProcessor(extractors: List[TweetInfoExtractor]) {
  def process(status: Status): TweetInfo = {
    extractors.map(e => (e.getClass(), future {e.extractInfo(status)})).toMap
  }

  type TweetInfo = Map[Class[_ <: TweetInfoExtractor], Future[Any]]
}
