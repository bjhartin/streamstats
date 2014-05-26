package org.banno.streamstats.tweetprocessing

import org.banno.streamstats.BaseSpec
import twitter4j.Status
import org.mockito.Mockito._

class EmojiSpec extends BaseSpec {
  behavior of "An Emoji"

  it should "find emoji chars" in {
    Emoji.find(0x00AE.toChar) should be(Some(Emoji(0x00AE.toChar, "REGISTERED SIGN")))
    Emoji.find(0x0001.toChar) should be(None)
    Emoji.find(0x1F62A.toChar) should be(Some(Emoji(0x1F62A.toChar, "SLEEPY FACE")))
  }

  it should "perform reasonably well" in {
    val tweets = 100
    val tps = tweetsPerSecond(tweets, Emoji.find(0x00AE.toChar))
    tps should be > 1000.0
    println(s"Tweets/s: $tps")
  }
}
