plugins {
    id("plugin.trails.android.library")
    id("plugin.trails.kotlin.multiplatform")
    alias(libs.plugins.serialization)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(libs.circuit.foundation)
            }
        }
    }
}

android {
    namespace = "org.mobilenativefoundation.trails.common.profileTab.api"
}