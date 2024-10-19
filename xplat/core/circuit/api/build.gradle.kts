plugins {
    id("plugin.trails.android.library")
    id("plugin.trails.kotlin.multiplatform")
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.xplat.feat.homeScreen.api)

                // 3P
                api(compose.runtime)
                api(libs.circuit.foundation)
            }
        }
    }
}

android {
    namespace = "org.mobilenativefoundation.trails.xplat.core.circuit.api"
}