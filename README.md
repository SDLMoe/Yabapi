# <h1>Yabapi 😱</h1>

[![License: CDDL](https://img.shields.io/badge/license-CDDL-%233778b4)](https://opensource.org/licenses/MIT)

Bilibili API for Kotlin

🔧 WIP

# Intro

Yabapi 是一个 B 站第三方 Kotlin API 库。

# License

[CDDL License](https://github.com/SDLMoe/Yabapi/blob/kotlin-mt/LICENSE) 

本项目开源且免费，引用本项目时，您需要明确指出使用了本项目。

同时，请注意，CDDL 不兼容 GPL 协议。

# 特性

## 多平台

Yabapi 是一个 [Kotlin Multiplatform](https://kotlinlang.org/docs/mpp-intro.html) 项目，得益于 [Ktor](https://ktor.io/) 与众多其他多平台库的支持，主要功能可在 JVM、JavaScript、Native 多个平台上运行。

## 类型安全

Kotlin 为开发者提供了巨大帮助，包括在语言层面上对空指针的避免，以及完备的序列化库 [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization).

Yabapi 基于此，力求类型安全，避免空指针，减少运行时错误。

# 支持的 API

- 登录
  - [X] 图形验证码获取 (验证需通过 [geetest-validator](https://github.com/kuresaru/geetest-validator))
  - [X] Web 登录
    - [X] Cookie
    - [X] 密码登录
      - [ ] Native 平台的 RSA 支持
    - [X] 扫码登录
    - [ ] 短信登录
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
- 视频
  - [X] 获取视频信息
  - [X] 获取视频分P
  - [X] 获取视频所属合集
  - [X] 点赞/投币/收藏/一键三连 及 状态查询
  - [X] 全清晰度(8K/4K/1080P+) 音视频流获取
    - [ ] 下载?
- [X] 表情
  - [X] 获取表情列表
- [X] 时间
  - [X] 获取服务器时间戳
- Cookie 存储
  - 提供 FileCookieStorage (基于 Ktor AcceptAllCookies, 以及 [korio](https://github.com/korlibs/korio) 的 VfsFile)

敏感 API 不会被支持, 如 B 币钱包、注册、新人答题、课程下载。同时 API 会优先支持读取操作。

# Thanks

- [Kotlin](https://github.com/JetBrains/kotlin) - A modern programming language that makes developers happier.
- [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) - Kotlin multiplatform / multi-format **reflectionless** serialization
- [Ktor](https://github.com/ktorio/ktor) - An asynchronous framework for creating microservices, web applications and more.
- [Kermit](https://github.com/touchlab/Kermit) - Multiplatform logger library
- [Korlibs](https://docs.korge.org/) - Korlibs is a set of Kotlin Common modern libraries to do full stack development
- [bilibili-API-collect](https://github.com/SocialSisterYi/bilibili-API-collect) - 哔哩哔哩-API收集整理
