@file:Suppress("MaxLineLength")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktlint: Configuration by configurations.creating

plugins {
    kotlin("jvm") version "1.6.0"
    id("io.gitlab.arturbosch.detekt").version("1.19.0")
    kotlin("plugin.serialization") version "1.6.0"
}

group = "sdl.moe"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Code Formatter
    ktlint("com.pinterest:ktlint:0.43.2") {
        attributes {
            attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
        }
    }
    // Test
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.6.0")
    // Reflect
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.0")
    // Kotlinx Libraries
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
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
    // Encryption
    implementation("org.bouncycastle:bcprov-jdk15on:1.70")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

val outputDir = "${project.buildDir}/reports/ktlint/"
val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))

val ktlintCheck by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Check Kotlin code style."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args = listOf("src/**/*.kt")
}

val ktlintFormat by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Fix Kotlin code style deviations."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args = listOf("-F", "src/**/*.kt")
}

detekt {
    buildUponDefaultConfig = true // preconfigure defaults
    allRules = false // activate all available (even unstable) rules.
    config =
        files("$projectDir/config/detekt.yml") // point to your custom config defining rules to run, overwriting default behavior
    baseline = file("$projectDir/config/baseline.xml") // a way of suppressing issues before introducing detekt
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        html.required.set(true) // observe findings in your browser with structure and code snippets
        xml.required.set(true) // checkstyle like format mainly for integrations like Jenkins
        txt.required.set(true) // similar to the console output, contains issue signature to manually edit baseline files
        sarif.required.set(true) // standardized SARIF format (https://sarifweb.azurewebsites.net/) to support integrations with GitHub Code Scanning
    }
}

kotlin {
    explicitApi()
    explicitApiWarning()
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    jvmTarget = "11"
}
tasks.withType<io.gitlab.arturbosch.detekt.DetektCreateBaselineTask>().configureEach {
    jvmTarget = "11"
}
