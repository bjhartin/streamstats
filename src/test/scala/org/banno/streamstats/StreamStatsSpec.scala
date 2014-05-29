package org.banno.streamstats

class StreamStatsSpec extends BaseSpec {
  it should "throw error if JVM not using UTF-8 encoding" in {
    withEncoding("Other", {
      val thrown = the [IllegalStateException] thrownBy StreamStats.main(Array())
      thrown.getMessage should be("file.encoding must be UTF-8")
    })
  }

  it should "Be happy with UTF-8 encoding" in {
    withEncoding("UTF-8", {
      StreamStats.main(Array())
    })
  }

  private def withEncoding(encoding:String, f: => Any) {
    val oldEncoding = System.getProperty("file.encoding")
    try {
      System.setProperty("file.encoding", encoding)
      f
    } finally {
      System.setProperty("file.encoding", oldEncoding)
    }
  }
}
