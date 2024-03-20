plugins {
    id("plugin.trails.android.application")
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose)
}

android {
    namespace = "org.mobilenativefoundation.trails.android.app"

    defaultConfig {
        applicationId = "org.mobilenativefoundation.trails"
        versionCode = 1
        versionName = "1.0"
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            excludes.add("/META-INF/versions/9/previous-compilation-data.bin")
        }
    }
}

dependencies {

    implementation(compose.runtime)
    implementation(compose.material3)
    implementation(libs.androidx.appCompat)
    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.core)
    implementation(libs.coroutines.android)
    implementation(libs.kotlinInject.runtime)
    implementation(libs.serialization.core)
    implementation(libs.serialization.json)
    implementation(libs.voyager.navigator)

    ksp(libs.kotlinInject.compiler)
}

ksp {
    arg("me.tatarka.inject.generateCompanionExtensions", "true")
}