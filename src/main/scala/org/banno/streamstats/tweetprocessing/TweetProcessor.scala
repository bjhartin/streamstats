package org.banno.streamstats.tweetprocessing

import twitter4j.Status
import concurrent.{ExecutionContext, Future}
import ExecutionContext.Implicits.global
import scala.concurrent._

case class TweetProcessor(extractors: List[TweetInfoExtractor]) {
  def process(status: Status):List[Future[Any]] = {
    extractors.map(e => future {e.extractInfo(status)})
  }
}
