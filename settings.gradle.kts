rootProject.name = "yabapi"
include("yabapi-core")

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://s01.oss.sonatype.org/content/repositories/snapshot")
    }
}

plugins {
    id("de.fayard.refreshVersions") version "0.40.2"
}
