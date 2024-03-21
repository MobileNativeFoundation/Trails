plugins {
    id("plugin.trails.android.library")
    id("plugin.trails.kotlin.multiplatform")
    alias(libs.plugins.serialization)
    alias(libs.plugins.compose)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.material3)
                implementation(libs.coroutines.core)
                implementation(libs.kotlinInject.runtime)
            }
        }
    }
}

android {
    namespace = "org.mobilenativefoundation.trails.common.res"

    lint {
        baseline = file("lint-baseline.xml")
    }
}