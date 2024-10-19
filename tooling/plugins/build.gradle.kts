plugins {
    `kotlin-dsl`
}

group = "org.mobilenativefoundation.trails.tooling"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17

    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationPlugin") {
            id = "plugin.trails.android.application"
            implementationClass = "org.mobilenativefoundation.trails.tooling.plugins.AndroidApplicationConventionPlugin"
        }

        register("androidComposePlugin") {
            id = "plugin.trails.android.compose"
            implementationClass = "org.mobilenativefoundation.trails.tooling.plugins.AndroidComposeConventionPlugin"
        }

        register("androidLibraryPlugin") {
            id = "plugin.trails.android.library"
            implementationClass = "org.mobilenativefoundation.trails.tooling.plugins.AndroidLibraryConventionPlugin"
        }

        register("kotlinAndroidLibraryPlugin") {
            id = "plugin.trails.kotlin.android.library"
            implementationClass = "org.mobilenativefoundation.trails.tooling.plugins.KotlinAndroidLibraryConventionPlugin"
        }

        register("kotlinMultiplatformPlugin") {
            id = "plugin.trails.kotlin.multiplatform"
            implementationClass = "org.mobilenativefoundation.trails.tooling.plugins.KotlinMultiplatformConventionPlugin"
        }
    }
}
