package org.banno.streamstats

object Configuration {
  def create = new twitter4j.conf.ConfigurationBuilder()
    .setOAuthConsumerKey("yk0KovuMjOnFLZnhHCAzGZtD2")
    .setOAuthConsumerSecret("EMZrs9gEM3sxRIK2nnKePu0DvT9owSgRqVwlo5Yo3twsP077PQ")
    .setOAuthAccessToken("5865682-4ooaSuQCoobUSdVK3Zuxv9EkwBmL5ayF1iVmq9tmcz")
    .setOAuthAccessTokenSecret("OnAkbyoimDqi8cxqjv0VxHoQJ1pdDRByoj4dib5jrBPbd")
    .build
}
