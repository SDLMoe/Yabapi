plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version Versions.kotlin
}

kotlin {

    explicitApi()
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(BOTH) {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
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

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Kotlinx Libraries
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.1")
                // Ktor
                implementation("io.ktor:ktor-client-core:${Versions.ktor}")
                implementation("io.ktor:ktor-client-serialization:${Versions.ktor}")
                implementation("io.ktor:ktor-client-websockets:${Versions.ktor}")
                // Encoding
                implementation("io.matthewnelson.kotlin-components:encoding-base64:1.0.3")
                implementation("com.soywiz.korlibs.krypto:krypto:2.2.0")
                // Logger
                implementation("co.touchlab:kermit:1.0.0")
                // File
                implementation("com.squareup.okio:okio:3.0.0")
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
        val jsMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-js:${Versions.ktor}")
            }
        }
        val jsTest by getting
        val nativeMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-curl:${Versions.ktor}")
            }
        }
        val nativeTest by getting
    }
}
