plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.jetbrains.compose.ui)
                implementation(libs.jetbrains.compose.material3)

                implementation(libs.jetbrains.compose.ui.tooling.preview)
                implementation(libs.jetbrains.compose.ui.tooling)
            }
        }
    }

}

android {

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }
    defaultConfig {
        minSdk = 27
    }

    compileSdk = 33
    compileSdkVersion = "android-33"

    namespace = "org.mobilenativefoundation.trails.shared.tig"
}

