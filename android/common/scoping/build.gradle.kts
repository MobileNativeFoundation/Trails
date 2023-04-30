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

    compileSdk = 33
    compileSdkVersion = "android-33"

    namespace = "org.mobilenativefoundation.trails.android.common.scoping"
}

dependencies {
    implementation(libs.dagger.dagger)
    kapt(libs.dagger.compiler)
}