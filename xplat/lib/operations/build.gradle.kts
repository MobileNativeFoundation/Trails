
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
                api(projects.xplat.lib.rest.api)
                implementation(libs.kotlinx.datetime)
                api(libs.kotlinx.coroutines.core)
                api(libs.kotlinx.serialization.core)
            }
        }
    }
}

android {
    namespace = "org.mobilenativefoundation.trails.xplat.lib.operations"
}