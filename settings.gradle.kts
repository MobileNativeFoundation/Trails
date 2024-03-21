enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("tooling")

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}
rootProject.name = "Trails"

// Android > App
include(":android:app")

// Lib
include(":lib:paging:core")
include(":lib:paging:compose")

// Common
include(":common:homeTab:api")
include(":common:homeTab:impl")
include(":common:bookmarksTab:impl")
include(":common:bookmarksTab:api")
include(":common:hikeTab:impl")
include(":common:hikeTab:api")
include(":common:profileTab:impl")
include(":common:profileTab:api")
include(":common:searchTab:impl")
include(":common:searchTab:api")
include(":common:navigation:api")
include(":common:navigation:impl")
include(":common:networking:api")
include(":common:networking:impl")
include(":common:tig:compose")
include(":common:ui")
