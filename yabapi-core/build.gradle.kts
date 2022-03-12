import java.util.Properties

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("org.jlleitschuh.gradle.ktlint")
    `maven-publish`
    signing
}

val mingwPath = File(System.getenv("MINGW64_DIR") ?: "C:/msys64/mingw64")

ext["signing.keyId"] = null
ext["signing.password"] = null
ext["signing.secretKeyRingFile"] = null
ext["ossrhUsername"] = null
ext["ossrhPassword"] = null

val secretPropsFile: File = project.rootProject.file("local.properties")
if (secretPropsFile.exists()) {
    secretPropsFile.reader().use {
        Properties().apply {
            load(it)
        }
    }.onEach { (name, value) ->
        ext[name.toString()] = value
    }
} else {
    ext["signing.keyId"] = System.getenv("SIGNING_KEY_ID")
    ext["signing.password"] = System.getenv("SIGNING_PASSWORD")
    ext["signing.secretKeyRingFile"] = System.getenv("SIGNING_SECRET_KEY_RING_FILE")
    ext["ossrhUsername"] = System.getenv("OSSRH_USERNAME")
    ext["ossrhPassword"] = System.getenv("OSSRH_PASSWORD")
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

fun getExtraString(name: String) = ext[name]?.toString()

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
                implementation(KotlinX.serialization.json)
                implementation(KotlinX.serialization.protobuf)
                implementation(KotlinX.coroutines.core)
                implementation(KotlinX.datetime)
                implementation("org.jetbrains.kotlinx:atomicfu:_")
                // Ktor
                implementation(Ktor.client.core)
                implementation(Ktor.client.websockets)
                implementation(Ktor.client.encoding)
                // Encoding
                implementation("io.matthewnelson.kotlin-components:encoding-base64:_")
                implementation("com.soywiz.korlibs.krypto:krypto:_")
                // IO
                implementation(Square.okio)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(Ktor.client.cio)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(Testing.junit.jupiter)
            }
        }
        val nativeMain by getting {
            dependencies {
                implementation(Ktor.client.curl)
            }
        }
        val nativeTest by getting
    }
}

publishing {
    // Configure maven central repository
    repositories {
        maven {
            name = "sonatype"
            val releasesRepoUrl = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
            val snapshotsRepoUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
            val url = if (version.toString().contains("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
            setUrl(url)
            credentials {
                username = getExtraString("ossrhUsername")
                password = getExtraString("ossrhPassword")
            }
        }
    }

    // Configure all publications
    publications.withType<MavenPublication> {

        // Stub javadoc.jar artifact
        artifact(javadocJar.get())

        // Provide artifacts information requited by Maven Central
        pom {
            name.set("Yabapi")
            description.set("A Third-party API for Bilibili")
            url.set("https://github.com/SDLMoe/Yabapi/")

            licenses {
                license {
                    name.set("CC0")
                    url.set("https://creativecommons.org/publicdomain/zero/1.0/legalcode")
                }
            }
            developers {
                developer {
                    id.set("Colerar")
                    email.set("233hbj@gmail.com")
                }
            }
            scm {
                url.set("https://github.com/SDLMoe/Yabapi.git")
            }
        }
    }
}

signing {
    sign(publishing.publications)
}

configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.HTML)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
    }
}
