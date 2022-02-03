@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.stream

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class PgcStreamResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("result") val data: PgcStreamResult? = null,
)

@Serializable
public data class PgcStreamResult(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("is_preview") val isPreview: Boolean? = null,
    @SerialName("fnval") val fnval: Int? = null,
    @SerialName("video_project") val videoProject: Boolean? = null,
    @SerialName("fnver") val fnver: Int? = null,
    @SerialName("type") val type: String? = null,
    @SerialName("bp") val bp: Int? = null,
    @SerialName("no_rexcode") val noRexcode: Boolean? = null,
    @SerialName("has_paid") val hasPaid: Boolean? = null,
    @SerialName("clip_info_list") val clipInfoList: JsonArray? = null,
    @SerialName("status") val status: Int? = null,
    @SerialName("from") override val from: String? = null,
    @SerialName("result") override val result: String? = null,
    @SerialName("message") override val message: String? = null,
    @SerialName("quality") override val quality: QnQuality? = null,
    @SerialName("format") override val format: String? = null,
    @SerialName("timelength") override val length: Long? = null, // ms
    @SerialName("accept_format") override val acceptFormat: String? = null,
    @SerialName("accept_description") override val acceptDescription: List<String> = emptyList(),
    @SerialName("accept_quality") override val rawAcceptQuality: List<String> = emptyList(),
    @SerialName("video_codecid") override val videoCodecId: CodecId? = null,
    @SerialName("seek_param") override val seekParam: String? = null,
    @SerialName("seek_type") override val seekType: String? = null,
    @SerialName("durl") override val urls: List<StreamUrl> = emptyList(),
    @SerialName("dash") override val dash: DashStream? = null,
    @SerialName("support_formats") override val supportFormats: List<SupportFormat> = emptyList(),
    @SerialName("high_format") override val highFormat: JsonElement? = null,
): AbstractStreamData()
