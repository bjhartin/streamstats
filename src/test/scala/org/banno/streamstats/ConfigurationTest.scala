package org.banno.streamstats

import org.junit.Test
import org.junit.Assert._

class ConfigurationTest {

  @Test
  def createConfig() {
    val config = Configuration.create
    assertTrue(config.isInstanceOf[twitter4j.conf.Configuration])
  }
}
