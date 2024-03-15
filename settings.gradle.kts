pluginManagement {
    includeBuild("tooling")

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
        id("app.cash.sqldelight").version("2.0.0-alpha05")
    }
}

rootProject.name = "Trails"

include(":android:app")
include(":android:common:scoping")
include(":android:feat:hike")
include(":android:feat:trail")
include(":android:feat:timeline:home")
include(":android:feat:timeline:trails")
include(":shared:tig")
include(":shared:navigation")
include(":lib:paging:core")
include(":lib:paging:compose")
include(":shared:timeline")
include(":shared:ui")
include(":shared:data:api")
include(":shared:data:db")
include(":shared:data:entity")
include(":shared:data:flag")
include(":shared:mock")
include(":server")
