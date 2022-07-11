package moe.sdl.yabapi.data.comment

import moe.sdl.yabapi.util.dsl.DslSwitch2
import moe.sdl.yabapi.util.dsl.DslSwitch2Status
import moe.sdl.yabapi.util.encoding.av

/**
 * 评论请求的 DSL 封装
 *
 * `Type` 属性是必须配置的, 否则会抛出 [IllegalStateException]
 *
 * 用法示例：
 * ```kotlin
 * // this: CommentReqDsl
 * Type av 114514
 * Type bv "BV123122"
 * Page no 1 size 20 sort hot
 * ```
 */
@Suppress("PropertyName", "ClassName", "FunctionName")
public data class CommentReqDsl(
    internal var _type: CommentType? = null,
    internal var _id: ULong? = null,
    internal var _page: Int = 0,
    internal var _count: Int = 20,
    internal var _sort: CommentSort = CommentSort.MIXED,
    internal var _lazy: Boolean = true,
) {
    ///////////////////////////////////////////////////////////////////////////
    // 别名, 避免额外导包
    ///////////////////////////////////////////////////////////////////////////

    public inline val time: CommentSort
        get() = CommentSort.TIME
    public inline val mixed: CommentSort
        get() = CommentSort.MIXED
    public inline val hot: CommentSort
        get() = CommentSort.HOT
    public inline val on: DslSwitch2Status
        get() = DslSwitch2Status.FIRST
    public inline val off: DslSwitch2Status
        get() = DslSwitch2Status.SECOND

    ///////////////////////////////////////////////////////////////////////////
    // 对 DSL 接口的实现
    ///////////////////////////////////////////////////////////////////////////

    public val Lazy: DslSwitch2 = object : DslSwitch2() {
        override fun slot1() {
            _lazy = true
        }

        override fun slot2() {
            _lazy = false
        }
    }

    public val Type: _Type = object : _Type() {
        override fun av(id: Long) {
            _type = CommentType.VIDEO
            _id = id.toULong()
        }

        override fun av(id: Int) {
            _type = CommentType.VIDEO
            _id = id.toULong()
        }

        override fun av(id: String) {
            val type = CommentType.VIDEO
            val value = id.toULongOrNull()
                ?: id.split("av")[1].toULongOrNull()
                ?: error("cannot parse input av number $id, please check input")
            _type = type
            _id = value
        }

        override fun bv(string: String) {
            _type = CommentType.VIDEO
            _id = (if (!string.startsWith("bv", true)) "BV$string" else string).av.toULong()
        }

        override fun feed(id: ULong) {
            _type = CommentType.IMAGE_FEED
            _id = id
        }
    }

    public val Page: _Page = object : _Page {
        override fun no(page: Int): _Page {
            _page = page
            return this
        }

        override fun size(size: Int): _Page {
            _count = size
            return this
        }

        override fun sort(type: CommentSort): _Page {
            _sort = type
            return this
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // 方便 DSL 调用的 接口/抽象类 , 相当于 API
    ///////////////////////////////////////////////////////////////////////////

    public sealed interface ReqDsl

    public abstract class _Type : ReqDsl {
        public infix fun video(av: Long): Unit = av(av)
        public infix fun video(av: Int): Unit = av(av)
        public infix fun video(bv: String): Unit = bv(bv)
        public abstract infix fun av(id: Long)
        public abstract infix fun av(id: Int)
        public abstract infix fun av(id: String)
        public abstract infix fun bv(string: String)
        public abstract infix fun feed(id: ULong)
    }

    public interface _Page : ReqDsl {
        public infix fun no(page: Int): _Page
        public infix fun size(size: Int): _Page
        public infix fun sort(type: CommentSort): _Page
    }
}
