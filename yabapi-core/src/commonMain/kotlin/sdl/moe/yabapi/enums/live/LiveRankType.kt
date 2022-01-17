package sdl.moe.yabapi.enums.live

import kotlinx.serialization.Serializable

@Serializable
public enum class LiveRankType(public val code: String) {
    LIVER_VITALITY("master_vitality_2018"),
    USER_ENERGY("user_energy_2018"),
    SAIL_VALUE("sail_boat_value"),
    SAIL_TICKET("sail_boat_ticket"),
    SAIL_NUMBER("sail_boat_number"),
    USER_LEVEL("user_level"),
    LIVER_LEVEL("master_level"),
}
