plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.squareup.anvil")
}

android {
    namespace = "org.mobilenativefoundation.pokesocial.android"

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
    implementation(libs.jetbrains.compose.material)
    implementation(libs.jetbrains.compose.ui)
    implementation(libs.jetbrains.compose.ui.tooling.preview)
    debugImplementation(libs.jetbrains.compose.ui.tooling)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.compose.material3)
    implementation(libs.dagger.dagger)
    kapt(libs.dagger.compiler)

    implementation(project(":android:common:scoping"))
}