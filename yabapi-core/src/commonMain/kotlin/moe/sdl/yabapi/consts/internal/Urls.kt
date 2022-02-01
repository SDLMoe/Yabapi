@file:Suppress("unused")

package moe.sdl.yabapi.consts.internal

// region =================== Base ========================

internal const val WWW: String = "https://www.bilibili.com"

/** 主站 */
internal const val MAIN: String = "https://api.bilibili.com"

/** 手机端API */
internal const val APP: String = "https://app.bilibili.com"

/** SPACE */
internal const val SPACE: String = "https://space.bilibili.com"

/** 消息中心 */
internal const val MESSAGE: String = "https://message.bilibili.com"

/** 直播中心 */
internal const val LIVE: String = "https://api.live.bilibili.com"

/** 直播 API */
internal const val LIVE_API: String = "https://api.live.bilibili.com"

/** 创作中心 */
internal const val MEMBER: String = "https://member.bilibili.com"

/** 鉴权 */
internal const val PASSPORT: String = "https://passport.bilibili.com"

internal const val ACCOUNT: String = "https://account.bilibili.com"

internal const val VC: String = "https://vc.bilibili.com"

internal const val VC_API: String = "https://api.vc.bilibili.com"

internal const val INTERFACE = "https://interface.bilibili.com"

internal const val FEED_DOMAIN = "https://t.bilibili.com"

// endregion

// region =================== Bangumi ========================

internal const val BANGUMI_INFO_GET_URL = "$MAIN/pgc/review/user"

internal const val BANGUMI_DETAILED_GET_URL = "$MAIN/pgc/view/web/season"

// endregion

// region =================== Video ========================

internal const val VIDEO_INFO_GET_URL = "$MAIN/x/web-interface/view"

internal const val VIDEO_PARTS_GET_URL = "$MAIN/x/player/pagelist"

internal const val VIDEO_DESCRIPTION_GET_URL = "$MAIN/x/web-interface/archive/desc"

internal const val VIDEO_LIKE_URL = "$MAIN/x/web-interface/archive/like"

internal const val VIDEO_HAS_LIKE_URL = "$MAIN/x/web-interface/archive/has/like"

internal const val VIDEO_COIN_URL = "$MAIN/x/web-interface/coin/add"

internal const val VIDEO_COIN_CHECK_URL = "$MAIN/x/web-interface/archive/coins"

// internal const val VIDEO_COLLECT_ACTION_URL = "$MAIN/x/v3/fav/resource/deal"

internal const val VIDEO_COLLECT_ACTION_URL = "$MAIN/medialist/gateway/coll/resource/deal"

internal const val VIDEO_COLLECT_CHECK_URL = "$MAIN/x/v2/fav/video/favoured"

internal const val VIDEO_COMBO_LIKE_URL = "$MAIN/x/web-interface/archive/like/triple"

internal const val VIDEO_SHARE_URL = "$MAIN/x/web-interface/share/add"

internal const val VIDEO_TIMELINE_HOT_URL = "https://bvc.bilivideo.com/pbp/data"

internal const val VIDEO_ONLINE_GET_URL = "$MAIN/x/player/online/total"

internal const val VIDEO_TAG_GET_URL = "$MAIN/x/tag/archive/tags"

internal const val VIDEO_RELATED_GET_URL = "$MAIN/x/web-interface/archive/related"

internal const val VIDEO_DANMAKU_WEB_URL = "$MAIN/x/v2/dm/web/seg.so"

internal const val VIDEO_DANMAKU_CALENDAR_URL = "$MAIN/x/v2/dm/history/index"

internal const val VIDEO_HISTORY_DANMAKU_GET_URL = "$MAIN/x/v2/dm/web/history/seg.so"

internal const val VIDEO_STREAM_FETCH_URL = "$MAIN/x/player/playurl"

internal const val VIDEO_REPORT_PROGRESS_URL = "$MAIN/x/v2/history/report"

// endregion

// region =================== Ranking ========================

internal const val RANKING_GET_URL = "$MAIN/x/web-interface/ranking/region"

internal const val LATEST_VIDEO_GET_URL = "$MAIN/x/web-interface/dynamic/region"

// endregion

// region =================== Time ========================

internal const val GET_TIMESTAMP_URL = "$MAIN/x/report/click/now"

// endregion

// region =================== Sticker ========================

internal const val GET_MY_STICKERS_LIST = "$MAIN/x/emote/user/panel/web"

internal const val GET_STICKERS_BY_ID_URL = "$MAIN/x/emote/package"

internal const val GET_ALL_STICKERS_URL = "$MAIN/x/emote/setting/panel"

// endregion

// region =================== Info ========================

internal const val BASIC_INFO_GET_URL = "$MAIN/x/web-interface/nav"

internal const val STAT_GET_URL = "$MAIN/x/web-interface/nav/stat"

internal const val COIN_GET_URL = "$ACCOUNT/site/getCoin"

internal const val USER_SPACE_GET_URL = "$MAIN/x/space/acc/info"

internal const val PINNED_VIDEO_GET_URL = "$MAIN/x/space/top/arc"

internal const val MASTERPIECE_VIDEO_GET_URL = "$MAIN/x/space/masterpiece"

internal const val USER_TAGS_GET_URL = "$MAIN/x/space/acc/tags"

internal const val USER_SPACE_ANNOUNCEMENT_GET_URL = "$MAIN/x/space/notice"

internal const val USER_SPACE_SETTING_GET_URL = "$SPACE/ajax/settings/getSettings"

internal const val USER_RECENT_PLAYED_GAME_GET_URL = "$MAIN/x/space/lastplaygame"

internal const val USER_RECENT_COINED_VIDEO_GET_URL = "$MAIN/x/space/coin/video"

internal const val SPACE_VIDEO_GET_URL = "$MAIN/x/space/arc/search"

internal const val SPACE_ALBUM_INDEX_URL = "$MAIN/x/space/album/index"

internal const val SPACE_ALBUM_LIST_URL = "$VC_API/link_draw/v1/doc/doc_list"

internal const val SPACE_CHANNEL_LIST_URL = "$MAIN/x/space/channel/list"

internal const val SPACE_CHANNEL_ARCHIVES_URL = "$MAIN/x/space/channel/video"

internal const val SPACE_COLLECTION_LIST_GET_URL = "$MAIN/x/v3/fav/folder/created/list-all"

internal const val SPACE_FAV_COLLECTION_LIST_GET_URL = "$MAIN/x/v3/fav/folder/collected/list"

internal const val SPACE_SUB_GET_URL = "$MAIN/x/space/bangumi/follow/list"

internal const val SPACE_SUB_TAGS_GET_URL = "$SPACE/ajax/tags/getSubList"

internal const val USER_CARD_GET_URL = "$MAIN/x/web-interface/card"

internal const val MY_SPACE_GET_URL = "$MAIN/x/space/myinfo"

internal const val NICK_CHECK_URL = "$PASSPORT/web/generic/check/nickname"

internal const val FANS_GET_URL = "$MAIN/x/relation/followers"

// region ## =================== Relation ========================

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

// endregion

// region ## =================== Centre ========================

internal const val ACCOUNT_INFO_GET_URL: String = "$MAIN/x/member/web/account"

internal const val EXP_REWARD_GET_URL: String = "$MAIN/x/member/web/exp/reward"

internal const val COIN_EXP_GET_URL: String = "$WWW/plus/account/exp.php"

internal const val VIP_STAT_GET_URL: String = "$MAIN/x/vip/web/user/info"

internal const val SECURE_INFO_GET_URL: String = "$PASSPORT/web/site/user/info"

internal const val REAL_NAME_INFO_GET_URL: String = "$MAIN/x/member/realname/status"

internal const val REAL_NAME_DETAILED_GET_URL: String = "$MAIN/x/member/realname/apply/status"

internal const val COIN_LOG_GET_URL: String = "$MAIN/x/member/web/coin/log"

internal const val CHANGE_BIO_URL: String = "$MAIN/x/member/web/sign/update"

// endregion

// endregion

// region =================== Album ========================

internal const val ALBUM_INFO_GET_URL = "$VC_API/link_draw/v1/doc/detail"

internal const val ALBUM_UPLOAD_URL = "$VC_API/api/v1/drawImage/upload"

// endregion

// region =================== Feed ========================

internal const val FEED_CONTENT_GET_URL = "$VC_API/dynamic_svr/v1/dynamic_svr/get_dynamic_detail"

internal const val FEED_NEW_GET_URL = "$VC_API/dynamic_svr/v1/dynamic_svr/dynamic_new" // ?uid=$uid&type_list=$list

internal const val FEED_HISTORY_GET_URL = "$VC_API/dynamic_svr/v1/dynamic_svr/dynamic_history"

internal const val FEED_SPACE_GET_URL = "$VC_API/dynamic_svr/v1/dynamic_svr/space_history"

internal const val FEED_LIVING_GET_URL = "$LIVE_API/xlive/web-ucenter/v1/xfetter/FeedList"

internal const val FEED_UPDATED_GET_URL = "$VC_API/dynamic_svr/v1/dynamic_svr/w_dyn_uplist"

// endregion

// region =================== Live ========================

internal const val LIVE_INIT_INFO_GET_URL = "$LIVE/room/v1/Room/room_init"

internal const val LIVE_AREA_URL = "$LIVE/room/v1/Area/getList"

internal const val LIVE_UID_TO_ROOM_ID = "$LIVE/room/v2/Room/room_id_by_uid"

internal const val LIVER_INFO_GET_URL = "$LIVE/live_user/v1/Master/info"

internal const val LIVE_SHOW_LIST_GET = "$LIVE/room/v1/Index/getShowList"

internal const val LIVE_CHECK_PWD_URL = "$LIVE/room/v1/Room/verify_room_pwd"

internal const val LIVE_HOVER_GET_URL = "$LIVE/room/v2/Index/getHoverInfo"

internal const val LIVE_DANMAKU_INFO_URL = "$LIVE/xlive/web-room/v1/index/getDanmuInfo"

internal const val LIVE_STREAM_FETCH_URL = "$LIVE/xlive/web-room/v2/index/getRoomPlayInfo"

internal const val LIVE_SIGN_URL = "$LIVE/xlive/web-ucenter/v1/sign/DoSign"

internal const val LIVE_SIGN_INFO_URL = "$LIVE/xlive/web-ucenter/v1/sign/WebGetSignInfo"

internal const val LIVE_SIGN_LAST_MONTH_URL = "$LIVE/sign/getLastMonthSignDays"

internal const val LIVE_RANKING_GET_URL = "$LIVE/rankdb/v1/Rank2018/getWebTop"

internal const val LIVE_MEDAL_RANK_GET_URL = "$LIVE/xlive/general-interface/v1/Rank/GetTotalMedalLevelRank"

// endregion

// region =================== Passport ========================

internal const val QUERY_CAPTCHA_URL: String = "$PASSPORT/x/passport-login/captcha"

/** params("act","getKey") */
internal const val RSA_GET_WEB_URL: String = "$PASSPORT/login"

internal const val RSA_GET_APP_URL: String = "$PASSPORT/api/oauth2/getKey"

internal const val LOGIN_WEB_URL: String = "$PASSPORT/x/passport-login/web/login"

internal const val LOGIN_QRCODE_GET_WEB_URL: String = "$PASSPORT/qrcode/getLoginUrl"

internal const val LOGIN_WEB_QRCODE_URL = "$PASSPORT/qrcode/getLoginInfo"

internal const val GET_CALLING_CODE_URL: String = "$PASSPORT/web/generic/country/list"

internal const val SEND_SMS_URL: String = "$PASSPORT/x/passport-login/web/sms/send"

internal const val LOGIN_WEB_SMS_URL: String = "$PASSPORT/x/passport-login/web/login/sms"

internal const val LOG_OUT_URL = "$PASSPORT/login/exit/v2"

// endregion

// region =================== Message ========================

internal const val UNREAD_MESSAGE_COUNT_GET_URL = "$MAIN/x/msgfeed/unread"

internal const val UNREAD_WHISPER_COUNT_GET_URL = "$VC_API/session_svr/v1/session_svr/single_unread"

internal const val SEND_MESSAGE_URL = "$VC_API/web_im/v1/web_im/send_msg"

// endregion

// region =================== Article ========================

internal const val ARTICLE_PAGE_URL = "$WWW/read"

internal const val ARTICLE_BASIC_INFO_GET_URL = "$MAIN/x/article/viewinfo"

internal const val ARTICLE_COLLECTION_INFO_GET_URL = "$MAIN/x/article/list/web/articles"

// endregion

// region =================== Info ========================

internal const val HISTORY_GET_URL = "$MAIN/x/web-interface/history/cursor"

internal const val HISTORY_DELETE_URL = "$MAIN/x/v2/history/delete"

internal const val HISTORY_CLEAR_URL = "$MAIN/x/v2/history/clear"

internal const val HISTORY_STOP_URL = "$MAIN/x/v2/history/shadow/set"

internal const val HISTORY_STATUS_QUERY_URL = "$MAIN/x/v2/history/shadow"

internal const val LATER_WATCH_ADD_URL = "$MAIN/x/v2/history/toview/add"

internal const val LATER_WATCH_ADD_CHANNEL_URL = "$SPACE/ajax/channel/addAllToView"

internal const val LATER_WATCH_GET_URL = "$MAIN/x/v2/history/toview"

internal const val LATER_WATCH_DELETE_URL = "$MAIN/x/v2/history/toview/del"

internal const val LATER_WATCH_CLEAR_URL = "$MAIN/x/v2/history/toview/clear"

// endregion

// region =================== Search ========================

internal const val SEARCH_ALL_URL = "$MAIN/x/web-interface/search/all/v2"

internal const val SEARCH_BY_TYPE_URL = "$MAIN/x/web-interface/search/type"

// endregion
