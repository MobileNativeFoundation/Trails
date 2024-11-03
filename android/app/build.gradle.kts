plugins {
    id("plugin.trails.android.application")
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
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
    implementation(projects.xplat.lib.carve)
    implementation(projects.xplat.core.circuit.impl)
    implementation(projects.xplat.feat.homeScreen.impl)
    implementation(projects.xplat.feat.messagesScreen.impl)
    implementation(projects.xplat.feat.profileScreen.impl)
    implementation(projects.xplat.feat.postScreen.impl)
    implementation(projects.xplat.feat.searchScreen.impl)
    implementation(projects.xplat.feat.bottomNav.impl)
    implementation(projects.xplat.lib.rest.impl)
    implementation(projects.xplat.lib.repositories.post.impl)
    implementation(projects.xplat.lib.db)

    // 3P
    implementation(compose.runtime)
    implementation(compose.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.core)
    implementation(libs.kotlin.inject.runtime)
    ksp(libs.kotlin.inject.compiler)
}
