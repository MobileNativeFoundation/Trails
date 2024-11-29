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

// Backend
include(":backend:server")

// XPLAT
/// Core
include(":xplat:core:circuit:api")
include(":xplat:core:circuit:impl")

/// Feat
include(":xplat:feat:bottomNav:api")
include(":xplat:feat:bottomNav:impl")
include(":xplat:feat:homeScreen:api")
include(":xplat:feat:homeScreen:impl")
include(":xplat:feat:messagesScreen:api")
include(":xplat:feat:messagesScreen:impl")
include(":xplat:feat:postScreen:api")
include(":xplat:feat:postScreen:impl")
include(":xplat:feat:profileScreen:api")
include(":xplat:feat:profileScreen:impl")
include(":xplat:feat:searchScreen:api")
include(":xplat:feat:searchScreen:impl")

/// Lib
include(":xplat:lib:carve")
include(":xplat:lib:db")
include(":xplat:lib:repositories:post:api")
include(":xplat:lib:repositories:post:impl")
include(":xplat:lib:repositories:post:test")
include(":xplat:lib:models")
include(":xplat:lib:rest:api")
include(":xplat:lib:rest:impl")
include(":xplat:lib:store")
include(":xplat:lib:operations")
include(":xplat:lib:coroutines")
