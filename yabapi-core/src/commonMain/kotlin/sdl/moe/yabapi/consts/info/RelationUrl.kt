// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.consts.info

import sdl.moe.yabapi.consts.MAIN

internal const val FANS_GET_URL = "$MAIN/x/relation/followers"

internal const val FOLLOWING_GET_URL = "$MAIN/x/relation/followings"

internal const val FOLLOWING_SEARCH_URL = "$MAIN/x/relation/followings/search"

internal const val CO_FOLLOWING_GET_URL = "$MAIN/x/relation/same/followings"

internal const val QUIETLY_FOLLOWING_GET_URL = "$MAIN/x/relation/whispers"

internal const val BLACKLIST_GET_URL = "$MAIN/x/relation/blacks"

internal const val MODIFY_RELATION_URL = "$MAIN/x/relation/modify"

internal const val BATCH_MODIFY_RELATION_URL = "$MAIN/x/relation/batch/modify"

internal const val RELATION_QUERY_URL = "$MAIN/x/relation"

internal const val RELATION_BATCH_QUERY_URL = "$MAIN/x/relation/relations"

internal const val RELATION_QUERY_MUTUALLY = "$MAIN/x/space/acc/relation"

internal const val RELATION_QUERY_SPECIAL = "$MAIN/x/relation/tag/special"
