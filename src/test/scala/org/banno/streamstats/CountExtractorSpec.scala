package org.banno.streamstats

import tweetprocessing.CountExtractor
import twitter4j.Status

class CountExtractorSpec extends BaseSpec {
  behavior of "A CountExtractor"

  // This is a silly test, but I prefer to have such tests so that,
  // when a class is missing a test, we can readily recognize the oversight.

  it should "return 1" in {
    new CountExtractor().extractInfo(mock[Status]) should be(1)
  }
}
