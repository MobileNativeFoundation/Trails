plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.squareup.anvil")
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

    namespace = "org.mobilenativefoundation.trails.android.feat.hike"
}

dependencies {
    implementation(libs.dagger.dagger)
    kapt(libs.dagger.compiler)
    implementation(libs.jetbrains.compose.material3)
    implementation(libs.google.maps.sdk)
    implementation(libs.google.location.sdk)
    implementation(libs.google.maps.compose)
    implementation(libs.google.maps.compose.widgets)
    implementation(libs.google.maps.compose.utils)
    implementation(libs.androidx.appCompat)
    implementation(libs.androidx.viewmodel)
    implementation(project(":shared:data:entity"))
    implementation(project(":android:common:scoping"))
    implementation(project(":shared:tig"))
    implementation(libs.jetbrains.compose.ui.tooling.preview)
}