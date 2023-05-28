plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    android()
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.store)
                implementation(libs.store.cache)
                implementation(libs.kotlinx.serialization.core)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.kotlinx.coroutines.core)
                implementation(project(":lib:paging:core"))
                implementation(project(":lib:paging:compose"))
                implementation(project(":shared:data:entity"))
                implementation(project(":shared:data:api"))
                implementation(project(":shared:data:db"))
                implementation(libs.jetbrains.compose.ui)
                implementation(libs.jetbrains.compose.runtime)
                implementation(libs.jetbrains.compose.material3)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.viewmodel)
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

    namespace = "org.mobilenativefoundation.trails.shared.timeline"
}

