package moe.sdl.yabapi.data.search.results

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

/**
 * PgcResult is the same as [BangumiResult]
 */
public object PgcResultFactory : ResultFactory() {
    override val code: String = "media_ft"
    override fun decode(json: Json, data: JsonObject): BangumiResult = json.decodeFromJsonElement(data)
}
