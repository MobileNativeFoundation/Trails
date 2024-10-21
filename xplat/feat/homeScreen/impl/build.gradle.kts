
plugins {
    id("plugin.trails.android.library")
    id("plugin.trails.kotlin.multiplatform")
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.plugin.parcelize)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.xplat.feat.homeScreen.api)
                implementation(projects.xplat.lib.carve)
                implementation(projects.xplat.lib.rest.api)

                // 3P
                implementation(compose.material3)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlin.inject.runtime)
                implementation(libs.coil.compose)
                implementation(libs.coil.network.ktor3)
            }
        }
    }
}

dependencies {
    add("kspAndroid", libs.kotlin.inject.compiler)
    add("kspIosX64", libs.kotlin.inject.compiler)
    add("kspIosArm64", libs.kotlin.inject.compiler)
}

android {
    namespace = "org.mobilenativefoundation.trails.xplat.feat.homeScreen.impl"
}