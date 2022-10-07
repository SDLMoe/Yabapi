import java.util.Properties

val local = Properties()
local.load(project.rootProject.file("local.properties").inputStream().buffered())

allprojects {
    group = "moe.sdl.yabapi"
    version = if (local["build.snapshot"]?.toString()?.toBoolean() != true) {
        "0.11.1"
    } else {
        val ver = local["build.version"]
            as? String
            ?: error("build.version is not String")
        if (!ver.endsWith("-SNAPSHOT")) {
            error("Snapshot version does not end with '-SNAPSHOT'")
        }
        ver
    }
    repositories {
        mavenCentral()
    }
}
