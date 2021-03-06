package moe.sdl.yabapi.enums.video

import kotlinx.serialization.Serializable
import moe.sdl.yabapi.consts.internal.WWW
import moe.sdl.yabapi.serializer.data.VideoTypeSerializer
import moe.sdl.yabapi.util.Logger
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger by lazy { Logger("VideoType") }

@Serializable(VideoTypeSerializer::class)
public sealed class VideoType(
    public val name: String,
    public val code: String,
    public val tid: Int,
    public val route: String,
    public val parent: VideoType? = null,
) {
    public fun getUrl(): String = "$WWW$route"

    override fun toString(): String = "$tid($name)"

    public companion object {
        // Long list, register video type, can replace with Annotation Processor, but KSP in experimental.
        public fun getAllTypes(): List<VideoType> = listOf(
            All, Unknown,
            Douga, Douga.MAD, Douga.MMD, Douga.Voice, Douga.Other, Douga.GarageKit, Douga.Tokusatsu,
            Anime, Anime.Serial, Anime.Finish, Anime.Information, Anime.Official,
            Guochuang, Guochuang.Donghua, Guochuang.Original, Guochuang.Puppetry, Guochuang.MotionComic, Guochuang.MotionComic, Guochuang.Information,
            Music, Music.Original, Music.Cover, Music.Vocaloid, Music.Electronic, Music.Perform, Music.MV, Music.Live, Music.Other,
            Dance, Dance.Otaku, Dance.Hiphop, Dance.Star, Dance.China, Dance.ThreeD, Dance.Demo,
            Game, Game.Standalone, Game.ESports, Game.Mobile, Game.Online, Game.Board, Game.GMV, Game.Music, Game.Mugen,
            Knowledge, Knowledge.Science, Knowledge.SocialScience, Knowledge.HumanityHistory, Knowledge.Business, Knowledge.Campus, Knowledge.Career, Knowledge.Design, Knowledge.Skill,
            Tech, Tech.Digital, Tech.Application, Tech.ComputerTech, Tech.Industry, Tech.DIY,
            Car, Car.Life, Car.Culture, Car.Geek, Car.Smart, Car.Strategy,
            Sports, Sports.Ball, Sports.Aerobics, Sports.Atheletic, Sports.Culture, Sports.Comprehensive,
            Life, Life.Funny, Life.Home, Life.HandMake, Life.Painting, Life.Daily,
            Food, Food.Make, Food.Measurement, Food.Rural, Food.Record,
            Animal, Animal.Cat, Animal.Dog, Animal.Panda, Animal.Wild, Animal.Reptiles, Animal.Composite,
            Kichiku, Kichiku.Guide, Kichiku.MAD, Kichiku.ManualVocaloid, Kichiku.Theatre, Kichiku.Course,
            Fashion, Fashion.Makeup, Fashion.Clothing, Fashion.Catwalk, Fashion.Trends,
            Information, Information.Hotspot, Information.Global, Information.Social, Information.Multiple,
            Entertainment, Entertainment.Variety, Entertainment.Star,
            Cinephile, Cinephile.Cinecism, Cinephile.Montage, Cinephile.ShortFilm, Cinephile.TrailerInfo,
            Documentary, Documentary.History, Documentary.Science, Documentary.Military, Documentary.Travel,
            Movie, Movie.Chinese, Movie.West, Movie.Japan, Movie.Other,
            TV, TV.Mainland, TV.Overseas,
        )

        public fun getAllUrl(): List<String> = getAllTypes().map { it.getUrl() }
        public fun fromTid(tid: Int): VideoType = getAllTypes().firstOrNull { it.tid == tid } ?: run {
            logger.debug { "Unexpected VideoType Id: $tid, fallback to Unknown" }
            Unknown
        }

        public fun fromCode(string: String): VideoType =
            getAllTypes().firstOrNull { it.code == string.lowercase() } ?: Unknown
    }
}

public object All : VideoType("??????", "all", 0, "")

public object Unknown : VideoType("??????", "unknown", -1, "")

public object Douga : VideoType("??????", "douga", 1, "/v/douga") {
    public object MAD : VideoType("MAD??AMV", "mad", 24, "/v/douga/mad", Douga)
    public object MMD : VideoType("MMD??3D", "mmd", 25, "/v/douga/mmd", Douga)
    public object Other : VideoType("??????", "other", 27, "/v/douga/other", Douga)
    public object Voice : VideoType("??????????????????????", "voice", 47, "/v/douga/voice", Douga)
    public object GarageKit : VideoType("??????????????", "garage_kit", 210, "/v/douga/garage_kit", Douga)
    public object Tokusatsu : VideoType("??????", "tokusatsu", 86, "/v/douga/other", Douga)
}

public object Anime : VideoType("??????", "anime", 13, "/anime") {
    public object Serial : VideoType("????????????", "serial", 33, "/v/anime/serial", Anime)
    public object Finish : VideoType("????????????", "finish", 32, "/v/anime/finish", Anime)
    public object Information : VideoType("??????", "information", 51, "/v/anime/information", Anime)
    public object Official : VideoType("????????????", "official", 152, "/v/anime/official", Anime)
}

public object Guochuang : VideoType("??????", "guochuang", 167, "/guochuang") {
    public object Donghua : VideoType("????????????", "chinese", 153, "/v/guochuang/chinese", Guochuang)
    public object Original : VideoType("??????????????????", "original", 168, "/v/guochuang/original", Guochuang)
    public object Puppetry : VideoType("?????????", "puppetry", 169, "/v/guochuang/puppetry", Guochuang)
    public object MotionComic : VideoType("?????????", "motioncomic", 195, "/v/guochuang/motioncomic", Guochuang)
    public object Information : VideoType("??????", "information", 170, "/v/guochuang/information", Guochuang)
}

public object Music : VideoType("??????", "music", 3, "/v/music") {
    public object Original : VideoType("????????????", "original", 28, "/v/music/original", Music)
    public object Cover : VideoType("??????", "cover", 31, "/v/music/cover", Music)
    public object Vocaloid : VideoType("VOCALOID??UTAU", "vocaloid", 30, "/v/music/vocaloid", Music)
    public object Electronic : VideoType("??????", "electronic", 194, "/v/music/electronic", Music)
    public object Perform : VideoType("??????", "perform", 59, "/v/music/perform", Music)
    public object MV : VideoType("MV", "mv", 193, "/v/music/mv", Music)
    public object Live : VideoType("????????????", "live", 29, "/v/music/live", Music)
    public object Other : VideoType("????????????", "other", 130, "/v/music/other", Music)
}

public object Dance : VideoType("??????	", "dance", 129, "/v/dance") {
    public object Otaku : VideoType("??????", "otaku", 20, "/v/dance/otaku", Dance)
    public object Hiphop : VideoType("??????", "hiphop", 198, "/v/dance/hiphop", Dance)
    public object Star : VideoType("????????????", "star", 199, "/v/dance/star", Dance)
    public object China : VideoType("?????????", "china", 200, "/v/dance/china", Dance)
    public object ThreeD : VideoType("????????????", "three_d", 154, "/v/dance/three_d", Dance)
    public object Demo : VideoType("????????????", "demo", 156, "/v/dance/demo", Dance)
}

public object Game : VideoType("??????", "game", 4, "/v/game") {
    public object Standalone : VideoType("????????????", "stand_alone", 17, "/v/game/stand_alone", Game)
    public object ESports : VideoType("????????????", "esports", 171, "/v/game/esports", Game)
    public object Mobile : VideoType("????????????", "mobile", 172, "/v/game/mobile", Game)
    public object Online : VideoType("????????????", "online", 65, "/v/game/online", Game)
    public object Board : VideoType("????????????", "board", 173, "/v/game/board", Game)
    public object GMV : VideoType("GMV", "gmv", 121, "/v/game/gmv", Game)
    public object Music : VideoType("??????", "music", 136, "/v/game/music", Game)
    public object Mugen : VideoType("Mugen", "mugen", 19, "/v/game/mugen", Game)
}

public object Knowledge : VideoType("??????", "knowledge", 36, "/v/knowledge") {
    public object Science : VideoType("????????????", "science", 201, "/v/knowledge/science", Knowledge)
    public object SocialScience : VideoType("??????????????????????", "social_science", 124, "/v/knowledge/social_science", Knowledge)
    public object HumanityHistory : VideoType("????????????", "humanity_history", 228, "/v/knowledge/humanity_history", Knowledge)
    public object Business : VideoType("????????????", "business", 207, "/v/knowledge/finance", Knowledge)
    public object Campus : VideoType("????????????", "campus", 208, "/v/knowledge/campus", Knowledge)
    public object Career : VideoType("????????????", "career", 209, "/v/knowledge/career", Knowledge)
    public object Design : VideoType("??????????????", "design", 229, "/v/knowledge/design", Knowledge)
    public object Skill : VideoType("??????????????????", "skill", 122, "/v/knowledge/skill", Knowledge)
}

public object Tech : VideoType("??????", "tech", 188, "/v/tech") {
    public object Digital : VideoType("??????", "digital", 95, "/v/tech/digital", Tech)
    public object Application : VideoType("????????????", "application", 230, "/v/tech/application", Tech)
    public object ComputerTech : VideoType("???????????????", "computer_tech", 231, "/v/tech/computer_tech", Tech)
    public object Industry : VideoType("??????????????????????", "industry", 232, "/v/tech/industry", Tech)
    public object DIY : VideoType("??????DIY", "diy", 233, "/v/tech/diy", Tech)
}

public object Sports : VideoType("??????", "sports", 234, "/v/sports") {
    public object Ball : VideoType("??????????????", "basketballfootball", 235, "/v/sports/basketballfootball", Sports)
    public object Aerobics : VideoType("??????", "aerobics", 164, "/v/sports/aerobics", Sports)
    public object Atheletic : VideoType("????????????", "athletic", 236, "/v/sports/culture", Sports)
    public object Culture : VideoType("????????????", "culture", 237, "/v/sports/culture", Sports)
    public object Comprehensive : VideoType("????????????", "comprehensive", 238, "/v/sports/comprehensive", Sports)
}

public object Car : VideoType("??????", "car", 223, "/v/car") {
    public object Life : VideoType("????????????", "life", 176, "/v/car/life", Car)
    public object Culture : VideoType("????????????", "culture", 224, "/v/car/culture", Car)
    public object Geek : VideoType("????????????", "geek", 225, "/v/car/geek", Car)
    public object Smart : VideoType("????????????", "smart", 226, "/v/car/smart", Car)
    public object Strategy : VideoType("????????????", "strategy", 227, "/v/car/strategy", Car)
}

public object Life : VideoType("??????", "life", 160, "/v/life") {
    public object Funny : VideoType("??????", "funny", 138, "/v/life/funny", Life)
    public object Home : VideoType("????????????", "home", 239, "/v/life/home", Life)
    public object HandMake : VideoType("??????", "handmake", 161, "/v/life/handmake", Life)
    public object Painting : VideoType("??????", "painting", 162, "/v/life/painting", Life)
    public object Daily : VideoType("??????", "daily", 21, "/v/life/daily", Life)
}

public object Food : VideoType("??????", "food", 211, "/v/food") {
    public object Make : VideoType("????????????", "make", 76, "/v/food/make", Food)
    public object Detective : VideoType("????????????", "detective", 212, "/v/food/detective", Food)
    public object Measurement : VideoType("????????????", "measurement", 213, "/v/food/measurement", Food)
    public object Rural : VideoType("????????????", "rural", 214, "/v/food/rural", Food)
    public object Record : VideoType("????????????", "record", 215, "/v/food/record", Food)
}

public object Animal : VideoType("?????????", "animal", 217, "/v/animal") {
    public object Cat : VideoType("?????????", "cat", 218, "/v/animal/cat", Animal)
    public object Dog : VideoType("?????????", "dog", 219, "/v/animal/dog", Animal)
    public object Panda : VideoType("?????????", "panda", 220, "/v/animal/panda", Animal)
    public object Wild : VideoType("????????????", "wild_animal", 221, "/v/animal/wild_animal", Animal)
    public object Reptiles : VideoType("??????", "reptiles", 222, "/v/animal/reptiles", Animal)
    public object Composite : VideoType("????????????", "animal_composite", 75, "/v/animal/animal_composite", Animal)
}

public object Kichiku : VideoType("??????", "kichiku", 119, "/v/kichiku") {
    public object Guide : VideoType("????????????", "guide", 22, "/v/kichiku/guide", Kichiku)
    public object MAD : VideoType("???MAD", "mad", 26, "/v/kichiku/mad/v/kichiku/mad", Kichiku)
    public object ManualVocaloid : VideoType("??????VOCALOID", "manual_vocaloid", 126, "/v/kichiku/manual_vocaloid", Kichiku)
    public object Theatre : VideoType("????????????", "theatre", 216, "/v/kichiku/theatre", Kichiku)
    public object Course : VideoType("????????????", "course", 127, "/v/kichiku/course", Kichiku)
}

public object Fashion : VideoType("??????", "Fashion", 155, "/v/fashion") {
    public object Makeup : VideoType("??????", "makeup", 157, "/v/fashion/makeup", Fashion)
    public object Clothing : VideoType("??????", "clothing", 158, "/v/fashion/clothing", Fashion)
    public object Catwalk : VideoType("T???", "catwalk", 159, "/v/fashion/catwalk", Fashion)
    public object Trends : VideoType("?????????", "trends", 192, "/v/fashion/trends", Fashion)
}

public object Information : VideoType("??????", "information", 202, "/v/information") {
    public object Hotspot : VideoType("??????", "hotspot", 203, "/v/information/hotspot", Information)
    public object Global : VideoType("??????", "global", 204, "/v/information/global", Information)
    public object Social : VideoType("??????", "social", 205, "/v/information/social", Information)
    public object Multiple : VideoType("??????", "multiple", 206, "/v/information/multiple", Information)
}

public object Entertainment : VideoType("??????", "ent", 5, "/v/ent") {
    public object Variety : VideoType("??????", "variety", 71, "/v/ent/variety", Entertainment)
    public object Star : VideoType("??????", "star", 137, "/v/ent/star", Entertainment)
}

public object Cinephile : VideoType("??????", "cinephile", 181, "/v/cinphile") {
    public object Cinecism : VideoType("????????????", "cinecism", 182, "/v/cinephile/cinecism", Cinephile)
    public object Montage : VideoType("????????????", "montage", 183, "/v/cinephile/montage", Cinephile)
    public object ShortFilm : VideoType("??????", "shortfilm", 85, "/v/cinephile/shortfilm", Cinephile)
    public object TrailerInfo : VideoType("??????????????", "trailer_info", 184, "/v/cinephile/trailer_info", Cinephile)
}

public object Documentary : VideoType("?????????", "documentary", 177, "/documentary") {
    public object History : VideoType("??????????????", "history", 37, "/v/documentary/history", Documentary)
    public object Science : VideoType("??????????????????????", "science", 178, "/v/documentary/science", Documentary)
    public object Military : VideoType("??????", "military", 179, "/v/documentary/military", Documentary)
    public object Travel : VideoType("??????????????????????", "travel", 180, "/v/documentary/travel", Documentary)
}

public object Movie : VideoType("??????", "movie", 23, "/movie") {
    public object Chinese : VideoType("????????????", "chinese", 147, "/v/movie/chinese", Movie)
    public object West : VideoType("????????????", "west", 145, "/v/movie/west", Movie)
    public object Japan : VideoType("????????????", "japan", 146, "/v/movie/japan", Movie)
    public object Other : VideoType("????????????", "movie", 83, "/v/movie/movie", Movie)
}

public object TV : VideoType("?????????", "tv", 11, "/tv") {
    public object Mainland : VideoType("?????????", "mainland", 185, "/v/tv/mainland", TV)
    public object Overseas : VideoType("?????????", "overseas", 187, "/v/tv/overseas", TV)
}
