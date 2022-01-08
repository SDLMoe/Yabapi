// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version Versions.kotlin
}

val mingwPath = File(System.getenv("MINGW64_DIR") ?: "C:/msys64/mingw64")

@Suppress("UNUSED_VARIABLE")
kotlin {

    explicitApiWarning()

    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    nativeTarget.apply {
        compilations["main"].cinterops {
            /* Template for add external native library
            val template by creating {
                when (preset) {
                    presets["macosX64"] -> includeDirs.headerFilterOnly("/usr/local/opt/libname/include","/opt/local/include")
                    presets["linuxX64"] -> includeDirs.headerFilterOnly("/usr/include", "/usr/include/x86_64-linux-gnu")
                    presets["mingwX64"] -> includeDirs.headerFilterOnly(mingwPath.resolve("include"))
                }
            }
            */
        }
    }

    sourceSets {
        all {
            OptInAnnotations.list.forEach {
                languageSettings.optIn(it)
            }
        }

        val commonMain by getting {
            dependencies {
                // Kotlinx Libraries
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:1.3.2")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-native-mt")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.1")
                implementation("org.jetbrains.kotlinx:atomicfu:0.17.0")
                // Ktor
                implementation("io.ktor:ktor-client-core:${Versions.ktor}")
                implementation("io.ktor:ktor-client-serialization:${Versions.ktor}")
                implementation("io.ktor:ktor-client-websockets:${Versions.ktor}")
                implementation("io.ktor:ktor-client-encoding:${Versions.ktor}")
                // Encoding
                implementation("io.matthewnelson.kotlin-components:encoding-base64:1.0.3")
                implementation("com.soywiz.korlibs.krypto:krypto:2.2.0")
                // Logger
                implementation("co.touchlab:kermit:1.0.0")
                // File
                implementation("com.soywiz.korlibs.korio:korio:2.2.1")
            }

        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-cio:${Versions.ktor}")
            }
        }
        val jvmTest by getting
        val nativeMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-curl:${Versions.ktor}")
            }
        }
        val nativeTest by getting
    }
}
