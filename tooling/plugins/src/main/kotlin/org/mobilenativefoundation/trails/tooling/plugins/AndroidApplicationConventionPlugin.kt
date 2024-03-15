package org.mobilenativefoundation.trails.tooling.plugins

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.mobilenativefoundation.trails.tooling.extensions.BuildFlavor
import org.mobilenativefoundation.trails.tooling.extensions.BuildType
import org.mobilenativefoundation.trails.tooling.extensions.FlavorDimension
import org.mobilenativefoundation.trails.tooling.extensions.Versions
import org.mobilenativefoundation.trails.tooling.extensions.configureAndroid
import org.mobilenativefoundation.trails.tooling.extensions.configureAndroidCompose
import org.mobilenativefoundation.trails.tooling.extensions.configureFlavors

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                defaultConfig {
                    targetSdk = Versions.TARGET_SDK
                    missingDimensionStrategy(
                        FlavorDimension.contentType.name,
                        BuildFlavor.demo.name
                    )
                }

                buildFeatures {
                    buildConfig = true
                }

                configureAndroid()
                configureAndroidCompose(this)
                configureFlavors(this)

                buildTypes {
                    getByName(BuildType.DEBUG.applicationIdSuffix) {
                    }

                    getByName(BuildType.RELEASE.applicationIdSuffix) {
                    }
                }
            }
        }
    }
}
