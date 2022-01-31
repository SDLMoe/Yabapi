package moe.sdl.yabapi.data.space

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public sealed class BaseCollectionItem {
    @SerialName("id") public abstract val id: Int?
    @SerialName("fid") public abstract val fid: Int?
    @SerialName("mid") public abstract val mid: Int?
    @SerialName("attr") public abstract val attribute: Int?
    @SerialName("title") public abstract val title: String?
    @SerialName("fav_state") public abstract val favState: Int?
    @SerialName("media_count") public abstract val count: Int?
}

@Serializable
public data class CollectionItem(
    @SerialName("id") public override val id: Int? = null,
    @SerialName("fid") public override val fid: Int? = null,
    @SerialName("mid") public override val mid: Int? = null,
    @SerialName("attr") public override val attribute: Int? = null,
    @SerialName("title") public override val title: String? = null,
    @SerialName("fav_state") public override val favState: Int? = null,
    @SerialName("media_count") public override val count: Int? = null,
): BaseCollectionItem()

@Serializable
public data class FavCollectionItem(
    @SerialName("id") public override val id: Int? = null,
    @SerialName("fid") public override val fid: Int? = null,
    @SerialName("mid") public override val mid: Int? = null,
    @SerialName("attr") public override val attribute: Int? = null,
    @SerialName("title") public override val title: String? = null,
    @SerialName("fav_state") public override val favState: Int? = null,
    @SerialName("media_count") public override val count: Int? = null,
    @SerialName("cover") val cover: String? = null,
    @SerialName("upper") val upper: CollectionOwner? = null,
    @SerialName("cover_type") val coverType: Int? = null,
    @SerialName("intro") val intro: String? = null,
    @SerialName("ctime") val createdTime: Long? = null,
    @SerialName("mtime") val modifiedTime: Long? = null,
    @SerialName("state") val state: Int? = null,
    @SerialName("view_count") val viewCount: Int? = null,
    @SerialName("type") val type: Int? = null,
    @SerialName("link") val link: String? = null,
): BaseCollectionItem()

@Serializable
public data class CollectionOwner(
    @SerialName("mid") val mid: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("face") val face: String? = null,
)
