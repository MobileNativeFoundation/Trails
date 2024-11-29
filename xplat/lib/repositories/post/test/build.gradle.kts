import org.gradle.internal.impldep.org.testng.reporters.XMLUtils

plugins {
    id("plugin.trails.android.library")
    id("plugin.trails.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.xplat.lib.repositories.post.api)
                implementation(projects.xplat.lib.coroutines)
                implementation(projects.xplat.lib.db)
                implementation(libs.kotlinx.datetime)
            }
        }
    }
}

android {
    namespace = "org.mobilenativefoundation.trails.xplat.lib.repositories.post.test"
}