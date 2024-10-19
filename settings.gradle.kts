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

// Android
include(":android:app")

// XPLAT
/// Core
include(":xplat:core:circuit:api")
include(":xplat:core:circuit:impl")

/// Feat
include(":xplat:feat:bottomNav:api")
include(":xplat:feat:bottomNav:impl")
include(":xplat:feat:homeScreen:api")
include(":xplat:feat:homeScreen:impl")
include(":xplat:feat:scaffold:api")
include(":xplat:feat:scaffold:impl")

/// Lib
include(":xplat:lib:carve")
include(":xplat:lib:rest:api")
include(":xplat:lib:rest:impl")

