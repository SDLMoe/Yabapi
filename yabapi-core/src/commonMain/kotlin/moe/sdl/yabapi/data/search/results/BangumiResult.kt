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
    @SerialName("media_id") val mediaId: Int? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("org_title") val orgTitle: String? = null,
    @SerialName("media_type") val mediaType: Int? = null,
    @SerialName("cv") val cv: String? = null,
    @SerialName("staff") val staff: String? = null,
    @SerialName("season_id") val seasonId: Int? = null,
    @SerialName("is_avid") val isAvId: Boolean? = null,
    @SerialName("hit_columns") val hitColumns: List<String> = emptyList(),
    @SerialName("hit_epids") val hitEpids: String? = null,
    @SerialName("season_type") val seasonType: Int? = null,
    @SerialName("season_type_name") val seasonTypeName: String? = null,
    @SerialName("selection_style") val selectionStyle: String? = null,
    @SerialName("ep_size") val epSize: Int? = null,
    @SerialName("url") val url: String? = null,
    @SerialName("button_text") val buttonText: String? = null,
    @SerialName("is_follow") val isFollow: Boolean? = null,
    @SerialName("is_selection") val isSelection: Boolean? = null,
    @SerialName("eps") val eps: List<BangumiEpisode> = emptyList(),
    @SerialName("badges") val badges: List<ColorsInfo> = emptyList(),
    @SerialName("cover") val cover: String? = null,
    @SerialName("areas") val areas: String? = null,
    @SerialName("styles") val styles: String? = null,
    @SerialName("goto_url") val gotoUrl: String? = null,
    @SerialName("desc") val description: String? = null,
    @SerialName("pubtime") val releaseDate: String? = null,
    @SerialName("media_mode") val mediaMode: Int? = null,
    @SerialName("fix_pubtime_str") val fixReleaseTimeString: String? = null,
    @SerialName("media_score") val mediaScore: MediaScore? = null,
    @SerialName("display_info") val displayInfo: List<ColorsInfo> = emptyList(),
    @SerialName("pgc_season_id") val pgcSeasonId: Int? = null,
    @SerialName("corner") val corner: Int? = null,
) : SearchResult {
    @Serializable
    public data class BangumiEpisode(
        @SerialName("id") val id: Int? = null,
        @SerialName("cover") val cover: String? = null,
        @SerialName("title") val title: String? = null,
        @SerialName("url") val url: String? = null,
        @SerialName("release_date") val releaseDate: String? = null,
        @SerialName("badges") val badges: JsonElement? = null,
        @SerialName("index_title") val indexTitle: String? = null,
        @SerialName("long_title") val longTitle: String? = null,
    )

    @Serializable
    public data class ColorsInfo(
        @SerialName("text") val text: String? = null,
        @SerialName("text_color") val textColor: RgbColor? = null,
        @SerialName("text_color_night") val textColorNight: RgbColor? = null,
        @SerialName("bg_color") val bgColor: RgbColor? = null,
        @SerialName("bg_color_night") val bgColorNight: RgbColor? = null,
        @SerialName("border_color") val borderColor: RgbColor? = null,
        @SerialName("border_color_night") val borderColorNight: RgbColor? = null,
        @SerialName("bg_style") val bgStyle: Int? = null,
    )

    public companion object : ResultFactory() {
        override val code: String = "media_bangumi"
        override fun decode(json: Json, data: JsonObject): BangumiResult = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class MediaScore(
    @SerialName("score") val score: Double? = null,
    @SerialName("user_count") val userCount: Int? = null,
)
