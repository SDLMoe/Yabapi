package sdl.moe.yabapi.enums

public enum class StickerBusiness {
    REPLY {
        override fun toString(): String = "reply"
    },

    DYNAMIC {
        override fun toString(): String = "dynamic"
    };
}
