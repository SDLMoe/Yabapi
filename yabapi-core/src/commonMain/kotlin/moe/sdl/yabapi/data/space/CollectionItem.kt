package moe.sdl.yabapi.data.space

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public sealed class BaseCollectionItem {
    @SerialName("id") public abstract val id: Int
    @SerialName("fid") public abstract val fid: Int
    @SerialName("mid") public abstract val mid: Int
    @SerialName("attr") public abstract val attribute: Int
    @SerialName("title") public abstract val title: String
    @SerialName("fav_state") public abstract val favState: Int
    @SerialName("media_count") public abstract val count: Int
}

@Serializable
public data class CollectionItem(
    @SerialName("id") public override val id: Int,
    @SerialName("fid") public override val fid: Int,
    @SerialName("mid") public override val mid: Int,
    @SerialName("attr") public override val attribute: Int,
    @SerialName("title") public override val title: String,
    @SerialName("fav_state") public override val favState: Int,
    @SerialName("media_count") public override val count: Int,
): BaseCollectionItem()

@Serializable
public data class FavCollectionItem(
    @SerialName("id") public override val id: Int,
    @SerialName("fid") public override val fid: Int,
    @SerialName("mid") public override val mid: Int,
    @SerialName("attr") public override val attribute: Int,
    @SerialName("title") public override val title: String,
    @SerialName("fav_state") public override val favState: Int,
    @SerialName("media_count") public override val count: Int,
    @SerialName("cover") val cover: String,
    @SerialName("upper") val upper: CollectionOwner,
    @SerialName("cover_type") val coverType: Int,
    @SerialName("intro") val intro: String,
    @SerialName("ctime") val createdTime: Long,
    @SerialName("mtime") val modifiedTime: Long,
    @SerialName("state") val state: Int,
    @SerialName("view_count") val viewCount: Int,
    @SerialName("type") val type: Int,
    @SerialName("link") val link: String,
): BaseCollectionItem()

@Serializable
public data class CollectionOwner(
    @SerialName("mid") val mid: Int,
    @SerialName("name") val name: String,
    @SerialName("face") val face: String,
)
