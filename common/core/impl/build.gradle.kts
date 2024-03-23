plugins {
    id("plugin.trails.android.library")
    id("plugin.trails.kotlin.multiplatform")
    alias(libs.plugins.serialization)
    alias(libs.plugins.compose)
    alias(libs.plugins.ksp)
}

kotlin {
    androidTarget {
        plugins.apply(libs.plugins.paparazzi.get().pluginId)
    }
    
    sourceSets {
        
        val commonMain by getting {
            dependencies {
                implementation(compose.material3)
                implementation(libs.coroutines.core)
                implementation(libs.kotlinInject.runtime)

                api(projects.common.navigation.api)
                implementation(projects.common.bookmarksTab.api)
                implementation(projects.common.hikeTab.api)
                implementation(projects.common.homeTab.api)
                implementation(projects.common.profileTab.api)
                implementation(projects.common.searchTab.api)
                implementation(projects.common.tig.compose)
                api(projects.common.core.api)
            }   
        }
        
        androidUnitTest {
            dependencies {
                dependsOn(commonMain)
                implementation(kotlin("test"))
            }
        }
    }
}

dependencies {
    add("kspAndroid", libs.kotlinInject.compiler)
    add("kspIosX64", libs.kotlinInject.compiler)
    add("kspIosArm64", libs.kotlinInject.compiler)
}

android {
    namespace = "org.mobilenativefoundation.trails.common.core.impl"
}