plugins {
    id("plugin.trails.android.library")
    id("plugin.trails.kotlin.multiplatform")
    alias(libs.plugins.serialization)
    alias(libs.plugins.compose)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(compose.runtime)
                api(compose.components.resources)
                api(libs.circuit.foundation)
                api(projects.common.bookmarksTab.api)
                api(projects.common.hikeTab.api)
                api(projects.common.homeTab.api)
                api(projects.common.profileTab.api)
                api(projects.common.searchTab.api)
            }
        }
    }
}

android {
    namespace = "org.mobilenativefoundation.trails.common.core.api"
}