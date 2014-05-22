package org.banno.streamstats.statistics

import twitter4j.Status

trait Statistic {
  def compute(status:Status): Any
}
