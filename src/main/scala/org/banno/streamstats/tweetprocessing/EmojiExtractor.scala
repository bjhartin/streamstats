package org.banno.streamstats.tweetprocessing

import twitter4j.Status

class EmojiExtractor extends TweetInfoExtractor {
  def extractInfo(status: Status) = status.getText.map(Emoji.find(_)).flatten.toList
}
