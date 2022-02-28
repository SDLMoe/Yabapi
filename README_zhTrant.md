<img src="imgs/yabapi-banner.png" alt="yabapi logo">

[![License: CC0](https://img.shields.io/badge/License-CC0-lightgrey?style=for-the-badge)](https://creativecommons.org/publicdomain/zero/1.0/legalcode)
[![Maven-Central Yabapi Version](https://img.shields.io/maven-central/v/moe.sdl.yabapi/yabapi-core-jvm?style=for-the-badge)](https://repo1.maven.org/maven2/moe/sdl/yabapi/)
[![Maven-Central Yabapi Snapshots Version](https://img.shields.io/nexus/s/moe.sdl.yabapi/yabapi-core-jvm?label=Maven%20Snapshots&server=https%3A%2F%2Fs01.oss.sonatype.org&style=for-the-badge)](https://s01.oss.sonatype.org/content/repositories/snapshots/moe/sdl/yabapi/yabapi-core-jvm/)

> 🔧 WIP 並沒有進入穩定版本, API 可能會發生變動，[简体中文](README.md)

# Intro

簡而言之，Yabapi 是一個 B 站第三方 Kotlin API 庫。

# License

本項目代碼基於 [CC0](https://github.com/SDLMoe/Yabapi/blob/kotlin-mt/LICENSE) 分發, 釋放於公有領域, 如果你樂意, 可以在使用時附上我的名字(這並不強製).

[本項目 Logo](./imgs/yabapi-logo.svg) 基於 [CC BY ND 4.0](https://creativecommons.org/licenses/by-nd/4.0/) 分發, 若需要使用 Logo,
務必附上本項目地址, 並且不得更改其內容. Banner 使用了[思源等寬](https://github.com/adobe-fonts/source-han-mono).

# 特性

## 多平臺

本項目是 Kotlin Multiplatform 項目，現在支持 JVM 和 Native 兩大平臺，絕大多數功能都提供多平臺支持。

## 高性能

基於 [Kotlin 協程](https://github.com/Kotlin/kotlinx.coroutines) 的支持，開發者可以輕松寫出高性能的代碼。

## 類型安全

Kotlin 為開發者提供了巨大幫助，包括在語言層面上對空指針的避免，以及完備的序列化庫 [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization).

Yabapi 基於此，力求類型安全，避免空指針，減少運行時錯誤。

# 支持的 API

<details>
<summary>查看詳情</summary>

- 登錄
    - [X] 圖形驗證碼獲取 (驗證需通過 [geetest-validator](https://github.com/kuresaru/geetest-validator))
    - [X] Web 登錄
        - [X] Cookie
        - [X] 密碼登錄
            - [ ] Native 平臺的 RSA 支持
        - [X] 掃碼登錄
        - [X] 短信登錄
    - [ ] APP / TV?
- 關系
    - [X] 查詢粉絲
    - [X] 查詢關註
    - [X] 查詢特別關註
    - [X] 批量查詢
    - [X] 關註/悄悄關註/拉黑 及取消操作
    - [X] 批量操作 (僅關註/拉黑)
    - [X] 查詢關系
- 信息獲取
    - [X] 個人基本信息
    - [X] 硬幣狀態及花費歷史
    - [X] 每日經驗值獎勵獲取
    - [X] 大會員狀態
    - [X] 實名狀態
    - [X] 昵稱是否可用
    - [X] 用戶空間
        - [X] 置頂 / 代表作
        - [X] 近期遊戲
        - [X] 近期投幣
        - [X] 空間公告
        - [X] 空間設置
        - [X] Tags
        - [X] 收藏夾獲取
          - [X] 創建的
          - [X] 收藏的
        - [X] 稍後觀看
          - [X] 查看 / 增加 / 刪除 / 清除
        - [X] 空間頻道 (視頻合集)
          - [X] 信息獲取
        - [X] 訂閱番劇
        - [X] 訂閱標簽
- 搜索
  - [X] 綜合搜索
  - [X] 分類搜索
    - [X] 視頻
    - [X] 番劇 及 劇集
    - [X] 用戶
    - [X] 直播間 及 直播用戶
    - [X] 話題
    - [X] 專欄
    - [X] 相關篩選
- 視頻
    - [X] 基本信息
    - [X] 在線人數
    - [X] 高能進度條
    - [X] Tags
    - [X] 分P
    - [X] 所屬合集
    - [X] 點贊/投幣/收藏/一鍵三連 及 狀態查詢
    - [X] 全清晰度(8K/4K/1080P+) 音視頻流獲取
        - [ ] 下載?
- 專欄
  - [X] 基本信息
  - [X] 文集信息
- 動態
    - [X] 獲取 新動態 / 特定用戶
    - [X] 動態解析
        - [X] 文本
        - [X] 圖文
        - [X] 視頻
        - [X] 番劇
        - [X] 專欄
        - [X] 合集
        - [X] 分享
        - [ ] ...
- 相簿
    - [X] 上傳圖片

  > 介於 [相簿專站](https://h.bilibili.com) 基本廢棄, 不計劃添加額外 API

- 專欄
    - [X] 基本信息
    - [X] 詳細信息
    - [X] 文集信息
- 直播
    - [X] 獲取信息
    - [X] 簽到
        - [X] 檢查簽到信息(本月/上月)
    - [X] 直播排行榜
        - [X] 主播元氣榜
        - [X] 用戶能量榜
        - [X] 主播艦隊榜
        - [X] 船員價值榜
        - [X] 艦船人數榜
        - [X] 用戶等級榜
        - [X] 主播等級榜
        - [X] 勛章等級榜
    - [X] 建立 WebSocket 消息流連接
        - [X] 發送 認證包 & 心跳包
        - [X] 接收 認證回應 & 心跳回應
        - [X] 接收 普通包 & 解析
            - [X] 禮物連發
            - [X] 彈幕信息
            - [X] 艦長特效
            - [X] 上艦信息
            - [X] 高能榜變化 V1, V2
            - [X] 高能榜TOP3 變化
            - [X] 登上熱門 V1, V2
            - [X] 房間信息變化(標題更改)
            - [X] 房間實時信息(粉絲, 粉絲團)
            - [X] 交互信息
            - [X] 交互遊戲信息
            - [X] 直播活動頁面信息
            - [X] 廣播信息
            - [X] SuperChat 進場/發送/刪除
                - [X] 日語樣式
            - [X] 續費/開通艦長提示
            - [X] 活動 banner 顯示
            - [X] 抽獎 開始/結束/審核/獲獎
    - [X] 直播視頻流 全分辨率獲取
        - [ ] 下載?
- 表情
    - [X] 獲取表情列表
- 時間
    - [X] 獲取服務器時間戳
- Cookie 存儲
    - [X] 提供 FileCookieStorage

</details>

敏感 API 不會被支持, 如 B 幣錢包、註冊、新人答題、課程下載。同時 API 會優先支持讀取操作。

# Setup

```kotlin
// Common
implementation("moe.sdl.yabapi:yabapi-core:$version")
// JVM
implementation("moe.sdl.yabapi:yabapi-core-jvm:$version")
// Native
implementation("moe.sdl.yabapi:yabapi-core-native:$version")
```

<details>

**<summary>Snapshots</summary>**

```kotlin
repositories {
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    implementation("moe.sdl.yabapi:yabapi-core-$platform:$snapshotVersion")
}
```

</details>

# Change Log

See: [CHANGELOG.md](CHANGELOG.md)

# Thanks

- [Kotlin](https://github.com/JetBrains/kotlin) - A modern programming language that makes developers happier.
- [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) - Kotlin multiplatform / multi-format
  **reflectionless** serialization
- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) - A rich library for coroutines developed by
  JetBrains
- [Ktor](https://github.com/ktorio/ktor) - An asynchronous framework for creating microservices, web applications and
  more.
- [Kermit](https://github.com/touchlab/Kermit) - Multiplatform logger library
- [Korlibs](https://docs.korge.org/) - Korlibs is a set of Kotlin Common modern libraries to do full stack development
- [bilibili-API-collect](https://github.com/SocialSisterYi/bilibili-API-collect) - 嗶哩嗶哩-API收集整理
