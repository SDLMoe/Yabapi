<img src="imgs/yabapi-banner.png" alt="yabapi logo">

[![License: CC0](https://img.shields.io/badge/License-CC0-lightgrey?style=for-the-badge)](https://creativecommons.org/publicdomain/zero/1.0/legalcode)
[![Maven-Central Yabapi Version](https://img.shields.io/maven-central/v/moe.sdl.yabapi/yabapi-core-jvm?style=for-the-badge)](https://repo1.maven.org/maven2/moe/sdl/yabapi/)
[![Maven-Central Yabapi Snapshots Version](https://img.shields.io/nexus/s/moe.sdl.yabapi/yabapi-core-jvm?label=Maven%20Snapshots&server=https%3A%2F%2Fs01.oss.sonatype.org&style=for-the-badge)](https://s01.oss.sonatype.org/content/repositories/snapshots/moe/sdl/yabapi/yabapi-core-jvm/)

> ð§ WIP ä¸¦æ²æé²å¥ç©©å®çæ¬, API å¯è½æç¼çè®å, [ç®ä½ä¸­æ](README.md)

# Intro

ç°¡èè¨ä¹ï¼Yabapi æ¯ä¸å B ç«ç¬¬ä¸æ¹ Kotlin API åº«ã

# License

æ¬é ç®ä»£ç¢¼åºæ¼ [CC0](https://github.com/SDLMoe/Yabapi/blob/kotlin-mt/LICENSE) åç¼, éæ¾æ¼å¬æé å, å¦æä½ æ¨æ, å¯ä»¥å¨ä½¿ç¨æéä¸æçåå­(éä¸¦ä¸å¼·è£½).

[æ¬é ç® Logo](./imgs/yabapi-logo.svg) åºæ¼ [CC BY ND 4.0](https://creativecommons.org/licenses/by-nd/4.0/) åç¼, è¥éè¦ä½¿ç¨ Logo,
åå¿éä¸æ¬é ç®å°å, ä¸¦ä¸ä¸å¾æ´æ¹å¶å§å®¹. Banner ä½¿ç¨äº[ææºç­å¯¬](https://github.com/adobe-fonts/source-han-mono).

# ç¹æ§

## å¤å¹³èº

æ¬é ç®æ¯ Kotlin Multiplatform é ç®ï¼ç¾å¨æ¯æ JVM å Native å©å¤§å¹³èºï¼çµå¤§å¤æ¸åè½é½æä¾å¤å¹³èºæ¯æã

## é«æ§è½

åºæ¼ [Kotlin åç¨](https://github.com/Kotlin/kotlinx.coroutines) çæ¯æï¼éç¼èå¯ä»¥è¼æ¾å¯«åºé«æ§è½çä»£ç¢¼ã

## é¡åå®å¨

Kotlin çºéç¼èæä¾äºå·¨å¤§å¹«å©ï¼åæ¬å¨èªè¨å±¤é¢ä¸å°ç©ºæéçé¿åï¼ä»¥åå®åçåºåååº« [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization).

Yabapi åºæ¼æ­¤ï¼åæ±é¡åå®å¨ï¼é¿åç©ºæéï¼æ¸å°éè¡æé¯èª¤ã

# æ¯æç API

<details>
<summary>æ¥çè©³æ</summary>

- ç»é
    - [X] åå½¢é©è­ç¢¼ç²å (é©è­ééé [geetest-validator](https://github.com/kuresaru/geetest-validator))
    - [X] Web ç»é
        - [X] Cookie
        - [X] å¯ç¢¼ç»é
            - [ ] Native å¹³èºç RSA æ¯æ
        - [X] æç¢¼ç»é
        - [X] ç­ä¿¡ç»é
    - [ ] APP / TV?
- éç³»
    - [X] æ¥è©¢ç²çµ²
    - [X] æ¥è©¢éè¨»
    - [X] æ¥è©¢ç¹å¥éè¨»
    - [X] æ¹éæ¥è©¢
    - [X] éè¨»/ææéè¨»/æé» ååæ¶æä½
    - [X] æ¹éæä½ (åéè¨»/æé»)
    - [X] æ¥è©¢éç³»
- ä¿¡æ¯ç²å
    - [X] åäººåºæ¬ä¿¡æ¯
    - [X] ç¡¬å¹£çæåè±è²»æ­·å²
    - [X] æ¯æ¥ç¶é©å¼çåµç²å
    - [X] å¤§æå¡çæ
    - [X] å¯¦åçæ
    - [X] æµç¨±æ¯å¦å¯ç¨
    - [X] ç¨æ¶ç©ºé
        - [X] ç½®é  / ä»£è¡¨ä½
        - [X] è¿æéæ²
        - [X] è¿ææå¹£
        - [X] ç©ºéå¬å
        - [X] ç©ºéè¨­ç½®
        - [X] Tags
        - [X] æ¶èå¤¾ç²å
          - [X] åµå»ºç
          - [X] æ¶èç
        - [X] ç¨å¾è§ç
          - [X] æ¥ç / å¢å  / åªé¤ / æ¸é¤
        - [X] ç©ºéé »é (è¦é »åé)
          - [X] ä¿¡æ¯ç²å
        - [X] è¨é±çªå
        - [X] è¨é±æ¨ç°½
- æç´¢
  - [X] ç¶åæç´¢
  - [X] åé¡æç´¢
    - [X] è¦é »
    - [X] çªå å åé
    - [X] ç¨æ¶
    - [X] ç´æ­é å ç´æ­ç¨æ¶
    - [X] è©±é¡
    - [X] å°æ¬
    - [X] ç¸éç¯©é¸
- è¦é »
    - [X] åºæ¬ä¿¡æ¯
    - [X] å¨ç·äººæ¸
    - [X] é«è½é²åº¦æ¢
    - [X] Tags
    - [X] åP
    - [X] æå±¬åé
    - [X] é»è´/æå¹£/æ¶è/ä¸éµä¸é£ å çææ¥è©¢
    - [X] å¨æ¸æ°åº¦(8K/4K/1080P+) é³è¦é »æµç²å
        - [ ] ä¸è¼?
- å°æ¬
  - [X] åºæ¬ä¿¡æ¯
  - [X] æéä¿¡æ¯
- åæ
    - [X] ç²å æ°åæ / ç¹å®ç¨æ¶
    - [X] åæè§£æ
        - [X] ææ¬
        - [X] åæ
        - [X] è¦é »
        - [X] çªå
        - [X] å°æ¬
        - [X] åé
        - [X] åäº«
        - [ ] ...
- ç¸ç°¿
    - [X] ä¸å³åç

  > ä»æ¼ [ç¸ç°¿å°ç«](https://h.bilibili.com) åºæ¬å»¢æ£, ä¸è¨åæ·»å é¡å¤ API

- å°æ¬
    - [X] åºæ¬ä¿¡æ¯
    - [X] è©³ç´°ä¿¡æ¯
    - [X] æéä¿¡æ¯
- ç´æ­
    - [X] ç²åä¿¡æ¯
    - [X] ç°½å°
        - [X] æª¢æ¥ç°½å°ä¿¡æ¯(æ¬æ/ä¸æ)
    - [X] ç´æ­æè¡æ¦
        - [X] ä¸»æ­åæ°£æ¦
        - [X] ç¨æ¶è½éæ¦
        - [X] ä¸»æ­è¦éæ¦
        - [X] è¹å¡å¹å¼æ¦
        - [X] è¦è¹äººæ¸æ¦
        - [X] ç¨æ¶ç­ç´æ¦
        - [X] ä¸»æ­ç­ç´æ¦
        - [X] åç« ç­ç´æ¦
    - [X] å»ºç« WebSocket æ¶æ¯æµé£æ¥
        - [X] ç¼é èªè­å & å¿è·³å
        - [X] æ¥æ¶ èªè­åæ & å¿è·³åæ
        - [X] æ¥æ¶ æ®éå & è§£æ
            - [X] ç¦®ç©é£ç¼
            - [X] å½å¹ä¿¡æ¯
            - [X] è¦é·ç¹æ
            - [X] ä¸è¦ä¿¡æ¯
            - [X] é«è½æ¦è®å V1, V2
            - [X] é«è½æ¦TOP3 è®å
            - [X] ç»ä¸ç±é V1, V2
            - [X] æ¿éä¿¡æ¯è®å(æ¨é¡æ´æ¹)
            - [X] æ¿éå¯¦æä¿¡æ¯(ç²çµ², ç²çµ²å)
            - [X] äº¤äºä¿¡æ¯
            - [X] äº¤äºéæ²ä¿¡æ¯
            - [X] ç´æ­æ´»åé é¢ä¿¡æ¯
            - [X] å»£æ­ä¿¡æ¯
            - [X] SuperChat é²å ´/ç¼é/åªé¤
                - [X] æ¥èªæ¨£å¼
            - [X] çºè²»/ééè¦é·æç¤º
            - [X] æ´»å banner é¡¯ç¤º
            - [X] æ½ç éå§/çµæ/å¯©æ ¸/ç²ç
    - [X] ç´æ­è¦é »æµ å¨åè¾¨çç²å
        - [ ] ä¸è¼?
- è¡¨æ
    - [X] ç²åè¡¨æåè¡¨
- æé
    - [X] ç²åæåå¨æéæ³
- Cookie å­å²
    - [X] æä¾ FileCookieStorage

</details>

ææ API ä¸æè¢«æ¯æ, å¦ B å¹£é¢åãè¨»åãæ°äººç­é¡ãèª²ç¨ä¸è¼ãåæ API æåªåæ¯æè®åæä½ã

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
- [bilibili-API-collect](https://github.com/SocialSisterYi/bilibili-API-collect) - å¶å©å¶å©-APIæ¶éæ´ç
