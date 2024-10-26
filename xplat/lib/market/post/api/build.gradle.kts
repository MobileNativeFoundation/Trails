
plugins {
    id("plugin.trails.android.library")
    id("plugin.trails.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.xplat.lib.models)
                implementation(libs.kotlinx.datetime)
            }
        }
    }
}

android {
    namespace = "org.mobilenativefoundation.trails.xplat.lib.market.post.api"
}