package org.banno.streamstats.tweetprocessing

import org.banno.streamstats.BaseSpec

class EmojiSpec extends BaseSpec {
  behavior of "An Emoji"

  it should "find emoji chars" in {
    Emoji.find(0x00AE.toChar) should be(Some(Emoji(0x00AE.toChar, "REGISTERED SIGN")))
    Emoji.find(0x0001.toChar) should be(None)
    Emoji.find(0x1F62A.toChar) should be(Some(Emoji(0x1F62A.toChar, "SLEEPY FACE")))
  }
}
