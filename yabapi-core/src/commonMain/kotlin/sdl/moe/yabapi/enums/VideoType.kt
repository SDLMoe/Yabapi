// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.enums

import kotlinx.serialization.Serializable
import sdl.moe.yabapi.consts.WWW
import sdl.moe.yabapi.serializer.data.VideoTypeSerializer
import sdl.moe.yabapi.util.logger

@Serializable(VideoTypeSerializer::class)
public sealed class VideoType(
    public val name: String,
    public val code: String,
    public val tid: Int,
    public val route: String,
) {
    public fun getUrl(): String = "$WWW$route"

    public companion object {
        // Long list, register video type, can replace with Annotation Processor, but KSP in experimental.
        public fun getAllTypes(): List<VideoType> = listOf(
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
            Life, Life.Funny, Life.Home, Life.Handmake, Life.Painting, Life.Daily,
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

public object Unknown : VideoType("未知", "unknown", -1, "")

public object Douga : VideoType("动画", "douga", 1, "/v/douga") {
    public object MAD : VideoType("MAD·AMV", "mad", 24, "/v/douga/mad")
    public object MMD : VideoType("MMD·3D", "mmd", 25, "/v/douga/mmd")
    public object Other : VideoType("综合", "other", 27, "/v/douga/other")
    public object Voice : VideoType("短片·手书·配音", "voice", 47, "/v/douga/voice")
    public object GarageKit : VideoType("手办·模玩", "garage_kit", 210, "/v/douga/garage_kit")
    public object Tokusatsu : VideoType("特摄", "tokusatsu", 86, "/v/douga/other")
}

public object Anime : VideoType("番剧", "anime", 13, "/anime") {
    public object Serial : VideoType("连载动画", "serial", 33, "/v/anime/serial")
    public object Finish : VideoType("完结动画", "finish", 32, "/v/anime/finish")
    public object Information : VideoType("资讯", "information", 51, "/v/anime/information")
    public object Official : VideoType("官方延伸", "official", 152, "/v/anime/official")
}

public object Guochuang : VideoType("国创", "guochuang", 167, "/guochuang") {
    public object Donghua : VideoType("国产动画", "chinese", 153, "/v/guochuang/chinese")
    public object Original : VideoType("国产原创相关", "original", 168, "/v/guochuang/original")
    public object Puppetry : VideoType("布袋戏", "puppetry", 169, "/v/guochuang/puppetry")
    public object MotionComic : VideoType("布袋戏", "motioncomic", 195, "/v/guochuang/motioncomic")
    public object Information : VideoType("资讯", "information", 170, "/v/guochuang/information")
}

public object Music : VideoType("音乐", "music", 3, "/v/music") {
    public object Original : VideoType("原创音乐", "original", 28, "/v/music/original")
    public object Cover : VideoType("翻唱", "cover", 31, "/v/music/cover")
    public object Vocaloid : VideoType("VOCALOID·UTAU", "vocaloid", 30, "/v/music/vocaloid")
    public object Electronic : VideoType("电音", "electronic", 194, "/v/music/electronic")
    public object Perform : VideoType("演奏", "perform", 59, "/v/music/perform")
    public object MV : VideoType("MV", "mv", 193, "/v/music/mv")
    public object Live : VideoType("音乐现场", "live", 29, "/v/music/live")
    public object Other : VideoType("音乐综合", "other", 130, "/v/music/other")
}

public object Dance : VideoType("舞蹈	", "dance", 129, "/v/dance") {
    public object Otaku : VideoType("宅舞", "otaku", 20, "/v/dance/otaku")
    public object Hiphop : VideoType("街舞", "hiphop", 198, "/v/dance/hiphop")
    public object Star : VideoType("明星舞蹈", "star", 199, "/v/dance/star")
    public object China : VideoType("中国舞", "china", 200, "/v/dance/china")
    public object ThreeD : VideoType("舞蹈综合", "three_d", 154, "/v/dance/three_d")
    public object Demo : VideoType("舞蹈教程", "demo", 156, "/v/dance/demo")
}

public object Game : VideoType("游戏", "game", 4, "/v/game") {
    public object Standalone : VideoType("单机游戏", "stand_alone", 17, "/v/game/stand_alone")
    public object ESports : VideoType("电子竞技", "esports", 171, "/v/game/esports")
    public object Mobile : VideoType("手机游戏", "mobile", 172, "/v/game/mobile")
    public object Online : VideoType("网络游戏", "online", 65, "/v/game/online")
    public object Board : VideoType("桌游棋牌", "board", 173, "/v/game/board")
    public object GMV : VideoType("GMV", "gmv", 121, "/v/game/gmv")
    public object Music : VideoType("音游", "music", 136, "/v/game/music")
    public object Mugen : VideoType("Mugen", "mugen", 19, "/v/game/mugen")
}

public object Knowledge : VideoType("知识", "knowledge", 36, "/v/knowledge") {
    public object Science : VideoType("科学科普", "science", 201, "/v/knowledge/science")
    public object SocialScience : VideoType("社科·法律·心理", "social_science", 124, "/v/knowledge/social_science")
    public object HumanityHistory : VideoType("人文历史", "humanity_history", 228, "/v/knowledge/humanity_history")
    public object Business : VideoType("财经商业", "business", 207, "/v/knowledge/finance")
    public object Campus : VideoType("校园学习", "campus", 208, "/v/knowledge/campus")
    public object Career : VideoType("职业职场", "career", 209, "/v/knowledge/career")
    public object Design : VideoType("设计·创意", "design", 229, "/v/knowledge/design")
    public object Skill : VideoType("野生技术协会", "skill", 122, "/v/knowledge/skill")
}

public object Tech : VideoType("科技", "tech", 188, "/v/tech") {
    public object Digital : VideoType("数码", "digital", 95, "/v/tech/digital")
    public object Application : VideoType("软件应用", "application", 230, "/v/tech/application")
    public object ComputerTech : VideoType("计算机技术", "computer_tech", 231, "/v/tech/computer_tech")
    public object Industry : VideoType("工业·工程·机械", "industry", 232, "/v/tech/industry")
    public object DIY : VideoType("极客DIY", "diy", 233, "/v/tech/diy")
}

public object Sports : VideoType("运动", "sports", 234, "/v/sports") {
    public object Ball : VideoType("篮球·足球", "basketballfootball", 235, "/v/sports/basketballfootball")
    public object Aerobics : VideoType("健身", "aerobics", 164, "/v/sports/aerobics")
    public object Atheletic : VideoType("竞技体育", "athletic", 236, "/v/sports/culture")
    public object Culture : VideoType("运动文化", "culture", 237, "/v/sports/culture")
    public object Comprehensive : VideoType("运动综合", "comprehensive", 238, "/v/sports/comprehensive")
}

public object Car : VideoType("汽车", "car", 223, "/v/car") {
    public object Life : VideoType("汽车生活", "life", 176, "/v/car/life")
    public object Culture : VideoType("汽车文化", "culture", 224, "/v/car/culture")
    public object Geek : VideoType("汽车极客", "geek", 225, "/v/car/geek")
    public object Smart : VideoType("智能出行", "smart", 226, "/v/car/smart")
    public object Strategy : VideoType("购车攻略", "strategy", 227, "/v/car/strategy")
}

public object Life : VideoType("生活", "life", 160, "/v/life") {
    public object Funny : VideoType("搞笑", "funny", 138, "/v/life/funny")
    public object Home : VideoType("家居房产", "home", 239, "/v/life/home")
    public object Handmake : VideoType("手工", "handmake", 161, "/v/life/handmake")
    public object Painting : VideoType("绘画", "painting", 162, "/v/life/painting")
    public object Daily : VideoType("日常", "daily", 21, "/v/life/daily")
}

public object Food : VideoType("美食", "food", 211, "/v/food") {
    public object Make : VideoType("美食制作", "make", 76, "/v/food/make")
    public object Detective : VideoType("美食侦探", "detective", 212, "/v/food/detective")
    public object Measurement : VideoType("美食测评", "measurement", 213, "/v/food/measurement")
    public object Rural : VideoType("田园美食", "rural", 214, "/v/food/rural")
    public object Record : VideoType("美食记录", "record", 215, "/v/food/record")
}

public object Animal : VideoType("动物圈", "animal", 217, "/v/animal") {
    public object Cat : VideoType("喵星人", "cat", 218, "/v/animal/cat")
    public object Dog : VideoType("汪星人", "dog", 219, "/v/animal/dog")
    public object Panda : VideoType("大熊猫", "panda", 220, "/v/animal/panda")
    public object Wild : VideoType("野生动物", "wild_animal", 221, "/v/animal/wild_animal")
    public object Reptiles : VideoType("爬宠", "reptiles", 222, "/v/animal/reptiles")
    public object Composite : VideoType("动物综合", "animal_composite", 75, "/v/animal/animal_composite")
}

public object Kichiku : VideoType("鬼畜", "kichiku", 119, "/v/kichiku") {
    public object Guide : VideoType("鬼畜调教", "guide", 22, "/v/kichiku/guide")
    public object MAD : VideoType("音MAD", "mad", 26, "/v/kichiku/mad/v/kichiku/mad")
    public object ManualVocaloid : VideoType("人力VOCALOID", "manual_vocaloid", 126, "/v/kichiku/manual_vocaloid")
    public object Theatre : VideoType("鬼畜剧场", "theatre", 216, "/v/kichiku/theatre")
    public object Course : VideoType("教程演示", "course", 127, "/v/kichiku/course")
}

public object Fashion : VideoType("时尚", "Fashion", 155, "/v/fashion") {
    public object Makeup : VideoType("美妆", "makeup", 157, "/v/fashion/makeup")
    public object Clothing : VideoType("服饰", "clothing", 158, "/v/fashion/clothing")
    public object Catwalk : VideoType("T台", "catwalk", 159, "/v/fashion/catwalk")
    public object Trends : VideoType("风尚标", "trends", 192, "/v/fashion/trends")
}

public object Information : VideoType("资讯", "information", 202, "/v/information") {
    public object Hotspot : VideoType("热点", "hotspot", 203, "/v/information/hotspot")
    public object Global : VideoType("环球", "global", 204, "/v/information/global")
    public object Social : VideoType("社会", "social", 205, "/v/information/social")
    public object Multiple : VideoType("综合", "multiple", 206, "/v/information/multiple")
}

public object Entertainment : VideoType("娱乐", "ent", 5, "/v/ent") {
    public object Variety : VideoType("综艺", "variety", 71, "/v/ent/variety")
    public object Star : VideoType("明星", "star", 137, "/v/ent/star")
}

public object Cinephile : VideoType("影视", "cinephile", 181, "/v/cinphile") {
    public object Cinecism : VideoType("影视杂谈", "cinecism", 182, "/v/cinephile/cinecism")
    public object Montage : VideoType("影视剪辑", "montage", 183, "/v/cinephile/montage")
    public object ShortFilm : VideoType("短片", "shortfilm", 85, "/v/cinephile/shortfilm")
    public object TrailerInfo : VideoType("预告·资讯", "trailer_info", 184, "/v/cinephile/trailer_info")
}

public object Documentary : VideoType("纪录片", "documentary", 177, "/documentary") {
    public object History : VideoType("人文·历史", "history", 37, "/v/documentary/history")
    public object Science : VideoType("科学·探索·自然", "science", 178, "/v/documentary/science")
    public object Military : VideoType("军事", "military", 179, "/v/documentary/military")
    public object Travel : VideoType("社会·美食·旅行", "travel", 180, "/v/documentary/travel")
}

public object Movie : VideoType("电影", "movie", 23, "/movie") {
    public object Chinese : VideoType("华语电影", "chinese", 147, "/v/movie/chinese")
    public object West : VideoType("欧美电影", "west", 145, "/v/movie/west")
    public object Japan : VideoType("日本电影", "japan", 146, "/v/movie/japan")
    public object Other : VideoType("其他国家", "movie", 83, "/v/movie/movie")
}

public object TV : VideoType("电视剧", "tv", 11, "/tv") {
    public object Mainland : VideoType("国产剧", "mainland", 185, "/v/tv/mainland")
    public object Overseas : VideoType("海外剧", "overseas", 187, "/v/tv/overseas")
}
