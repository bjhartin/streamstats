package org.banno.streamstats

import twitter4j._
import org.junit.Test

class BasicTest {
  val config = new twitter4j.conf.ConfigurationBuilder()
      .setOAuthConsumerKey("yk0KovuMjOnFLZnhHCAzGZtD2")
      .setOAuthConsumerSecret("EMZrs9gEM3sxRIK2nnKePu0DvT9owSgRqVwlo5Yo3twsP077PQ")
      .setOAuthAccessToken("5865682-4ooaSuQCoobUSdVK3Zuxv9EkwBmL5ayF1iVmq9tmcz")
      .setOAuthAccessTokenSecret("OnAkbyoimDqi8cxqjv0VxHoQJ1pdDRByoj4dib5jrBPbd")
      .build

  def simpleStatusListener = new StatusListener() {
    var tweetsReceived = 0

    def onStatus(status: Status) {
//      println(status.getText)
//      println("tweet " + tweetsReceived + " received")
      tweetsReceived += 1
    }
    def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}
    def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}
    def onException(ex: Exception) { ex.printStackTrace }
    def onScrubGeo(p1: Long, p2: Long) {}
    def onStallWarning(p1: StallWarning) {}
  }

  @Test
  def configWorks() {
    val listener = simpleStatusListener
    val twitterStream = new TwitterStreamFactory(config).getInstance
    twitterStream.addListener(listener)
    println("Beginning to sample...")
    twitterStream.sample
    Thread.sleep(10000)
    println("Cleaning up and shutting down")
    twitterStream.cleanUp
    twitterStream.shutdown
    println("Done")
    println(listener.tweetsReceived + " tweets received in 10s")


  }




}
