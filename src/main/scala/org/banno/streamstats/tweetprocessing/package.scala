package org.banno.streamstats

import concurrent.Future

package object tweetprocessing {
  type TweetInfo = Map[Class[_ <: TweetInfoExtractor], Any]
}
