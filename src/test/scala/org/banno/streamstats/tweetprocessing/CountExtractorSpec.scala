package org.banno.streamstats.tweetprocessing

import twitter4j.Status
import org.banno.streamstats.BaseSpec

class CountExtractorSpec extends BaseSpec {
  behavior of "A CountExtractor"

  def extractor = new CountExtractor()

  // This is a silly test, but I prefer to have such tests so that,
  // when a class is missing a test, we can readily recognize the oversight.

  it should "return 1" in {
    extractor.extractInfo(mock[Status]) should be(1)
  }

}
