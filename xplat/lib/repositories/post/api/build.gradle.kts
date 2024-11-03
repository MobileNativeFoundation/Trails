
plugins {
    id("plugin.trails.android.library")
    id("plugin.trails.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.xplat.lib.models)
                api(projects.xplat.lib.rest.api)
                implementation(libs.kotlinx.datetime)
                api(libs.kotlinx.coroutines.core)
                api(projects.xplat.lib.operations)
            }
        }
    }
}

android {
    namespace = "org.mobilenativefoundation.trails.xplat.lib.repositories.post.api"
}