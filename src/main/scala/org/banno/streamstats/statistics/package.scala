package org.banno.streamstats

import org.banno.streamstats.tweetprocessing.TweetInfo

package object statistics {
  type Stat = TweetInfo => Unit
}
