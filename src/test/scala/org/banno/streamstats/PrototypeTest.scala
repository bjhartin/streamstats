package org.banno.streamstats

import twitter4j._
import org.junit.Test

class PrototypeTest {
  def simpleStatusListener = new StatusListener() {
    var tweetsReceived = 0

    def onStatus(status: Status) {
//      println("-" * 100)
//      println(status.getText)
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
    val twitterStream = new TwitterStreamFactory(Configuration.create).getInstance
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
