package org.banno.streamstats.statistics

import twitter4j.Status

trait Statistic {
  def compute(status:Status): Any


  /*

  Summary of required statistics and what they require us to do for each tweet

  TotalTweets: Nothing (simple increment)
  Average tweets per hour/minute/second: Nothing
  Top emojis: Extract emojis for tweets
  Percentage of tweets that contain emojis: Extract emojis for tweet
  Top hashtags: Extract hashtags for tweet
  Percentage of tweets that contain url: Extract urls for tweet
  Percentage of tweets that contain a photo url: Extract urls for tweet
  Top domains of urls in tweets:  Extract urls for tweet




   */
}
