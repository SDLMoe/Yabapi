// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

import java.io.FileInputStream
import java.util.Properties

plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

val kotlinVersion = FileInputStream(file("../gradle.properties")).use { propFile ->
    val ver = Properties().apply { load(propFile) }["kotlin.version"]
    require(ver is String) { "kotlin.version must be string in ../gradle.properties, got $ver instead" }
    ver
}
