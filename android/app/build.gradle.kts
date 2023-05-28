plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.squareup.anvil")
    kotlin("plugin.serialization")
}

android {
    namespace = "org.mobilenativefoundation.trails.android"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }

    defaultConfig {
        minSdk = 27
        multiDexEnabled = true
    }

    compileSdk = 33
    compileSdkVersion = "android-33"

    dexOptions {
        javaMaxHeapSize = "4g"
    }
}

dependencies {
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)
    implementation(libs.google.material)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appCompat)
    implementation(libs.androidx.core)
    implementation(libs.compose.material3)
    implementation(libs.jetbrains.compose.ui)
    implementation(libs.jetbrains.compose.ui.tooling.preview)
    debugImplementation(libs.jetbrains.compose.ui.tooling)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.compose.material3)
    implementation(libs.dagger.dagger)
    kapt(libs.dagger.compiler)
    implementation(libs.store)
    implementation(libs.store.cache)
    implementation(libs.kotlinx.serialization.core)
    implementation(libs.kotlinx.serialization.json)
    implementation("app.cash.sqldelight:android-driver:2.0.0-alpha05")

    implementation(project(":shared:tig"))
    implementation(project(":shared:navigation"))
    implementation(project(":shared:timeline"))
    implementation(project(":android:common:scoping"))
    implementation(project(":android:feat:hike"))
    implementation(project(":android:feat:trail"))
    implementation(project(":android:feat:timeline:home"))
    implementation(project(":android:feat:timeline:trails"))
    implementation(project(":lib:paging:core"))
    implementation(project(":shared:data:entity"))
    implementation(project(":shared:data:api"))
    implementation(project(":shared:data:db"))
    implementation(project(":shared:mock"))
}