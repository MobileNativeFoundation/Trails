plugins {
    id("plugin.trails.android.library")
    id("plugin.trails.kotlin.multiplatform")
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
}

kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.xplat.core.circuit.api)

                implementation(projects.xplat.lib.carve)
                implementation(projects.xplat.feat.homeScreen.api)

                // 3P
                implementation(compose.ui)
                implementation(compose.material3)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlin.inject.runtime)
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
    namespace = "org.mobilenativefoundation.trails.xplat.core.circuit.impl"
}