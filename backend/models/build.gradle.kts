
plugins {
    id("plugin.trails.kotlin.multiplatform")
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlinx.serialization.core)
                implementation(libs.kotlinx.datetime)
            }
        }
    }
}
