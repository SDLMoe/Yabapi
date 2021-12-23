@file:Suppress("MaxLineLength")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktlint: Configuration by configurations.creating

plugins {
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.serialization") version "1.6.0"
}

group = "sdl.moe"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    // Test
    val jupiterVersion = "5.8.2"
    implementation("org.junit.jupiter:junit-jupiter:$jupiterVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$jupiterVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.6.0")
    // Kotlinx Libraries
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.1")
    // Logger
    implementation("io.github.microutils:kotlin-logging-jvm:2.1.16")
    implementation("org.slf4j:slf4j-api:1.7.32")
    implementation("ch.qos.logback:logback-classic:1.2.8")
    // Ktor
    val ktorVersion = "1.6.7"
    // implementation("io.ktor:ktor-server-core:$ktorVersion")
    // implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-serialization:$ktorVersion")
    implementation("io.ktor:ktor-client-websockets:$ktorVersion")
    // QR Code
    implementation("com.github.kenglxn.QRGen:javase:2.6.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

kotlin {
    explicitApi()
    explicitApiWarning()
}
