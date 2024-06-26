
plugins {
    id("plugin.trails.android.library")
    id("plugin.trails.kotlin.multiplatform")
    alias(libs.plugins.serialization)
    alias(libs.plugins.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.plugin.parcelize)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.material3)
                implementation(libs.coroutines.core)
                implementation(libs.kotlinInject.runtime)
                implementation(libs.store)
                implementation(libs.store.cache)
                implementation(libs.store.core)
                implementation(libs.store.paging)

                implementation(projects.common.navigation.api)
                api(projects.common.bookmarksTab.api)
                implementation(projects.common.networking.api)
                implementation(projects.common.tig.compose)
            }
        }

        androidMain {
            dependencies {
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
    namespace = "org.mobilenativefoundation.trails.common.bookmarksTab.impl"
}