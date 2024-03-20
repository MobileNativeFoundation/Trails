
plugins {
    id("plugin.trails.android.library")
    id("plugin.trails.kotlin.multiplatform")
    alias(libs.plugins.serialization)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlinx.serialization.core)
            }
        }

        androidMain {
            dependencies {
            }
        }
        nativeMain {
            dependencies {
            }
        }
        jvmMain {
            dependencies {
            }
        }
    }
}

android {
    namespace = "org.mobilenativefoundation.trails.common.networking.api"
}