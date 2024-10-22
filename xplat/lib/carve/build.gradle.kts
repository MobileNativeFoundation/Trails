plugins {
    id("plugin.trails.android.library")
    id("plugin.trails.kotlin.multiplatform")
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.material3)
                implementation(compose.components.uiToolingPreview)
                implementation(compose.components.resources)
                implementation(libs.coil.compose)
                implementation(libs.coil.network.ktor3)
            }
        }
    }
}

android {
    namespace = "org.mobilenativefoundation.trails.xplat.lib.carve"
}