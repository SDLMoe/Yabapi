<img src="imgs/yabapi-banner.png" alt="yabapi logo">

[![License: CC0](https://img.shields.io/badge/License-CC0-lightgrey?style=for-the-badge)](https://creativecommons.org/publicdomain/zero/1.0/legalcode)
[![Maven-Central Yabapi Version](https://img.shields.io/maven-central/v/moe.sdl.yabapi/yabapi-core-jvm?style=for-the-badge)](https://repo1.maven.org/maven2/moe/sdl/yabapi/)
[![Maven-Central Yabapi Snapshots Version](https://img.shields.io/nexus/s/moe.sdl.yabapi/yabapi-core-jvm?label=Maven%20Snapshots&server=https%3A%2F%2Fs01.oss.sonatype.org&style=for-the-badge)](https://s01.oss.sonatype.org/content/repositories/snapshots/moe/sdl/yabapi/yabapi-core-jvm/)

> 🔧 WIP 并没有进入稳定版本, API 可能会发生变动

# Intro

简而言之，Yabapi 是一个 B 站第三方 Kotlin API 库。

# License

本项目代码基于 [CC0](https://github.com/SDLMoe/Yabapi/blob/kotlin-mt/LICENSE) 分发, 释放于公有领域, 如果你乐意, 可以在使用时附上我的名字(这并不强制).

[本项目 Logo](./imgs/yabapi-logo.svg) 基于 [CC BY ND 4.0](https://creativecommons.org/licenses/by-nd/4.0/) 分发, 若需要使用 Logo,
务必附上本项目地址, 并且不得更改其内容. Banner 使用了[思源等宽](https://github.com/adobe-fonts/source-han-mono).

# 特性

## 多平台

本项目是 Kotlin Multiplatform 项目，现在支持 JVM 和 Native 两大平台，绝大多数功能都提供多平台支持。

## 高性能

基于 [Kotlin 协程](https://github.com/Kotlin/kotlinx.coroutines) 的支持，开发者可以轻松写出高性能的代码。

## 类型安全

Kotlin 为开发者提供了巨大帮助，包括在语言层面上对空指针的避免，以及完备的序列化库 [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization).

Yabapi 基于此，力求类型安全，避免空指针，减少运行时错误。

# 支持的 API

<details>
<summary>查看详情</summary>

- 登录
    - [X] 图形验证码获取 (验证需通过 [geetest-validator](https://github.com/kuresaru/geetest-validator))
    - [X] Web 登录
        - [X] Cookie
        - [X] 密码登录
            - [ ] Native 平台的 RSA 支持
        - [X] 扫码登录
        - [X] 短信登录
    - [ ] APP / TV?
- 关系
    - [X] 查询粉丝
    - [X] 查询关注
    - [X] 查询特别关注
    - [X] 批量查询
    - [X] 关注/悄悄关注/拉黑 及取消操作
    - [X] 批量操作 (仅关注/拉黑)
    - [X] 查询关系
- 信息获取
    - [X] 个人基本信息
    - [X] 硬币状态及花费历史
    - [X] 每日经验值奖励获取
    - [X] 大会员状态
    - [X] 实名状态
    - [X] 昵称是否可用
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
        - [X] 订阅番剧
        - [X] 訂閱標籤
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
- 视频
    - [X] 基本信息
    - [X] 在线人数
    - [X] 高能进度条
    - [X] Tags
    - [X] 分P
    - [X] 所属合集
    - [X] 点赞/投币/收藏/一键三连 及 状态查询
    - [X] 全清晰度(8K/4K/1080P+) 音视频流获取
        - [ ] 下载?
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
    - [X] 获取信息
    - [X] 签到
        - [X] 检查签到信息(本月/上月)
    - [X] 直播排行榜
        - [X] 主播元气榜
        - [X] 用户能量榜
        - [X] 主播舰队榜
        - [X] 船员价值榜
        - [X] 舰船人数榜
        - [X] 用户等级榜
        - [X] 主播等级榜
        - [X] 勋章等级榜
    - [X] 建立 WebSocket 消息流连接
        - [X] 发送 认证包 & 心跳包
        - [X] 接收 认证回应 & 心跳回应
        - [X] 接收 普通包 & 解析
            - [X] 礼物连发
            - [X] 弹幕信息
            - [X] 舰长特效
            - [X] 上舰信息
            - [X] 高能榜变化 V1, V2
            - [X] 高能榜TOP3 变化
            - [X] 登上热门 V1, V2
            - [X] 房间信息变化(标题更改)
            - [X] 房间实时信息(粉丝, 粉丝团)
            - [X] 交互信息
            - [X] 交互游戏信息
            - [X] 直播活动页面信息
            - [X] 广播信息
            - [X] SuperChat 进场/发送/删除
                - [X] 日语样式
            - [X] 续费/开通舰长提示
            - [X] 活动 banner 显示
            - [X] 抽奖 开始/结束/审核/获奖
    - [X] 直播視頻流 全分辨率獲取
        - [ ] 下載?
- 表情
    - [X] 获取表情列表
- 时间
    - [X] 获取服务器时间戳
- Cookie 存储
    - [X] 提供 FileCookieStorage (基于 Ktor AcceptAllCookies, 以及 [korio](https://github.com/korlibs/korio) 的 VfsFile)

</details>

敏感 API 不会被支持, 如 B 币钱包、注册、新人答题、课程下载。同时 API 会优先支持读取操作。

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
- [bilibili-API-collect](https://github.com/SocialSisterYi/bilibili-API-collect) - 哔哩哔哩-API收集整理
