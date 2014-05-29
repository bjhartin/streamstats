package org.banno.streamstats

import org.junit.Test
import org.junit.Assert._

class ConfigurationSpec extends BaseSpec {
  behavior of "The configuration"

  it should "create a configuration" in {
    val config = Configuration.create
     config shouldBe a [twitter4j.conf.Configuration]
  }
}
