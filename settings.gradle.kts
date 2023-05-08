pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    plugins {
        kotlin("multiplatform").version(extra["kotlin.version"] as String)
        kotlin("jvm").version(extra["kotlin.version"] as String)
        kotlin("android").version(extra["kotlin.version"] as String)
        id("com.android.application").version(extra["agp.version"] as String)
        id("com.android.library").version(extra["agp.version"] as String)
        id("org.jetbrains.compose").version(extra["compose.version"] as String)
        id("io.ktor.plugin").version(extra["ktor.version"] as String)
    }
}

rootProject.name = "trails"

include(":android:app")
include(":android:common:scoping")
include(":android:feat:hike")
include(":android:feat:trail")
include(":android:feat:following")
include(":shared:tig")
include(":shared:navigation")
include(":shared:ui")
include(":shared:data:api")
include(":shared:data:entity")
include(":server")
