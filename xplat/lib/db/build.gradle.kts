plugins {
    id("plugin.trails.android.library")
    id("plugin.trails.kotlin.multiplatform")
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.sqldelight)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.xplat.lib.models)
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlin.inject.runtime)
                api(libs.coroutines.extensions)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.android.driver)
            }
        }

        nativeMain {
            dependencies {
                implementation(libs.native.driver)
            }
        }

        jvmMain {
            dependencies {
                implementation(libs.sqlite.driver)
            }
        }

        jsMain {
            dependencies {
                implementation(npm("@cashapp/sqldelight-sqljs-worker", "2.0.2"))
                implementation(npm("sql.js", "1.6.2"))
                implementation(devNpm("copy-webpack-plugin", "9.1.0"))
                implementation(libs.web.worker.driver)
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
    namespace = "org.mobilenativefoundation.trails.xplat.lib.db"
}

sqldelight {
    databases {
        create("TrailsDatabase") {
            packageName.set("org.mobilenativefoundation.trails.xplat.lib.db")
            generateAsync.set(true)
        }
    }
}
