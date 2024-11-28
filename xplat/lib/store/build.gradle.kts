
plugins {
    id("plugin.trails.android.library")
    id("plugin.trails.kotlin.multiplatform")
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(libs.store)
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinx.serialization.core)
            }
        }
    }
}

android {
    namespace = "org.mobilenativefoundation.trails.xplat.lib.store"
}