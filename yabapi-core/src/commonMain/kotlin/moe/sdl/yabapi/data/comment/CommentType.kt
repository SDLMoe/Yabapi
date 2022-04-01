package moe.sdl.yabapi.data.comment

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
public enum class CommentType(public val code: Int) {
    UNKNOWN(-1),
    VIDEO(1), // 对应 av 号
    TOPIC(2), // 对应话题 id
    ACTIVITY(4), // 对应活动 id
    SHORT(5), // 对应小视频 id
    BLACK_ROOM(6), // 对应封禁公示 id
    ANNOUNCEMENT(7), // 对应公告 id
    LIVE_ACTIVITY(8), // 对应直播间号
    IMAGE_FEED(11), // 对应图片动态 id
    ARTICLE(12), // 对应专栏 cvid
    AUDIO(14), // 对应音频 id
    COMMITTEE(15), // 风纪委员会, 对应仲裁项目 id
    TEXT_FEED(17), // 纯文字动态和分享
    MANGA(22), // 对应漫画 mcid
    LESSON(33), // 对应课程 epid
    ;

    public companion object : KSerializer<CommentType> {
        private val map = enumValues<CommentType>().associateBy { it.code }
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("CommentType", PrimitiveKind.INT)
        override fun deserialize(decoder: Decoder): CommentType = map[decoder.decodeInt()] ?: UNKNOWN
        override fun serialize(encoder: Encoder, value: CommentType): Unit = encoder.encodeInt(value.code)
    }
}
