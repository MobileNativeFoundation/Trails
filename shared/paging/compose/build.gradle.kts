plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
}

kotlin {
    jvm()
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.store)
                implementation(libs.store.cache)
                implementation(libs.kotlinx.serialization.core)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.compose.material3)
                implementation(libs.jetbrains.compose.ui)
                implementation(project(":shared:paging:core"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.turbine)
                implementation(libs.kotlinx.coroutines.test)
                implementation(kotlin("test"))
            }
        }
    }

}

android {

    defaultConfig {
        minSdk = 27
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }

    compileSdk = 33
    compileSdkVersion = "android-33"

    namespace = "org.mobilenativefoundation.trails.shared.paging.compose"
}

