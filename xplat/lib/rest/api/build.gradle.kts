
plugins {
    id("plugin.trails.android.library")
    id("plugin.trails.kotlin.multiplatform")
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.xplat.lib.models)
                implementation(libs.kotlinx.serialization.core)
                implementation(libs.kotlinx.datetime)
            }
        }
    }
}

android {
    namespace = "org.mobilenativefoundation.trails.xplat.lib.trailsClient.api"
}