package org.banno.streamstats.tweetprocessing

case class Emoji(char:Char, description:String)
object Emoji {
  def find(c:Char): Option[Emoji] = allEmoji.get(c)

  // Performance tests justify keeping this state.
  val allEmoji: Map[Char, Emoji] = List( Emoji(0x00A9.toChar, "COPYRIGHT SIGN"),
    Emoji(0x00AE.toChar, "REGISTERED SIGN"),
    Emoji(0x203C.toChar, "DOUBLE EXCLAMATION MARK"),
    Emoji(0x2049.toChar, "EXCLAMATION QUESTION MARK"),
    Emoji(0x2122.toChar, "TRADE MARK SIGN"),
    Emoji(0x2139.toChar, "INFORMATION SOURCE"),
    Emoji(0x2194.toChar, "LEFT RIGHT ARROW"),
    Emoji(0x2195.toChar, "UP DOWN ARROW"),
    Emoji(0x2196.toChar, "NORTH WEST ARROW"),
    Emoji(0x2197.toChar, "NORTH EAST ARROW"),
    Emoji(0x2198.toChar, "SOUTH EAST ARROW"),
    Emoji(0x2199.toChar, "SOUTH WEST ARROW"),
    Emoji(0x21A9.toChar, "LEFTWARDS ARROW WITH HOOK"),
    Emoji(0x21AA.toChar, "RIGHTWARDS ARROW WITH HOOK"),
    Emoji(0x231A.toChar, "WATCH"),
    Emoji(0x231B.toChar, "HOURGLASS"),
    Emoji(0x23E9.toChar, "BLACK RIGHT-POINTING DOUBLE TRIANGLE"),
    Emoji(0x23EA.toChar, "BLACK LEFT-POINTING DOUBLE TRIANGLE"),
    Emoji(0x23EB.toChar, "BLACK UP-POINTING DOUBLE TRIANGLE"),
    Emoji(0x23EC.toChar, "BLACK DOWN-POINTING DOUBLE TRIANGLE"),
    Emoji(0x23F0.toChar, "ALARM CLOCK"),
    Emoji(0x23F3.toChar, "HOURGLASS WITH FLOWING SAND"),
    Emoji(0x24C2.toChar, "CIRCLED LATIN CAPITAL LETTER M"),
    Emoji(0x25AA.toChar, "BLACK SMALL SQUARE"),
    Emoji(0x25AB.toChar, "WHITE SMALL SQUARE"),
    Emoji(0x25B6.toChar, "BLACK RIGHT-POINTING TRIANGLE"),
    Emoji(0x25C0.toChar, "BLACK LEFT-POINTING TRIANGLE"),
    Emoji(0x25FB.toChar, "WHITE MEDIUM SQUARE"),
    Emoji(0x25FC.toChar, "BLACK MEDIUM SQUARE"),
    Emoji(0x25FD.toChar, "WHITE MEDIUM SMALL SQUARE"),
    Emoji(0x25FE.toChar, "BLACK MEDIUM SMALL SQUARE"),
    Emoji(0x2600.toChar, "BLACK SUN WITH RAYS"),
    Emoji(0x2601.toChar, "CLOUD"),
    Emoji(0x260E.toChar, "BLACK TELEPHONE"),
    Emoji(0x2611.toChar, "BALLOT BOX WITH CHECK"),
    Emoji(0x2614.toChar, "UMBRELLA WITH RAIN DROPS"),
    Emoji(0x2615.toChar, "HOT BEVERAGE"),
    Emoji(0x261D.toChar, "WHITE UP POINTING INDEX"),
    Emoji(0x263A.toChar, "WHITE SMILING FACE"),
    Emoji(0x2648.toChar, "ARIES"),
    Emoji(0x2649.toChar, "TAURUS"),
    Emoji(0x264A.toChar, "GEMINI"),
    Emoji(0x264B.toChar, "CANCER"),
    Emoji(0x264C.toChar, "LEO"),
    Emoji(0x264D.toChar, "VIRGO"),
    Emoji(0x264E.toChar, "LIBRA"),
    Emoji(0x264F.toChar, "SCORPIUS"),
    Emoji(0x2650.toChar, "SAGITTARIUS"),
    Emoji(0x2651.toChar, "CAPRICORN"),
    Emoji(0x2652.toChar, "AQUARIUS"),
    Emoji(0x2653.toChar, "PISCES"),
    Emoji(0x2660.toChar, "BLACK SPADE SUIT"),
    Emoji(0x2663.toChar, "BLACK CLUB SUIT"),
    Emoji(0x2665.toChar, "BLACK HEART SUIT"),
    Emoji(0x2666.toChar, "BLACK DIAMOND SUIT"),
    Emoji(0x2668.toChar, "HOT SPRINGS"),
    Emoji(0x267B.toChar, "BLACK UNIVERSAL RECYCLING SYMBOL"),
    Emoji(0x267F.toChar, "WHEELCHAIR SYMBOL"),
    Emoji(0x2693.toChar, "ANCHOR"),
    Emoji(0x26A0.toChar, "WARNING SIGN"),
    Emoji(0x26A1.toChar, "HIGH VOLTAGE SIGN"),
    Emoji(0x26AA.toChar, "MEDIUM WHITE CIRCLE"),
    Emoji(0x26AB.toChar, "MEDIUM BLACK CIRCLE"),
    Emoji(0x26BD.toChar, "SOCCER BALL"),
    Emoji(0x26BE.toChar, "BASEBALL"),
    Emoji(0x26C4.toChar, "SNOWMAN WITHOUT SNOW"),
    Emoji(0x26C5.toChar, "SUN BEHIND CLOUD"),
    Emoji(0x26CE.toChar, "OPHIUCHUS"),
    Emoji(0x26D4.toChar, "NO ENTRY"),
    Emoji(0x26EA.toChar, "CHURCH"),
    Emoji(0x26F2.toChar, "FOUNTAIN"),
    Emoji(0x26F3.toChar, "FLAG IN HOLE"),
    Emoji(0x26F5.toChar, "SAILBOAT"),
    Emoji(0x26FA.toChar, "TENT"),
    Emoji(0x26FD.toChar, "FUEL PUMP"),
    Emoji(0x2702.toChar, "BLACK SCISSORS"),
    Emoji(0x2705.toChar, "WHITE HEAVY CHECK MARK"),
    Emoji(0x2708.toChar, "AIRPLANE"),
    Emoji(0x2709.toChar, "ENVELOPE"),
    Emoji(0x270A.toChar, "RAISED FIST"),
    Emoji(0x270B.toChar, "RAISED HAND"),
    Emoji(0x270C.toChar, "VICTORY HAND"),
    Emoji(0x270F.toChar, "PENCIL"),
    Emoji(0x2712.toChar, "BLACK NIB"),
    Emoji(0x2714.toChar, "HEAVY CHECK MARK"),
    Emoji(0x2716.toChar, "HEAVY MULTIPLICATION X"),
    Emoji(0x2728.toChar, "SPARKLES"),
    Emoji(0x2733.toChar, "EIGHT SPOKED ASTERISK"),
    Emoji(0x2734.toChar, "EIGHT POINTED BLACK STAR"),
    Emoji(0x2744.toChar, "SNOWFLAKE"),
    Emoji(0x2747.toChar, "SPARKLE"),
    Emoji(0x274C.toChar, "CROSS MARK"),
    Emoji(0x274E.toChar, "NEGATIVE SQUARED CROSS MARK"),
    Emoji(0x2753.toChar, "BLACK QUESTION MARK ORNAMENT"),
    Emoji(0x2754.toChar, "WHITE QUESTION MARK ORNAMENT"),
    Emoji(0x2755.toChar, "WHITE EXCLAMATION MARK ORNAMENT"),
    Emoji(0x2757.toChar, "HEAVY EXCLAMATION MARK SYMBOL"),
    Emoji(0x2764.toChar, "HEAVY BLACK HEART"),
    Emoji(0x2795.toChar, "HEAVY PLUS SIGN"),
    Emoji(0x2796.toChar, "HEAVY MINUS SIGN"),
    Emoji(0x2797.toChar, "HEAVY DIVISION SIGN"),
    Emoji(0x27A1.toChar, "BLACK RIGHTWARDS ARROW"),
    Emoji(0x27B0.toChar, "CURLY LOOP"),
    Emoji(0x27BF.toChar, "DOUBLE CURLY LOOP"),
    Emoji(0x2934.toChar, "ARROW POINTING RIGHTWARDS THEN CURVING UPWARDS"),
    Emoji(0x2935.toChar, "ARROW POINTING RIGHTWARDS THEN CURVING DOWNWARDS"),
    Emoji(0x2B05.toChar, "LEFTWARDS BLACK ARROW"),
    Emoji(0x2B06.toChar, "UPWARDS BLACK ARROW"),
    Emoji(0x2B07.toChar, "DOWNWARDS BLACK ARROW"),
    Emoji(0x2B1B.toChar, "BLACK LARGE SQUARE"),
    Emoji(0x2B1C.toChar, "WHITE LARGE SQUARE"),
    Emoji(0x2B50.toChar, "WHITE MEDIUM STAR"),
    Emoji(0x2B55.toChar, "HEAVY LARGE CIRCLE"),
    Emoji(0x3030.toChar, "WAVY DASH"),
    Emoji(0x303D.toChar, "PART ALTERNATION MARK"),
    Emoji(0x3297.toChar, "CIRCLED IDEOGRAPH CONGRATULATION"),
    Emoji(0x3299.toChar, "CIRCLED IDEOGRAPH SECRET"),
    Emoji(0x1F004.toChar, "MAHJONG TILE RED DRAGON"),
    Emoji(0x1F0CF.toChar, "PLAYING CARD BLACK JOKER"),
    Emoji(0x1F170.toChar, "NEGATIVE SQUARED LATIN CAPITAL LETTER A"),
    Emoji(0x1F171.toChar, "NEGATIVE SQUARED LATIN CAPITAL LETTER B"),
    Emoji(0x1F17E.toChar, "NEGATIVE SQUARED LATIN CAPITAL LETTER O"),
    Emoji(0x1F17F.toChar, "NEGATIVE SQUARED LATIN CAPITAL LETTER P"),
    Emoji(0x1F18E.toChar, "NEGATIVE SQUARED AB"),
    Emoji(0x1F191.toChar, "SQUARED CL"),
    Emoji(0x1F192.toChar, "SQUARED COOL"),
    Emoji(0x1F193.toChar, "SQUARED FREE"),
    Emoji(0x1F194.toChar, "SQUARED ID"),
    Emoji(0x1F195.toChar, "SQUARED NEW"),
    Emoji(0x1F196.toChar, "SQUARED NG"),
    Emoji(0x1F197.toChar, "SQUARED OK"),
    Emoji(0x1F198.toChar, "SQUARED SOS"),
    Emoji(0x1F199.toChar, "SQUARED UP WITH EXCLAMATION MARK"),
    Emoji(0x1F19A.toChar, "SQUARED VS"),
    Emoji(0x1F201.toChar, "SQUARED KATAKANA KOKO"),
    Emoji(0x1F202.toChar, "SQUARED KATAKANA SA"),
    Emoji(0x1F21A.toChar, "SQUARED CJK UNIFIED IDEOGRAPH-7121"),
    Emoji(0x1F22F.toChar, "SQUARED CJK UNIFIED IDEOGRAPH-6307"),
    Emoji(0x1F232.toChar, "SQUARED CJK UNIFIED IDEOGRAPH-7981"),
    Emoji(0x1F233.toChar, "SQUARED CJK UNIFIED IDEOGRAPH-7A7A"),
    Emoji(0x1F234.toChar, "SQUARED CJK UNIFIED IDEOGRAPH-5408"),
    Emoji(0x1F235.toChar, "SQUARED CJK UNIFIED IDEOGRAPH-6E80"),
    Emoji(0x1F236.toChar, "SQUARED CJK UNIFIED IDEOGRAPH-6709"),
    Emoji(0x1F237.toChar, "SQUARED CJK UNIFIED IDEOGRAPH-6708"),
    Emoji(0x1F238.toChar, "SQUARED CJK UNIFIED IDEOGRAPH-7533"),
    Emoji(0x1F239.toChar, "SQUARED CJK UNIFIED IDEOGRAPH-5272"),
    Emoji(0x1F23A.toChar, "SQUARED CJK UNIFIED IDEOGRAPH-55B6"),
    Emoji(0x1F250.toChar, "CIRCLED IDEOGRAPH ADVANTAGE"),
    Emoji(0x1F251.toChar, "CIRCLED IDEOGRAPH ACCEPT"),
    Emoji(0x1F300.toChar, "CYCLONE"),
    Emoji(0x1F301.toChar, "FOGGY"),
    Emoji(0x1F302.toChar, "CLOSED UMBRELLA"),
    Emoji(0x1F303.toChar, "NIGHT WITH STARS"),
    Emoji(0x1F304.toChar, "SUNRISE OVER MOUNTAINS"),
    Emoji(0x1F305.toChar, "SUNRISE"),
    Emoji(0x1F306.toChar, "CITYSCAPE AT DUSK"),
    Emoji(0x1F307.toChar, "SUNSET OVER BUILDINGS"),
    Emoji(0x1F308.toChar, "RAINBOW"),
    Emoji(0x1F309.toChar, "BRIDGE AT NIGHT"),
    Emoji(0x1F30A.toChar, "WATER WAVE"),
    Emoji(0x1F30B.toChar, "VOLCANO"),
    Emoji(0x1F30C.toChar, "MILKY WAY"),
    Emoji(0x1F30D.toChar, "EARTH GLOBE EUROPE-AFRICA"),
    Emoji(0x1F30E.toChar, "EARTH GLOBE AMERICAS"),
    Emoji(0x1F30F.toChar, "EARTH GLOBE ASIA-AUSTRALIA"),
    Emoji(0x1F310.toChar, "GLOBE WITH MERIDIANS"),
    Emoji(0x1F311.toChar, "NEW MOON SYMBOL"),
    Emoji(0x1F312.toChar, "WAXING CRESCENT MOON SYMBOL"),
    Emoji(0x1F313.toChar, "FIRST QUARTER MOON SYMBOL"),
    Emoji(0x1F314.toChar, "WAXING GIBBOUS MOON SYMBOL"),
    Emoji(0x1F315.toChar, "FULL MOON SYMBOL"),
    Emoji(0x1F316.toChar, "WANING GIBBOUS MOON SYMBOL"),
    Emoji(0x1F317.toChar, "LAST QUARTER MOON SYMBOL"),
    Emoji(0x1F318.toChar, "WANING CRESCENT MOON SYMBOL"),
    Emoji(0x1F319.toChar, "CRESCENT MOON"),
    Emoji(0x1F31A.toChar, "NEW MOON WITH FACE"),
    Emoji(0x1F31B.toChar, "FIRST QUARTER MOON WITH FACE"),
    Emoji(0x1F31C.toChar, "LAST QUARTER MOON WITH FACE"),
    Emoji(0x1F31D.toChar, "FULL MOON WITH FACE"),
    Emoji(0x1F31E.toChar, "SUN WITH FACE"),
    Emoji(0x1F31F.toChar, "GLOWING STAR"),
    Emoji(0x1F320.toChar, "SHOOTING STAR"),
    Emoji(0x1F330.toChar, "CHESTNUT"),
    Emoji(0x1F331.toChar, "SEEDLING"),
    Emoji(0x1F332.toChar, "EVERGREEN TREE"),
    Emoji(0x1F333.toChar, "DECIDUOUS TREE"),
    Emoji(0x1F334.toChar, "PALM TREE"),
    Emoji(0x1F335.toChar, "CACTUS"),
    Emoji(0x1F337.toChar, "TULIP"),
    Emoji(0x1F338.toChar, "CHERRY BLOSSOM"),
    Emoji(0x1F339.toChar, "ROSE"),
    Emoji(0x1F33A.toChar, "HIBISCUS"),
    Emoji(0x1F33B.toChar, "SUNFLOWER"),
    Emoji(0x1F33C.toChar, "BLOSSOM"),
    Emoji(0x1F33D.toChar, "EAR OF MAIZE"),
    Emoji(0x1F33E.toChar, "EAR OF RICE"),
    Emoji(0x1F33F.toChar, "HERB"),
    Emoji(0x1F340.toChar, "FOUR LEAF CLOVER"),
    Emoji(0x1F341.toChar, "MAPLE LEAF"),
    Emoji(0x1F342.toChar, "FALLEN LEAF"),
    Emoji(0x1F343.toChar, "LEAF FLUTTERING IN WIND"),
    Emoji(0x1F344.toChar, "MUSHROOM"),
    Emoji(0x1F345.toChar, "TOMATO"),
    Emoji(0x1F346.toChar, "AUBERGINE"),
    Emoji(0x1F347.toChar, "GRAPES"),
    Emoji(0x1F348.toChar, "MELON"),
    Emoji(0x1F349.toChar, "WATERMELON"),
    Emoji(0x1F34A.toChar, "TANGERINE"),
    Emoji(0x1F34B.toChar, "LEMON"),
    Emoji(0x1F34C.toChar, "BANANA"),
    Emoji(0x1F34D.toChar, "PINEAPPLE"),
    Emoji(0x1F34E.toChar, "RED APPLE"),
    Emoji(0x1F34F.toChar, "GREEN APPLE"),
    Emoji(0x1F350.toChar, "PEAR"),
    Emoji(0x1F351.toChar, "PEACH"),
    Emoji(0x1F352.toChar, "CHERRIES"),
    Emoji(0x1F353.toChar, "STRAWBERRY"),
    Emoji(0x1F354.toChar, "HAMBURGER"),
    Emoji(0x1F355.toChar, "SLICE OF PIZZA"),
    Emoji(0x1F356.toChar, "MEAT ON BONE"),
    Emoji(0x1F357.toChar, "POULTRY LEG"),
    Emoji(0x1F358.toChar, "RICE CRACKER"),
    Emoji(0x1F359.toChar, "RICE BALL"),
    Emoji(0x1F35A.toChar, "COOKED RICE"),
    Emoji(0x1F35B.toChar, "CURRY AND RICE"),
    Emoji(0x1F35C.toChar, "STEAMING BOWL"),
    Emoji(0x1F35D.toChar, "SPAGHETTI"),
    Emoji(0x1F35E.toChar, "BREAD"),
    Emoji(0x1F35F.toChar, "FRENCH FRIES"),
    Emoji(0x1F360.toChar, "ROASTED SWEET POTATO"),
    Emoji(0x1F361.toChar, "DANGO"),
    Emoji(0x1F362.toChar, "ODEN"),
    Emoji(0x1F363.toChar, "SUSHI"),
    Emoji(0x1F364.toChar, "FRIED SHRIMP"),
    Emoji(0x1F365.toChar, "FISH CAKE WITH SWIRL DESIGN"),
    Emoji(0x1F366.toChar, "SOFT ICE CREAM"),
    Emoji(0x1F367.toChar, "SHAVED ICE"),
    Emoji(0x1F368.toChar, "ICE CREAM"),
    Emoji(0x1F369.toChar, "DOUGHNUT"),
    Emoji(0x1F36A.toChar, "COOKIE"),
    Emoji(0x1F36B.toChar, "CHOCOLATE BAR"),
    Emoji(0x1F36C.toChar, "CANDY"),
    Emoji(0x1F36D.toChar, "LOLLIPOP"),
    Emoji(0x1F36E.toChar, "CUSTARD"),
    Emoji(0x1F36F.toChar, "HONEY POT"),
    Emoji(0x1F370.toChar, "SHORTCAKE"),
    Emoji(0x1F371.toChar, "BENTO BOX"),
    Emoji(0x1F372.toChar, "POT OF FOOD"),
    Emoji(0x1F373.toChar, "COOKING"),
    Emoji(0x1F374.toChar, "FORK AND KNIFE"),
    Emoji(0x1F375.toChar, "TEACUP WITHOUT HANDLE"),
    Emoji(0x1F376.toChar, "SAKE BOTTLE AND CUP"),
    Emoji(0x1F377.toChar, "WINE GLASS"),
    Emoji(0x1F378.toChar, "COCKTAIL GLASS"),
    Emoji(0x1F379.toChar, "TROPICAL DRINK"),
    Emoji(0x1F37A.toChar, "BEER MUG"),
    Emoji(0x1F37B.toChar, "CLINKING BEER MUGS"),
    Emoji(0x1F37C.toChar, "BABY BOTTLE"),
    Emoji(0x1F380.toChar, "RIBBON"),
    Emoji(0x1F381.toChar, "WRAPPED PRESENT"),
    Emoji(0x1F382.toChar, "BIRTHDAY CAKE"),
    Emoji(0x1F383.toChar, "JACK-O-LANTERN"),
    Emoji(0x1F384.toChar, "CHRISTMAS TREE"),
    Emoji(0x1F385.toChar, "FATHER CHRISTMAS"),
    Emoji(0x1F386.toChar, "FIREWORKS"),
    Emoji(0x1F387.toChar, "FIREWORK SPARKLER"),
    Emoji(0x1F388.toChar, "BALLOON"),
    Emoji(0x1F389.toChar, "PARTY POPPER"),
    Emoji(0x1F38A.toChar, "CONFETTI BALL"),
    Emoji(0x1F38B.toChar, "TANABATA TREE"),
    Emoji(0x1F38C.toChar, "CROSSED FLAGS"),
    Emoji(0x1F38D.toChar, "PINE DECORATION"),
    Emoji(0x1F38E.toChar, "JAPANESE DOLLS"),
    Emoji(0x1F38F.toChar, "CARP STREAMER"),
    Emoji(0x1F390.toChar, "WIND CHIME"),
    Emoji(0x1F391.toChar, "MOON VIEWING CEREMONY"),
    Emoji(0x1F392.toChar, "SCHOOL SATCHEL"),
    Emoji(0x1F393.toChar, "GRADUATION CAP"),
    Emoji(0x1F3A0.toChar, "CAROUSEL HORSE"),
    Emoji(0x1F3A1.toChar, "FERRIS WHEEL"),
    Emoji(0x1F3A2.toChar, "ROLLER COASTER"),
    Emoji(0x1F3A3.toChar, "FISHING POLE AND FISH"),
    Emoji(0x1F3A4.toChar, "MICROPHONE"),
    Emoji(0x1F3A5.toChar, "MOVIE CAMERA"),
    Emoji(0x1F3A6.toChar, "CINEMA"),
    Emoji(0x1F3A7.toChar, "HEADPHONE"),
    Emoji(0x1F3A8.toChar, "ARTIST PALETTE"),
    Emoji(0x1F3A9.toChar, "TOP HAT"),
    Emoji(0x1F3AA.toChar, "CIRCUS TENT"),
    Emoji(0x1F3AB.toChar, "TICKET"),
    Emoji(0x1F3AC.toChar, "CLAPPER BOARD"),
    Emoji(0x1F3AD.toChar, "PERFORMING ARTS"),
    Emoji(0x1F3AE.toChar, "VIDEO GAME"),
    Emoji(0x1F3AF.toChar, "DIRECT HIT"),
    Emoji(0x1F3B0.toChar, "SLOT MACHINE"),
    Emoji(0x1F3B1.toChar, "BILLIARDS"),
    Emoji(0x1F3B2.toChar, "GAME DIE"),
    Emoji(0x1F3B3.toChar, "BOWLING"),
    Emoji(0x1F3B4.toChar, "FLOWER PLAYING CARDS"),
    Emoji(0x1F3B5.toChar, "MUSICAL NOTE"),
    Emoji(0x1F3B6.toChar, "MULTIPLE MUSICAL NOTES"),
    Emoji(0x1F3B7.toChar, "SAXOPHONE"),
    Emoji(0x1F3B8.toChar, "GUITAR"),
    Emoji(0x1F3B9.toChar, "MUSICAL KEYBOARD"),
    Emoji(0x1F3BA.toChar, "TRUMPET"),
    Emoji(0x1F3BB.toChar, "VIOLIN"),
    Emoji(0x1F3BC.toChar, "MUSICAL SCORE"),
    Emoji(0x1F3BD.toChar, "RUNNING SHIRT WITH SASH"),
    Emoji(0x1F3BE.toChar, "TENNIS RACQUET AND BALL"),
    Emoji(0x1F3BF.toChar, "SKI AND SKI BOOT"),
    Emoji(0x1F3C0.toChar, "BASKETBALL AND HOOP"),
    Emoji(0x1F3C1.toChar, "CHEQUERED FLAG"),
    Emoji(0x1F3C2.toChar, "SNOWBOARDER"),
    Emoji(0x1F3C3.toChar, "RUNNER"),
    Emoji(0x1F3C4.toChar, "SURFER"),
    Emoji(0x1F3C6.toChar, "TROPHY"),
    Emoji(0x1F3C7.toChar, "HORSE RACING"),
    Emoji(0x1F3C8.toChar, "AMERICAN FOOTBALL"),
    Emoji(0x1F3C9.toChar, "RUGBY FOOTBALL"),
    Emoji(0x1F3CA.toChar, "SWIMMER"),
    Emoji(0x1F3E0.toChar, "HOUSE BUILDING"),
    Emoji(0x1F3E1.toChar, "HOUSE WITH GARDEN"),
    Emoji(0x1F3E2.toChar, "OFFICE BUILDING"),
    Emoji(0x1F3E3.toChar, "JAPANESE POST OFFICE"),
    Emoji(0x1F3E4.toChar, "EUROPEAN POST OFFICE"),
    Emoji(0x1F3E5.toChar, "HOSPITAL"),
    Emoji(0x1F3E6.toChar, "BANK"),
    Emoji(0x1F3E7.toChar, "AUTOMATED TELLER MACHINE"),
    Emoji(0x1F3E8.toChar, "HOTEL"),
    Emoji(0x1F3E9.toChar, "LOVE HOTEL"),
    Emoji(0x1F3EA.toChar, "CONVENIENCE STORE"),
    Emoji(0x1F3EB.toChar, "SCHOOL"),
    Emoji(0x1F3EC.toChar, "DEPARTMENT STORE"),
    Emoji(0x1F3ED.toChar, "FACTORY"),
    Emoji(0x1F3EE.toChar, "IZAKAYA LANTERN"),
    Emoji(0x1F3EF.toChar, "JAPANESE CASTLE"),
    Emoji(0x1F3F0.toChar, "EUROPEAN CASTLE"),
    Emoji(0x1F400.toChar, "RAT"),
    Emoji(0x1F401.toChar, "MOUSE"),
    Emoji(0x1F402.toChar, "OX"),
    Emoji(0x1F403.toChar, "WATER BUFFALO"),
    Emoji(0x1F404.toChar, "COW"),
    Emoji(0x1F405.toChar, "TIGER"),
    Emoji(0x1F406.toChar, "LEOPARD"),
    Emoji(0x1F407.toChar, "RABBIT"),
    Emoji(0x1F408.toChar, "CAT"),
    Emoji(0x1F409.toChar, "DRAGON"),
    Emoji(0x1F40A.toChar, "CROCODILE"),
    Emoji(0x1F40B.toChar, "WHALE"),
    Emoji(0x1F40C.toChar, "SNAIL"),
    Emoji(0x1F40D.toChar, "SNAKE"),
    Emoji(0x1F40E.toChar, "HORSE"),
    Emoji(0x1F40F.toChar, "RAM"),
    Emoji(0x1F410.toChar, "GOAT"),
    Emoji(0x1F411.toChar, "SHEEP"),
    Emoji(0x1F412.toChar, "MONKEY"),
    Emoji(0x1F413.toChar, "ROOSTER"),
    Emoji(0x1F414.toChar, "CHICKEN"),
    Emoji(0x1F415.toChar, "DOG"),
    Emoji(0x1F416.toChar, "PIG"),
    Emoji(0x1F417.toChar, "BOAR"),
    Emoji(0x1F418.toChar, "ELEPHANT"),
    Emoji(0x1F419.toChar, "OCTOPUS"),
    Emoji(0x1F41A.toChar, "SPIRAL SHELL"),
    Emoji(0x1F41B.toChar, "BUG"),
    Emoji(0x1F41C.toChar, "ANT"),
    Emoji(0x1F41D.toChar, "HONEYBEE"),
    Emoji(0x1F41E.toChar, "LADY BEETLE"),
    Emoji(0x1F41F.toChar, "FISH"),
    Emoji(0x1F420.toChar, "TROPICAL FISH"),
    Emoji(0x1F421.toChar, "BLOWFISH"),
    Emoji(0x1F422.toChar, "TURTLE"),
    Emoji(0x1F423.toChar, "HATCHING CHICK"),
    Emoji(0x1F424.toChar, "BABY CHICK"),
    Emoji(0x1F425.toChar, "FRONT-FACING BABY CHICK"),
    Emoji(0x1F426.toChar, "BIRD"),
    Emoji(0x1F427.toChar, "PENGUIN"),
    Emoji(0x1F428.toChar, "KOALA"),
    Emoji(0x1F429.toChar, "POODLE"),
    Emoji(0x1F42A.toChar, "DROMEDARY CAMEL"),
    Emoji(0x1F42B.toChar, "BACTRIAN CAMEL"),
    Emoji(0x1F42C.toChar, "DOLPHIN"),
    Emoji(0x1F42D.toChar, "MOUSE FACE"),
    Emoji(0x1F42E.toChar, "COW FACE"),
    Emoji(0x1F42F.toChar, "TIGER FACE"),
    Emoji(0x1F430.toChar, "RABBIT FACE"),
    Emoji(0x1F431.toChar, "CAT FACE"),
    Emoji(0x1F432.toChar, "DRAGON FACE"),
    Emoji(0x1F433.toChar, "SPOUTING WHALE"),
    Emoji(0x1F434.toChar, "HORSE FACE"),
    Emoji(0x1F435.toChar, "MONKEY FACE"),
    Emoji(0x1F436.toChar, "DOG FACE"),
    Emoji(0x1F437.toChar, "PIG FACE"),
    Emoji(0x1F438.toChar, "FROG FACE"),
    Emoji(0x1F439.toChar, "HAMSTER FACE"),
    Emoji(0x1F43A.toChar, "WOLF FACE"),
    Emoji(0x1F43B.toChar, "BEAR FACE"),
    Emoji(0x1F43C.toChar, "PANDA FACE"),
    Emoji(0x1F43D.toChar, "PIG NOSE"),
    Emoji(0x1F43E.toChar, "PAW PRINTS"),
    Emoji(0x1F440.toChar, "EYES"),
    Emoji(0x1F442.toChar, "EAR"),
    Emoji(0x1F443.toChar, "NOSE"),
    Emoji(0x1F444.toChar, "MOUTH"),
    Emoji(0x1F445.toChar, "TONGUE"),
    Emoji(0x1F446.toChar, "WHITE UP POINTING BACKHAND INDEX"),
    Emoji(0x1F447.toChar, "WHITE DOWN POINTING BACKHAND INDEX"),
    Emoji(0x1F448.toChar, "WHITE LEFT POINTING BACKHAND INDEX"),
    Emoji(0x1F449.toChar, "WHITE RIGHT POINTING BACKHAND INDEX"),
    Emoji(0x1F44A.toChar, "FISTED HAND SIGN"),
    Emoji(0x1F44B.toChar, "WAVING HAND SIGN"),
    Emoji(0x1F44C.toChar, "OK HAND SIGN"),
    Emoji(0x1F44D.toChar, "THUMBS UP SIGN"),
    Emoji(0x1F44E.toChar, "THUMBS DOWN SIGN"),
    Emoji(0x1F44F.toChar, "CLAPPING HANDS SIGN"),
    Emoji(0x1F450.toChar, "OPEN HANDS SIGN"),
    Emoji(0x1F451.toChar, "CROWN"),
    Emoji(0x1F452.toChar, "WOMANS HAT"),
    Emoji(0x1F453.toChar, "EYEGLASSES"),
    Emoji(0x1F454.toChar, "NECKTIE"),
    Emoji(0x1F455.toChar, "T-SHIRT"),
    Emoji(0x1F456.toChar, "JEANS"),
    Emoji(0x1F457.toChar, "DRESS"),
    Emoji(0x1F458.toChar, "KIMONO"),
    Emoji(0x1F459.toChar, "BIKINI"),
    Emoji(0x1F45A.toChar, "WOMANS CLOTHES"),
    Emoji(0x1F45B.toChar, "PURSE"),
    Emoji(0x1F45C.toChar, "HANDBAG"),
    Emoji(0x1F45D.toChar, "POUCH"),
    Emoji(0x1F45E.toChar, "MANS SHOE"),
    Emoji(0x1F45F.toChar, "ATHLETIC SHOE"),
    Emoji(0x1F460.toChar, "HIGH-HEELED SHOE"),
    Emoji(0x1F461.toChar, "WOMANS SANDAL"),
    Emoji(0x1F462.toChar, "WOMANS BOOTS"),
    Emoji(0x1F463.toChar, "FOOTPRINTS"),
    Emoji(0x1F464.toChar, "BUST IN SILHOUETTE"),
    Emoji(0x1F465.toChar, "BUSTS IN SILHOUETTE"),
    Emoji(0x1F466.toChar, "BOY"),
    Emoji(0x1F467.toChar, "GIRL"),
    Emoji(0x1F468.toChar, "MAN"),
    Emoji(0x1F469.toChar, "WOMAN"),
    Emoji(0x1F46A.toChar, "FAMILY"),
    Emoji(0x1F46B.toChar, "MAN AND WOMAN HOLDING HANDS"),
    Emoji(0x1F46C.toChar, "TWO MEN HOLDING HANDS"),
    Emoji(0x1F46D.toChar, "TWO WOMEN HOLDING HANDS"),
    Emoji(0x1F46E.toChar, "POLICE OFFICER"),
    Emoji(0x1F46F.toChar, "WOMAN WITH BUNNY EARS"),
    Emoji(0x1F470.toChar, "BRIDE WITH VEIL"),
    Emoji(0x1F471.toChar, "PERSON WITH BLOND HAIR"),
    Emoji(0x1F472.toChar, "MAN WITH GUA PI MAO"),
    Emoji(0x1F473.toChar, "MAN WITH TURBAN"),
    Emoji(0x1F474.toChar, "OLDER MAN"),
    Emoji(0x1F475.toChar, "OLDER WOMAN"),
    Emoji(0x1F476.toChar, "BABY"),
    Emoji(0x1F477.toChar, "CONSTRUCTION WORKER"),
    Emoji(0x1F478.toChar, "PRINCESS"),
    Emoji(0x1F479.toChar, "JAPANESE OGRE"),
    Emoji(0x1F47A.toChar, "JAPANESE GOBLIN"),
    Emoji(0x1F47B.toChar, "GHOST"),
    Emoji(0x1F47C.toChar, "BABY ANGEL"),
    Emoji(0x1F47D.toChar, "EXTRATERRESTRIAL ALIEN"),
    Emoji(0x1F47E.toChar, "ALIEN MONSTER"),
    Emoji(0x1F47F.toChar, "IMP"),
    Emoji(0x1F480.toChar, "SKULL"),
    Emoji(0x1F481.toChar, "INFORMATION DESK PERSON"),
    Emoji(0x1F482.toChar, "GUARDSMAN"),
    Emoji(0x1F483.toChar, "DANCER"),
    Emoji(0x1F484.toChar, "LIPSTICK"),
    Emoji(0x1F485.toChar, "NAIL POLISH"),
    Emoji(0x1F486.toChar, "FACE MASSAGE"),
    Emoji(0x1F487.toChar, "HAIRCUT"),
    Emoji(0x1F488.toChar, "BARBER POLE"),
    Emoji(0x1F489.toChar, "SYRINGE"),
    Emoji(0x1F48A.toChar, "PILL"),
    Emoji(0x1F48B.toChar, "KISS MARK"),
    Emoji(0x1F48C.toChar, "LOVE LETTER"),
    Emoji(0x1F48D.toChar, "RING"),
    Emoji(0x1F48E.toChar, "GEM STONE"),
    Emoji(0x1F48F.toChar, "KISS"),
    Emoji(0x1F490.toChar, "BOUQUET"),
    Emoji(0x1F491.toChar, "COUPLE WITH HEART"),
    Emoji(0x1F492.toChar, "WEDDING"),
    Emoji(0x1F493.toChar, "BEATING HEART"),
    Emoji(0x1F494.toChar, "BROKEN HEART"),
    Emoji(0x1F495.toChar, "TWO HEARTS"),
    Emoji(0x1F496.toChar, "SPARKLING HEART"),
    Emoji(0x1F497.toChar, "GROWING HEART"),
    Emoji(0x1F498.toChar, "HEART WITH ARROW"),
    Emoji(0x1F499.toChar, "BLUE HEART"),
    Emoji(0x1F49A.toChar, "GREEN HEART"),
    Emoji(0x1F49B.toChar, "YELLOW HEART"),
    Emoji(0x1F49C.toChar, "PURPLE HEART"),
    Emoji(0x1F49D.toChar, "HEART WITH RIBBON"),
    Emoji(0x1F49E.toChar, "REVOLVING HEARTS"),
    Emoji(0x1F49F.toChar, "HEART DECORATION"),
    Emoji(0x1F4A0.toChar, "DIAMOND SHAPE WITH A DOT INSIDE"),
    Emoji(0x1F4A1.toChar, "ELECTRIC LIGHT BULB"),
    Emoji(0x1F4A2.toChar, "ANGER SYMBOL"),
    Emoji(0x1F4A3.toChar, "BOMB"),
    Emoji(0x1F4A4.toChar, "SLEEPING SYMBOL"),
    Emoji(0x1F4A5.toChar, "COLLISION SYMBOL"),
    Emoji(0x1F4A6.toChar, "SPLASHING SWEAT SYMBOL"),
    Emoji(0x1F4A7.toChar, "DROPLET"),
    Emoji(0x1F4A8.toChar, "DASH SYMBOL"),
    Emoji(0x1F4A9.toChar, "PILE OF POO"),
    Emoji(0x1F4AA.toChar, "FLEXED BICEPS"),
    Emoji(0x1F4AB.toChar, "DIZZY SYMBOL"),
    Emoji(0x1F4AC.toChar, "SPEECH BALLOON"),
    Emoji(0x1F4AD.toChar, "THOUGHT BALLOON"),
    Emoji(0x1F4AE.toChar, "WHITE FLOWER"),
    Emoji(0x1F4AF.toChar, "HUNDRED POINTS SYMBOL"),
    Emoji(0x1F4B0.toChar, "MONEY BAG"),
    Emoji(0x1F4B1.toChar, "CURRENCY EXCHANGE"),
    Emoji(0x1F4B2.toChar, "HEAVY DOLLAR SIGN"),
    Emoji(0x1F4B3.toChar, "CREDIT CARD"),
    Emoji(0x1F4B4.toChar, "BANKNOTE WITH YEN SIGN"),
    Emoji(0x1F4B5.toChar, "BANKNOTE WITH DOLLAR SIGN"),
    Emoji(0x1F4B6.toChar, "BANKNOTE WITH EURO SIGN"),
    Emoji(0x1F4B7.toChar, "BANKNOTE WITH POUND SIGN"),
    Emoji(0x1F4B8.toChar, "MONEY WITH WINGS"),
    Emoji(0x1F4B9.toChar, "CHART WITH UPWARDS TREND AND YEN SIGN"),
    Emoji(0x1F4BA.toChar, "SEAT"),
    Emoji(0x1F4BB.toChar, "PERSONAL COMPUTER"),
    Emoji(0x1F4BC.toChar, "BRIEFCASE"),
    Emoji(0x1F4BD.toChar, "MINIDISC"),
    Emoji(0x1F4BE.toChar, "FLOPPY DISK"),
    Emoji(0x1F4BF.toChar, "OPTICAL DISC"),
    Emoji(0x1F4C0.toChar, "DVD"),
    Emoji(0x1F4C1.toChar, "FILE FOLDER"),
    Emoji(0x1F4C2.toChar, "OPEN FILE FOLDER"),
    Emoji(0x1F4C3.toChar, "PAGE WITH CURL"),
    Emoji(0x1F4C4.toChar, "PAGE FACING UP"),
    Emoji(0x1F4C5.toChar, "CALENDAR"),
    Emoji(0x1F4C6.toChar, "TEAR-OFF CALENDAR"),
    Emoji(0x1F4C7.toChar, "CARD INDEX"),
    Emoji(0x1F4C8.toChar, "CHART WITH UPWARDS TREND"),
    Emoji(0x1F4C9.toChar, "CHART WITH DOWNWARDS TREND"),
    Emoji(0x1F4CA.toChar, "BAR CHART"),
    Emoji(0x1F4CB.toChar, "CLIPBOARD"),
    Emoji(0x1F4CC.toChar, "PUSHPIN"),
    Emoji(0x1F4CD.toChar, "ROUND PUSHPIN"),
    Emoji(0x1F4CE.toChar, "PAPERCLIP"),
    Emoji(0x1F4CF.toChar, "STRAIGHT RULER"),
    Emoji(0x1F4D0.toChar, "TRIANGULAR RULER"),
    Emoji(0x1F4D1.toChar, "BOOKMARK TABS"),
    Emoji(0x1F4D2.toChar, "LEDGER"),
    Emoji(0x1F4D3.toChar, "NOTEBOOK"),
    Emoji(0x1F4D4.toChar, "NOTEBOOK WITH DECORATIVE COVER"),
    Emoji(0x1F4D5.toChar, "CLOSED BOOK"),
    Emoji(0x1F4D6.toChar, "OPEN BOOK"),
    Emoji(0x1F4D7.toChar, "GREEN BOOK"),
    Emoji(0x1F4D8.toChar, "BLUE BOOK"),
    Emoji(0x1F4D9.toChar, "ORANGE BOOK"),
    Emoji(0x1F4DA.toChar, "BOOKS"),
    Emoji(0x1F4DB.toChar, "NAME BADGE"),
    Emoji(0x1F4DC.toChar, "SCROLL"),
    Emoji(0x1F4DD.toChar, "MEMO"),
    Emoji(0x1F4DE.toChar, "TELEPHONE RECEIVER"),
    Emoji(0x1F4DF.toChar, "PAGER"),
    Emoji(0x1F4E0.toChar, "FAX MACHINE"),
    Emoji(0x1F4E1.toChar, "SATELLITE ANTENNA"),
    Emoji(0x1F4E2.toChar, "PUBLIC ADDRESS LOUDSPEAKER"),
    Emoji(0x1F4E3.toChar, "CHEERING MEGAPHONE"),
    Emoji(0x1F4E4.toChar, "OUTBOX TRAY"),
    Emoji(0x1F4E5.toChar, "INBOX TRAY"),
    Emoji(0x1F4E6.toChar, "PACKAGE"),
    Emoji(0x1F4E7.toChar, "E-MAIL SYMBOL"),
    Emoji(0x1F4E8.toChar, "INCOMING ENVELOPE"),
    Emoji(0x1F4E9.toChar, "ENVELOPE WITH DOWNWARDS ARROW ABOVE"),
    Emoji(0x1F4EA.toChar, "CLOSED MAILBOX WITH LOWERED FLAG"),
    Emoji(0x1F4EB.toChar, "CLOSED MAILBOX WITH RAISED FLAG"),
    Emoji(0x1F4EC.toChar, "OPEN MAILBOX WITH RAISED FLAG"),
    Emoji(0x1F4ED.toChar, "OPEN MAILBOX WITH LOWERED FLAG"),
    Emoji(0x1F4EE.toChar, "POSTBOX"),
    Emoji(0x1F4EF.toChar, "POSTAL HORN"),
    Emoji(0x1F4F0.toChar, "NEWSPAPER"),
    Emoji(0x1F4F1.toChar, "MOBILE PHONE"),
    Emoji(0x1F4F2.toChar, "MOBILE PHONE WITH RIGHTWARDS ARROW AT LEFT"),
    Emoji(0x1F4F3.toChar, "VIBRATION MODE"),
    Emoji(0x1F4F4.toChar, "MOBILE PHONE OFF"),
    Emoji(0x1F4F5.toChar, "NO MOBILE PHONES"),
    Emoji(0x1F4F6.toChar, "ANTENNA WITH BARS"),
    Emoji(0x1F4F7.toChar, "CAMERA"),
    Emoji(0x1F4F9.toChar, "VIDEO CAMERA"),
    Emoji(0x1F4FA.toChar, "TELEVISION"),
    Emoji(0x1F4FB.toChar, "RADIO"),
    Emoji(0x1F4FC.toChar, "VIDEOCASSETTE"),
    Emoji(0x1F500.toChar, "TWISTED RIGHTWARDS ARROWS"),
    Emoji(0x1F501.toChar, "CLOCKWISE RIGHTWARDS AND LEFTWARDS OPEN CIRCLE ARROWS"),
    Emoji(0x1F502.toChar, "CLOCKWISE RIGHTWARDS AND LEFTWARDS OPEN CIRCLE ARROWS WITH CIRCLED ONE OVERLAY"),
    Emoji(0x1F503.toChar, "CLOCKWISE DOWNWARDS AND UPWARDS OPEN CIRCLE ARROWS"),
    Emoji(0x1F504.toChar, "ANTICLOCKWISE DOWNWARDS AND UPWARDS OPEN CIRCLE ARROWS"),
    Emoji(0x1F505.toChar, "LOW BRIGHTNESS SYMBOL"),
    Emoji(0x1F506.toChar, "HIGH BRIGHTNESS SYMBOL"),
    Emoji(0x1F507.toChar, "SPEAKER WITH CANCELLATION STROKE"),
    Emoji(0x1F508.toChar, "SPEAKER"),
    Emoji(0x1F509.toChar, "SPEAKER WITH ONE SOUND WAVE"),
    Emoji(0x1F50A.toChar, "SPEAKER WITH THREE SOUND WAVES"),
    Emoji(0x1F50B.toChar, "BATTERY"),
    Emoji(0x1F50C.toChar, "ELECTRIC PLUG"),
    Emoji(0x1F50D.toChar, "LEFT-POINTING MAGNIFYING GLASS"),
    Emoji(0x1F50E.toChar, "RIGHT-POINTING MAGNIFYING GLASS"),
    Emoji(0x1F50F.toChar, "LOCK WITH INK PEN"),
    Emoji(0x1F510.toChar, "CLOSED LOCK WITH KEY"),
    Emoji(0x1F511.toChar, "KEY"),
    Emoji(0x1F512.toChar, "LOCK"),
    Emoji(0x1F513.toChar, "OPEN LOCK"),
    Emoji(0x1F514.toChar, "BELL"),
    Emoji(0x1F515.toChar, "BELL WITH CANCELLATION STROKE"),
    Emoji(0x1F516.toChar, "BOOKMARK"),
    Emoji(0x1F517.toChar, "LINK SYMBOL"),
    Emoji(0x1F518.toChar, "RADIO BUTTON"),
    Emoji(0x1F519.toChar, "BACK WITH LEFTWARDS ARROW ABOVE"),
    Emoji(0x1F51A.toChar, "END WITH LEFTWARDS ARROW ABOVE"),
    Emoji(0x1F51B.toChar, "ON WITH EXCLAMATION MARK WITH LEFT RIGHT ARROW ABOVE"),
    Emoji(0x1F51C.toChar, "SOON WITH RIGHTWARDS ARROW ABOVE"),
    Emoji(0x1F51D.toChar, "TOP WITH UPWARDS ARROW ABOVE"),
    Emoji(0x1F51E.toChar, "NO ONE UNDER EIGHTEEN SYMBOL"),
    Emoji(0x1F51F.toChar, "KEYCAP TEN"),
    Emoji(0x1F520.toChar, "INPUT SYMBOL FOR LATIN CAPITAL LETTERS"),
    Emoji(0x1F521.toChar, "INPUT SYMBOL FOR LATIN SMALL LETTERS"),
    Emoji(0x1F522.toChar, "INPUT SYMBOL FOR NUMBERS"),
    Emoji(0x1F523.toChar, "INPUT SYMBOL FOR SYMBOLS"),
    Emoji(0x1F524.toChar, "INPUT SYMBOL FOR LATIN LETTERS"),
    Emoji(0x1F525.toChar, "FIRE"),
    Emoji(0x1F526.toChar, "ELECTRIC TORCH"),
    Emoji(0x1F527.toChar, "WRENCH"),
    Emoji(0x1F528.toChar, "HAMMER"),
    Emoji(0x1F529.toChar, "NUT AND BOLT"),
    Emoji(0x1F52A.toChar, "HOCHO"),
    Emoji(0x1F52B.toChar, "PISTOL"),
    Emoji(0x1F52C.toChar, "MICROSCOPE"),
    Emoji(0x1F52D.toChar, "TELESCOPE"),
    Emoji(0x1F52E.toChar, "CRYSTAL BALL"),
    Emoji(0x1F52F.toChar, "SIX POINTED STAR WITH MIDDLE DOT"),
    Emoji(0x1F530.toChar, "JAPANESE SYMBOL FOR BEGINNER"),
    Emoji(0x1F531.toChar, "TRIDENT EMBLEM"),
    Emoji(0x1F532.toChar, "BLACK SQUARE BUTTON"),
    Emoji(0x1F533.toChar, "WHITE SQUARE BUTTON"),
    Emoji(0x1F534.toChar, "LARGE RED CIRCLE"),
    Emoji(0x1F535.toChar, "LARGE BLUE CIRCLE"),
    Emoji(0x1F536.toChar, "LARGE ORANGE DIAMOND"),
    Emoji(0x1F537.toChar, "LARGE BLUE DIAMOND"),
    Emoji(0x1F538.toChar, "SMALL ORANGE DIAMOND"),
    Emoji(0x1F539.toChar, "SMALL BLUE DIAMOND"),
    Emoji(0x1F53A.toChar, "UP-POINTING RED TRIANGLE"),
    Emoji(0x1F53B.toChar, "DOWN-POINTING RED TRIANGLE"),
    Emoji(0x1F53C.toChar, "UP-POINTING SMALL RED TRIANGLE"),
    Emoji(0x1F53D.toChar, "DOWN-POINTING SMALL RED TRIANGLE"),
    Emoji(0x1F550.toChar, "CLOCK FACE ONE OCLOCK"),
    Emoji(0x1F551.toChar, "CLOCK FACE TWO OCLOCK"),
    Emoji(0x1F552.toChar, "CLOCK FACE THREE OCLOCK"),
    Emoji(0x1F553.toChar, "CLOCK FACE FOUR OCLOCK"),
    Emoji(0x1F554.toChar, "CLOCK FACE FIVE OCLOCK"),
    Emoji(0x1F555.toChar, "CLOCK FACE SIX OCLOCK"),
    Emoji(0x1F556.toChar, "CLOCK FACE SEVEN OCLOCK"),
    Emoji(0x1F557.toChar, "CLOCK FACE EIGHT OCLOCK"),
    Emoji(0x1F558.toChar, "CLOCK FACE NINE OCLOCK"),
    Emoji(0x1F559.toChar, "CLOCK FACE TEN OCLOCK"),
    Emoji(0x1F55A.toChar, "CLOCK FACE ELEVEN OCLOCK"),
    Emoji(0x1F55B.toChar, "CLOCK FACE TWELVE OCLOCK"),
    Emoji(0x1F55C.toChar, "CLOCK FACE ONE-THIRTY"),
    Emoji(0x1F55D.toChar, "CLOCK FACE TWO-THIRTY"),
    Emoji(0x1F55E.toChar, "CLOCK FACE THREE-THIRTY"),
    Emoji(0x1F55F.toChar, "CLOCK FACE FOUR-THIRTY"),
    Emoji(0x1F560.toChar, "CLOCK FACE FIVE-THIRTY"),
    Emoji(0x1F561.toChar, "CLOCK FACE SIX-THIRTY"),
    Emoji(0x1F562.toChar, "CLOCK FACE SEVEN-THIRTY"),
    Emoji(0x1F563.toChar, "CLOCK FACE EIGHT-THIRTY"),
    Emoji(0x1F564.toChar, "CLOCK FACE NINE-THIRTY"),
    Emoji(0x1F565.toChar, "CLOCK FACE TEN-THIRTY"),
    Emoji(0x1F566.toChar, "CLOCK FACE ELEVEN-THIRTY"),
    Emoji(0x1F567.toChar, "CLOCK FACE TWELVE-THIRTY"),
    Emoji(0x1F5FB.toChar, "MOUNT FUJI"),
    Emoji(0x1F5FC.toChar, "TOKYO TOWER"),
    Emoji(0x1F5FD.toChar, "STATUE OF LIBERTY"),
    Emoji(0x1F5FE.toChar, "SILHOUETTE OF JAPAN"),
    Emoji(0x1F5FF.toChar, "MOYAI"),
    Emoji(0x1F600.toChar, "GRINNING FACE"),
    Emoji(0x1F601.toChar, "GRINNING FACE WITH SMILING EYES"),
    Emoji(0x1F602.toChar, "FACE WITH TEARS OF JOY"),
    Emoji(0x1F603.toChar, "SMILING FACE WITH OPEN MOUTH"),
    Emoji(0x1F604.toChar, "SMILING FACE WITH OPEN MOUTH AND SMILING EYES"),
    Emoji(0x1F605.toChar, "SMILING FACE WITH OPEN MOUTH AND COLD SWEAT"),
    Emoji(0x1F606.toChar, "SMILING FACE WITH OPEN MOUTH AND TIGHTLY-CLOSED EYES"),
    Emoji(0x1F607.toChar, "SMILING FACE WITH HALO"),
    Emoji(0x1F608.toChar, "SMILING FACE WITH HORNS"),
    Emoji(0x1F609.toChar, "WINKING FACE"),
    Emoji(0x1F60A.toChar, "SMILING FACE WITH SMILING EYES"),
    Emoji(0x1F60B.toChar, "FACE SAVOURING DELICIOUS FOOD"),
    Emoji(0x1F60C.toChar, "RELIEVED FACE"),
    Emoji(0x1F60D.toChar, "SMILING FACE WITH HEART-SHAPED EYES"),
    Emoji(0x1F60E.toChar, "SMILING FACE WITH SUNGLASSES"),
    Emoji(0x1F60F.toChar, "SMIRKING FACE"),
    Emoji(0x1F610.toChar, "NEUTRAL FACE"),
    Emoji(0x1F611.toChar, "EXPRESSIONLESS FACE"),
    Emoji(0x1F612.toChar, "UNAMUSED FACE"),
    Emoji(0x1F613.toChar, "FACE WITH COLD SWEAT"),
    Emoji(0x1F614.toChar, "PENSIVE FACE"),
    Emoji(0x1F615.toChar, "CONFUSED FACE"),
    Emoji(0x1F616.toChar, "CONFOUNDED FACE"),
    Emoji(0x1F617.toChar, "KISSING FACE"),
    Emoji(0x1F618.toChar, "FACE THROWING A KISS"),
    Emoji(0x1F619.toChar, "KISSING FACE WITH SMILING EYES"),
    Emoji(0x1F61A.toChar, "KISSING FACE WITH CLOSED EYES"),
    Emoji(0x1F61B.toChar, "FACE WITH STUCK-OUT TONGUE"),
    Emoji(0x1F61C.toChar, "FACE WITH STUCK-OUT TONGUE AND WINKING EYE"),
    Emoji(0x1F61D.toChar, "FACE WITH STUCK-OUT TONGUE AND TIGHTLY-CLOSED EYES"),
    Emoji(0x1F61E.toChar, "DISAPPOINTED FACE"),
    Emoji(0x1F61F.toChar, "WORRIED FACE"),
    Emoji(0x1F620.toChar, "ANGRY FACE"),
    Emoji(0x1F621.toChar, "POUTING FACE"),
    Emoji(0x1F622.toChar, "CRYING FACE"),
    Emoji(0x1F623.toChar, "PERSEVERING FACE"),
    Emoji(0x1F624.toChar, "FACE WITH LOOK OF TRIUMPH"),
    Emoji(0x1F625.toChar, "DISAPPOINTED BUT RELIEVED FACE"),
    Emoji(0x1F626.toChar, "FROWNING FACE WITH OPEN MOUTH"),
    Emoji(0x1F627.toChar, "ANGUISHED FACE"),
    Emoji(0x1F628.toChar, "FEARFUL FACE"),
    Emoji(0x1F629.toChar, "WEARY FACE"),
    Emoji(0x1F62A.toChar, "SLEEPY FACE"),
    Emoji(0x1F62B.toChar, "TIRED FACE"),
    Emoji(0x1F62C.toChar, "GRIMACING FACE"),
    Emoji(0x1F62D.toChar, "LOUDLY CRYING FACE"),
    Emoji(0x1F62E.toChar, "FACE WITH OPEN MOUTH"),
    Emoji(0x1F62F.toChar, "HUSHED FACE"),
    Emoji(0x1F630.toChar, "FACE WITH OPEN MOUTH AND COLD SWEAT"),
    Emoji(0x1F631.toChar, "FACE SCREAMING IN FEAR"),
    Emoji(0x1F632.toChar, "ASTONISHED FACE"),
    Emoji(0x1F633.toChar, "FLUSHED FACE"),
    Emoji(0x1F634.toChar, "SLEEPING FACE"),
    Emoji(0x1F635.toChar, "DIZZY FACE"),
    Emoji(0x1F636.toChar, "FACE WITHOUT MOUTH"),
    Emoji(0x1F637.toChar, "FACE WITH MEDICAL MASK"),
    Emoji(0x1F638.toChar, "GRINNING CAT FACE WITH SMILING EYES"),
    Emoji(0x1F639.toChar, "CAT FACE WITH TEARS OF JOY"),
    Emoji(0x1F63A.toChar, "SMILING CAT FACE WITH OPEN MOUTH"),
    Emoji(0x1F63B.toChar, "SMILING CAT FACE WITH HEART-SHAPED EYES"),
    Emoji(0x1F63C.toChar, "CAT FACE WITH WRY SMILE"),
    Emoji(0x1F63D.toChar, "KISSING CAT FACE WITH CLOSED EYES"),
    Emoji(0x1F63E.toChar, "POUTING CAT FACE"),
    Emoji(0x1F63F.toChar, "CRYING CAT FACE"),
    Emoji(0x1F640.toChar, "WEARY CAT FACE"),
    Emoji(0x1F645.toChar, "FACE WITH NO GOOD GESTURE"),
    Emoji(0x1F646.toChar, "FACE WITH OK GESTURE"),
    Emoji(0x1F647.toChar, "PERSON BOWING DEEPLY"),
    Emoji(0x1F648.toChar, "SEE-NO-EVIL MONKEY"),
    Emoji(0x1F649.toChar, "HEAR-NO-EVIL MONKEY"),
    Emoji(0x1F64A.toChar, "SPEAK-NO-EVIL MONKEY"),
    Emoji(0x1F64B.toChar, "HAPPY PERSON RAISING ONE HAND"),
    Emoji(0x1F64C.toChar, "PERSON RAISING BOTH HANDS IN CELEBRATION"),
    Emoji(0x1F64D.toChar, "PERSON FROWNING"),
    Emoji(0x1F64E.toChar, "PERSON WITH POUTING FACE"),
    Emoji(0x1F64F.toChar, "PERSON WITH FOLDED HANDS"),
    Emoji(0x1F680.toChar, "ROCKET"),
    Emoji(0x1F681.toChar, "HELICOPTER"),
    Emoji(0x1F682.toChar, "STEAM LOCOMOTIVE"),
    Emoji(0x1F683.toChar, "RAILWAY CAR"),
    Emoji(0x1F684.toChar, "HIGH-SPEED TRAIN"),
    Emoji(0x1F685.toChar, "HIGH-SPEED TRAIN WITH BULLET NOSE"),
    Emoji(0x1F686.toChar, "TRAIN"),
    Emoji(0x1F687.toChar, "METRO"),
    Emoji(0x1F688.toChar, "LIGHT RAIL"),
    Emoji(0x1F689.toChar, "STATION"),
    Emoji(0x1F68A.toChar, "TRAM"),
    Emoji(0x1F68B.toChar, "TRAM CAR"),
    Emoji(0x1F68C.toChar, "BUS"),
    Emoji(0x1F68D.toChar, "ONCOMING BUS"),
    Emoji(0x1F68E.toChar, "TROLLEYBUS"),
    Emoji(0x1F68F.toChar, "BUS STOP"),
    Emoji(0x1F690.toChar, "MINIBUS"),
    Emoji(0x1F691.toChar, "AMBULANCE"),
    Emoji(0x1F692.toChar, "FIRE ENGINE"),
    Emoji(0x1F693.toChar, "POLICE CAR"),
    Emoji(0x1F694.toChar, "ONCOMING POLICE CAR"),
    Emoji(0x1F695.toChar, "TAXI"),
    Emoji(0x1F696.toChar, "ONCOMING TAXI"),
    Emoji(0x1F697.toChar, "AUTOMOBILE"),
    Emoji(0x1F698.toChar, "ONCOMING AUTOMOBILE"),
    Emoji(0x1F699.toChar, "RECREATIONAL VEHICLE"),
    Emoji(0x1F69A.toChar, "DELIVERY TRUCK"),
    Emoji(0x1F69B.toChar, "ARTICULATED LORRY"),
    Emoji(0x1F69C.toChar, "TRACTOR"),
    Emoji(0x1F69D.toChar, "MONORAIL"),
    Emoji(0x1F69E.toChar, "MOUNTAIN RAILWAY"),
    Emoji(0x1F69F.toChar, "SUSPENSION RAILWAY"),
    Emoji(0x1F6A0.toChar, "MOUNTAIN CABLEWAY"),
    Emoji(0x1F6A1.toChar, "AERIAL TRAMWAY"),
    Emoji(0x1F6A2.toChar, "SHIP"),
    Emoji(0x1F6A3.toChar, "ROWBOAT"),
    Emoji(0x1F6A4.toChar, "SPEEDBOAT"),
    Emoji(0x1F6A5.toChar, "HORIZONTAL TRAFFIC LIGHT"),
    Emoji(0x1F6A6.toChar, "VERTICAL TRAFFIC LIGHT"),
    Emoji(0x1F6A7.toChar, "CONSTRUCTION SIGN"),
    Emoji(0x1F6A8.toChar, "POLICE CARS REVOLVING LIGHT"),
    Emoji(0x1F6A9.toChar, "TRIANGULAR FLAG ON POST"),
    Emoji(0x1F6AA.toChar, "DOOR"),
    Emoji(0x1F6AB.toChar, "NO ENTRY SIGN"),
    Emoji(0x1F6AC.toChar, "SMOKING SYMBOL"),
    Emoji(0x1F6AD.toChar, "NO SMOKING SYMBOL"),
    Emoji(0x1F6AE.toChar, "PUT LITTER IN ITS PLACE SYMBOL"),
    Emoji(0x1F6AF.toChar, "DO NOT LITTER SYMBOL"),
    Emoji(0x1F6B0.toChar, "POTABLE WATER SYMBOL"),
    Emoji(0x1F6B1.toChar, "NON-POTABLE WATER SYMBOL"),
    Emoji(0x1F6B2.toChar, "BICYCLE"),
    Emoji(0x1F6B3.toChar, "NO BICYCLES"),
    Emoji(0x1F6B4.toChar, "BICYCLIST"),
    Emoji(0x1F6B5.toChar, "MOUNTAIN BICYCLIST"),
    Emoji(0x1F6B6.toChar, "PEDESTRIAN"),
    Emoji(0x1F6B7.toChar, "NO PEDESTRIANS"),
    Emoji(0x1F6B8.toChar, "CHILDREN CROSSING"),
    Emoji(0x1F6B9.toChar, "MENS SYMBOL"),
    Emoji(0x1F6BA.toChar, "WOMENS SYMBOL"),
    Emoji(0x1F6BB.toChar, "RESTROOM"),
    Emoji(0x1F6BC.toChar, "BABY SYMBOL"),
    Emoji(0x1F6BD.toChar, "TOILET"),
    Emoji(0x1F6BE.toChar, "WATER CLOSET"),
    Emoji(0x1F6BF.toChar, "SHOWER"),
    Emoji(0x1F6C0.toChar, "BATH"),
    Emoji(0x1F6C1.toChar, "BATHTUB"),
    Emoji(0x1F6C2.toChar, "PASSPORT CONTROL"),
    Emoji(0x1F6C3.toChar, "CUSTOMS"),
    Emoji(0x1F6C4.toChar, "BAGGAGE CLAIM"),
    Emoji(0x1F6C5.toChar, "LEFT LUGGAGE")).map(e => (e.char, e)).toMap

//    Not sure what it means when the code has a hyphen -  Alternates?  Two character sequences?  Research did not turn
//    anything up.
//
//    Emoji(0x0023-20E3.toChar, "HASH KEY"),
//    Emoji(0x0030-20E3.toChar, "KEYCAP 0"),
//    Emoji(0x0031-20E3.toChar, "KEYCAP 1"),
//    Emoji(0x0032-20E3.toChar, "KEYCAP 2"),
//    Emoji(0x0033-20E3.toChar, "KEYCAP 3"),
//    Emoji(0x0034-20E3.toChar, "KEYCAP 4"),
//    Emoji(0x0035-20E3.toChar, "KEYCAP 5"),
//    Emoji(0x0036-20E3.toChar, "KEYCAP 6"),
//    Emoji(0x0037-20E3.toChar, "KEYCAP 7"),
//    Emoji(0x0038-20E3.toChar, "KEYCAP 8"),
//    Emoji(0x0039-20E3.toChar, "KEYCAP 9"),
//    Emoji(0x1F1E8-1F1F3.toChar, "REGIONAL INDICATOR SYMBOL LETTERS CN"),
//    Emoji(0x1F1E9-1F1EA.toChar, "REGIONAL INDICATOR SYMBOL LETTERS DE"),
//    Emoji(0x1F1EA-1F1F8.toChar, "REGIONAL INDICATOR SYMBOL LETTERS ES"),
//    Emoji(0x1F1EB-1F1F7.toChar, "REGIONAL INDICATOR SYMBOL LETTERS FR"),
//    Emoji(0x1F1EC-1F1E7.toChar, "REGIONAL INDICATOR SYMBOL LETTERS GB"),
//    Emoji(0x1F1EE-1F1F9.toChar, "REGIONAL INDICATOR SYMBOL LETTERS IT"),
//    Emoji(0x1F1EF-1F1F5.toChar, "REGIONAL INDICATOR SYMBOL LETTERS JP"),
//    Emoji(0x1F1F0-1F1F7.toChar, "REGIONAL INDICATOR SYMBOL LETTERS KR"),
//    Emoji(0x1F1F7-1F1FA.toChar, "REGIONAL INDICATOR SYMBOL LETTERS RU"),
//    Emoji(0x1F1FA-1F1F8.toChar, "REGIONAL INDICATOR SYMBOL LETTERS US")
}

