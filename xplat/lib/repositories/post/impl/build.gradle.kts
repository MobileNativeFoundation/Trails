import org.gradle.internal.impldep.org.testng.reporters.XMLUtils

plugins {
    id("plugin.trails.android.library")
    id("plugin.trails.kotlin.multiplatform")
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.xplat.lib.repositories.post.api)
                implementation(projects.xplat.lib.coroutines)
                implementation(projects.xplat.lib.db)
                implementation(libs.kotlin.inject.runtime)
                implementation(libs.ktor.core)
                implementation(libs.ktor.negotiation)
                implementation(libs.ktor.serialization.json)
                implementation(libs.store)
                implementation(libs.store.cache)
                implementation(libs.kotlinx.datetime)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.ktor.client.okhttp.jvm)
            }
        }
        nativeMain {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
        jvmMain {
            dependencies {
                implementation(libs.ktor.client.apache5)
            }
        }
        jsMain {
            dependencies {
                implementation(libs.ktor.client.js)
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
    namespace = "org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl"
}