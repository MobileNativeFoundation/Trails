plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.squareup.anvil")
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

    namespace = "org.mobilenativefoundation.pokesocial.android.common.pig"
}

dependencies {
    implementation(libs.jetbrains.compose.material)
    implementation(libs.jetbrains.compose.ui)
    implementation(libs.compose.material3)
    implementation(libs.dagger.dagger)
    kapt(libs.dagger.compiler)
}