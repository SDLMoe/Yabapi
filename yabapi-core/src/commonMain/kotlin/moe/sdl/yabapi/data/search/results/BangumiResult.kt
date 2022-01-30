@file:UseSerializers(BooleanJsSerializer::class, RgbColorStringSerializer::class)

package moe.sdl.yabapi.data.search.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import moe.sdl.yabapi.data.RgbColor
import moe.sdl.yabapi.serializer.BooleanJsSerializer
import moe.sdl.yabapi.serializer.data.RgbColorStringSerializer

@Serializable
public data class BangumiResult(
    @SerialName("type") val type: String,
    @SerialName("media_id") val mediaId: Int,
    @SerialName("title") val title: String,
    @SerialName("org_title") val orgTitle: String,
    @SerialName("media_type") val mediaType: Int,
    @SerialName("cv") val cv: String,
    @SerialName("staff") val staff: String,
    @SerialName("season_id") val seasonId: Int,
    @SerialName("is_avid") val isAvId: Boolean,
    @SerialName("hit_columns") val hitColumns: List<String> = emptyList(),
    @SerialName("hit_epids") val hitEpids: String,
    @SerialName("season_type") val seasonType: Int,
    @SerialName("season_type_name") val seasonTypeName: String,
    @SerialName("selection_style") val selectionStyle: String,
    @SerialName("ep_size") val epSize: Int,
    @SerialName("url") val url: String,
    @SerialName("button_text") val buttonText: String,
    @SerialName("is_follow") val isFollow: Boolean,
    @SerialName("is_selection") val isSelection: Boolean,
    @SerialName("eps") val eps: List<BangumiEpisode>,
    @SerialName("badges") val badges: List<ColorsInfo>,
    @SerialName("cover") val cover: String,
    @SerialName("areas") val areas: String,
    @SerialName("styles") val styles: String,
    @SerialName("goto_url") val gotoUrl: String,
    @SerialName("desc") val description: String,
    @SerialName("pubtime") val releaseDate: String,
    @SerialName("media_mode") val mediaMode: Int,
    @SerialName("fix_pubtime_str") val fixReleaseTimeString: String,
    @SerialName("media_score") val mediaScore: MediaScore,
    @SerialName("display_info") val displayInfo: List<ColorsInfo>,
    @SerialName("pgc_season_id") val pgcSeasonId: Int,
    @SerialName("corner") val corner: Int,
) : SearchResult {
    @Serializable
    public data class BangumiEpisode(
        @SerialName("id") val id: Int,
        @SerialName("cover") val cover: String,
        @SerialName("title") val title: String,
        @SerialName("url") val url: String,
        @SerialName("release_date") val releaseDate: String,
        @SerialName("badges") val badges: JsonElement? = null,
        @SerialName("index_title") val indexTitle: String,
        @SerialName("long_title") val longTitle: String,
    )

    @Serializable
    public data class ColorsInfo(
        @SerialName("text") val text: String,
        @SerialName("text_color") val textColor: RgbColor,
        @SerialName("text_color_night") val textColorNight: RgbColor,
        @SerialName("bg_color") val bgColor: RgbColor,
        @SerialName("bg_color_night") val bgColorNight: RgbColor,
        @SerialName("border_color") val borderColor: RgbColor,
        @SerialName("border_color_night") val borderColorNight: RgbColor,
        @SerialName("bg_style") val bgStyle: Int,
    )

    public companion object : ResultFactory() {
        override val code: String = "media_bangumi"
        override fun decode(json: Json, data: JsonObject): BangumiResult = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class MediaScore(
    @SerialName("score") val score: Double,
    @SerialName("user_count") val userCount: Int,
)
