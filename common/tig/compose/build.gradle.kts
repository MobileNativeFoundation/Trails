plugins {
    id("plugin.trails.android.library")
    id("plugin.trails.kotlin.multiplatform")
    alias(libs.plugins.compose)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(compose.material3)
                implementation(projects.common.res)
            }
        }
    }
}

android {
    namespace = "org.mobilenativefoundation.trails.common.tig.compose"
}