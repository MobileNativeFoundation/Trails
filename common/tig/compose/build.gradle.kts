plugins {
    id("plugin.trails.android.library")
    id("plugin.trails.kotlin.multiplatform")
    alias(libs.plugins.compose)
}

kotlin {
    androidTarget {
        plugins.apply(libs.plugins.paparazzi.get().pluginId)
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.material3)
                implementation(compose.components.uiToolingPreview)
                implementation(compose.components.resources)
            }
        }

        val androidUnitTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "org.mobilenativefoundation.trails.common.tig.compose"
}