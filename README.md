<img src="imgs/yabapi-banner.png" alt="yabapi logo">

[![License: CC0](https://img.shields.io/badge/License-CC0-lightgrey?style=for-the-badge)](https://creativecommons.org/publicdomain/zero/1.0/legalcode)
[![Maven-Central Yabapi Version](https://img.shields.io/maven-central/v/moe.sdl.yabapi/yabapi-core-jvm?style=for-the-badge)](https://repo1.maven.org/maven2/moe/sdl/yabapi/)
[![Maven-Central Yabapi Snapshots Version](https://img.shields.io/nexus/s/moe.sdl.yabapi/yabapi-core-jvm?label=Maven%20Snapshots&server=https%3A%2F%2Fs01.oss.sonatype.org&style=for-the-badge)](https://s01.oss.sonatype.org/content/repositories/snapshots/moe/sdl/yabapi/yabapi-core-jvm/)

> ð§ WIP å¹¶æ²¡æè¿å¥ç¨³å®çæ¬, API å¯è½ä¼åçåå¨, [ç¹ä½ä¸­æ](README_zhHant.md)

# Intro

ç®èè¨ä¹ï¼Yabapi æ¯ä¸ä¸ª B ç«ç¬¬ä¸æ¹ Kotlin API åºã

# License

æ¬é¡¹ç®ä»£ç åºäº [CC0](https://github.com/SDLMoe/Yabapi/blob/kotlin-mt/LICENSE) åå, éæ¾äºå¬æé¢å, å¦æä½ ä¹æ, å¯ä»¥å¨ä½¿ç¨æ¶éä¸æçåå­(è¿å¹¶ä¸å¼ºå¶).

[æ¬é¡¹ç® Logo](./imgs/yabapi-logo.svg) åºäº [CC BY ND 4.0](https://creativecommons.org/licenses/by-nd/4.0/) åå, è¥éè¦ä½¿ç¨ Logo,
å¡å¿éä¸æ¬é¡¹ç®å°å, å¹¶ä¸ä¸å¾æ´æ¹å¶åå®¹. Banner ä½¿ç¨äº[ææºç­å®½](https://github.com/adobe-fonts/source-han-mono).

# ç¹æ§

## å¤å¹³å°

æ¬é¡¹ç®æ¯ Kotlin Multiplatform é¡¹ç®ï¼ç°å¨æ¯æ JVM å Native ä¸¤å¤§å¹³å°ï¼ç»å¤§å¤æ°åè½é½æä¾å¤å¹³å°æ¯æã

## é«æ§è½

åºäº [Kotlin åç¨](https://github.com/Kotlin/kotlinx.coroutines) çæ¯æï¼å¼åèå¯ä»¥è½»æ¾ååºé«æ§è½çä»£ç ã

## ç±»åå®å¨

Kotlin ä¸ºå¼åèæä¾äºå·¨å¤§å¸®å©ï¼åæ¬å¨è¯­è¨å±é¢ä¸å¯¹ç©ºæéçé¿åï¼ä»¥åå®å¤çåºåååº [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization).

Yabapi åºäºæ­¤ï¼åæ±ç±»åå®å¨ï¼é¿åç©ºæéï¼åå°è¿è¡æ¶éè¯¯ã

# æ¯æç API

<details>
<summary>æ¥çè¯¦æ</summary>

- ç»å½
    - [X] å¾å½¢éªè¯ç è·å (éªè¯ééè¿ [geetest-validator](https://github.com/kuresaru/geetest-validator))
    - [X] Web ç»å½
        - [X] Cookie
        - [X] å¯ç ç»å½
            - [ ] Native å¹³å°ç RSA æ¯æ
        - [X] æ«ç ç»å½
        - [X] ç­ä¿¡ç»å½
    - [ ] APP / TV?
- å³ç³»
    - [X] æ¥è¯¢ç²ä¸
    - [X] æ¥è¯¢å³æ³¨
    - [X] æ¥è¯¢ç¹å«å³æ³¨
    - [X] æ¹éæ¥è¯¢
    - [X] å³æ³¨/ææå³æ³¨/æé» ååæ¶æä½
    - [X] æ¹éæä½ (ä»å³æ³¨/æé»)
    - [X] æ¥è¯¢å³ç³»
- ä¿¡æ¯è·å
    - [X] ä¸ªäººåºæ¬ä¿¡æ¯
    - [X] ç¡¬å¸ç¶æåè±è´¹åå²
    - [X] æ¯æ¥ç»éªå¼å¥å±è·å
    - [X] å¤§ä¼åç¶æ
    - [X] å®åç¶æ
    - [X] æµç§°æ¯å¦å¯ç¨
    - [X] ç¨æ·ç©ºé´
        - [X] ç½®é¡¶ / ä»£è¡¨ä½
        - [X] è¿ææ¸¸æ
        - [X] è¿ææå¸
        - [X] ç©ºé´å¬å
        - [X] ç©ºé´è®¾ç½®
        - [X] Tags
        - [X] æ¶èå¤¹è·å
          - [X] åå»ºç
          - [X] æ¶èç
        - [X] ç¨åè§ç
          - [X] æ¥ç / å¢å  / å é¤ / æ¸é¤
        - [X] ç©ºé´é¢é (è§é¢åé)
          - [X] ä¿¡æ¯è·å
        - [X] è®¢éçªå§
        - [X] è®¢éæ ç­¾
- æç´¢
  - [X] ç»¼åæç´¢
  - [X] åç±»æç´¢
    - [X] è§é¢
    - [X] çªå§ å å§é
    - [X] ç¨æ·
    - [X] ç´æ­é´ å ç´æ­ç¨æ·
    - [X] è¯é¢
    - [X] ä¸æ 
    - [X] ç¸å³ç­é
- è§é¢
    - [X] åºæ¬ä¿¡æ¯
    - [X] å¨çº¿äººæ°
    - [X] é«è½è¿åº¦æ¡
    - [X] Tags
    - [X] åP
    - [X] æå±åé
    - [X] ç¹èµ/æå¸/æ¶è/ä¸é®ä¸è¿ å ç¶ææ¥è¯¢
    - [X] å¨æ¸æ°åº¦(8K/4K/1080P+) é³è§é¢æµè·å
        - [ ] ä¸è½½?
- ä¸æ 
  - [X] åºæ¬ä¿¡æ¯
  - [X] æéä¿¡æ¯
- å¨æ
    - [X] è·å æ°å¨æ / ç¹å®ç¨æ·
    - [X] å¨æè§£æ
        - [X] ææ¬
        - [X] å¾æ
        - [X] è§é¢
        - [X] çªå§
        - [X] ä¸æ 
        - [X] åé
        - [X] åäº«
        - [ ] ...
- ç¸ç°¿
    - [X] ä¸ä¼ å¾ç

  > ä»äº [ç¸ç°¿ä¸ç«](https://h.bilibili.com) åºæ¬åºå¼, ä¸è®¡åæ·»å é¢å¤ API

- ä¸æ 
    - [X] åºæ¬ä¿¡æ¯
    - [X] è¯¦ç»ä¿¡æ¯
    - [X] æéä¿¡æ¯
- ç´æ­
    - [X] è·åä¿¡æ¯
    - [X] ç­¾å°
        - [X] æ£æ¥ç­¾å°ä¿¡æ¯(æ¬æ/ä¸æ)
    - [X] ç´æ­æè¡æ¦
        - [X] ä¸»æ­åæ°æ¦
        - [X] ç¨æ·è½éæ¦
        - [X] ä¸»æ­è°éæ¦
        - [X] è¹åä»·å¼æ¦
        - [X] è°è¹äººæ°æ¦
        - [X] ç¨æ·ç­çº§æ¦
        - [X] ä¸»æ­ç­çº§æ¦
        - [X] åç« ç­çº§æ¦
    - [X] å»ºç« WebSocket æ¶æ¯æµè¿æ¥
        - [X] åé è®¤è¯å & å¿è·³å
        - [X] æ¥æ¶ è®¤è¯ååº & å¿è·³ååº
        - [X] æ¥æ¶ æ®éå & è§£æ
            - [X] ç¤¼ç©è¿å
            - [X] å¼¹å¹ä¿¡æ¯
            - [X] è°é¿ç¹æ
            - [X] ä¸è°ä¿¡æ¯
            - [X] é«è½æ¦åå V1, V2
            - [X] é«è½æ¦TOP3 åå
            - [X] ç»ä¸ç­é¨ V1, V2
            - [X] æ¿é´ä¿¡æ¯åå(æ é¢æ´æ¹)
            - [X] æ¿é´å®æ¶ä¿¡æ¯(ç²ä¸, ç²ä¸å¢)
            - [X] äº¤äºä¿¡æ¯
            - [X] äº¤äºæ¸¸æä¿¡æ¯
            - [X] ç´æ­æ´»å¨é¡µé¢ä¿¡æ¯
            - [X] å¹¿æ­ä¿¡æ¯
            - [X] SuperChat è¿åº/åé/å é¤
                - [X] æ¥è¯­æ ·å¼
            - [X] ç»­è´¹/å¼éè°é¿æç¤º
            - [X] æ´»å¨ banner æ¾ç¤º
            - [X] æ½å¥ å¼å§/ç»æ/å®¡æ ¸/è·å¥
    - [X] ç´æ­è§é¢æµ å¨åè¾¨çè·å
        - [ ] ä¸è½½?
- è¡¨æ
    - [X] è·åè¡¨æåè¡¨
- æ¶é´
    - [X] è·åæå¡å¨æ¶é´æ³
- Cookie å­å¨
    - [X] æä¾ FileCookieStorage

</details>

ææ API ä¸ä¼è¢«æ¯æ, å¦ B å¸é±åãæ³¨åãæ°äººç­é¢ãè¯¾ç¨ä¸è½½ãåæ¶ API ä¼ä¼åæ¯æè¯»åæä½ã

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
- [bilibili-API-collect](https://github.com/SocialSisterYi/bilibili-API-collect) - åå©åå©-APIæ¶éæ´ç
