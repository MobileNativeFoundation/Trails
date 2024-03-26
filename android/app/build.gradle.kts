import org.mobilenativefoundation.trails.tooling.extensions.configureAndroidCompose

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
    api(libs.circuit.foundation)


    with(projects.common) {
        api(bookmarksTab.impl)
        api(core.impl)
        api(hikeTab.impl)
        api(homeTab.impl)
        api(navigation.impl)
        api(networking.impl)
        api(profileTab.impl)
        api(searchTab.impl)
        api(tig.compose)
    }

    ksp(libs.kotlinInject.compiler)
}

ksp {
    arg("me.tatarka.inject.generateCompanionExtensions", "true")
}