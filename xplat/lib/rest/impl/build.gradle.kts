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
                api(projects.xplat.lib.rest.api)

                implementation(libs.kotlin.inject.runtime)
                implementation(libs.ktor.core)
                implementation(libs.ktor.negotiation)
                implementation(libs.ktor.serialization.json)
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
    namespace = "org.mobilenativefoundation.trails.xplat.lib.trailsClient.impl"
}