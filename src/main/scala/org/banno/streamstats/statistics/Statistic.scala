package org.banno.streamstats.statistics

trait Statistic {
  def compute(): Any
}
