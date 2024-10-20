
plugins {
    application
    kotlin("jvm")
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.sqldelight)
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

dependencies {

    implementation(projects.backend.models)

    implementation(libs.kotlinx.datetime)
    implementation(libs.ktor.server.core.jvm)
    implementation(libs.ktor.server.content.negotiation.jvm)
    implementation(libs.ktor.serialization.kotlinx.json.jvm)
    implementation(libs.ktor.server.netty.jvm)
    implementation(libs.ktor.server.config.yaml)
    implementation(libs.jdbc.driver)
    implementation(libs.postgresql)
    implementation(libs.hikaricp)
}

sqldelight {
    databases {
        create("TrailsDatabase") {
            val sqlDelightVersion = libs.versions.sqldelight.get()
            packageName.set("org.mobilenativefoundation.trails.backend.server")
            dialect("app.cash.sqldelight:postgresql-dialect:$sqlDelightVersion")
        }
    }
}

