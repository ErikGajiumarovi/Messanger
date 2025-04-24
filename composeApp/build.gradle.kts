import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("app.cash.sqldelight") version "2.0.2"
}


sqldelight {
    databases {
        create("MessengerDatabase") {
            packageName.set("com.erikproject.database")
        }
    }
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
    }
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3) // Используем Material 3
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)

            // JWT обработка
            implementation(libs.napier)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)

            // Storage
            implementation(libs.okio)

            // Network
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.websockets)
            implementation(libs.ktor.client.json)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.utils)

            // coroutines
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.serialization.kotlinx.json)


            // SQLDelight
            implementation("org.slf4j:slf4j-api:1.7.32")
            implementation("ch.qos.logback:logback-classic:1.4.12")
            implementation("app.cash.sqldelight:coroutines-extensions:2.0.0")

            // Date & Time
            implementation(libs.kotlinx.datetime)

        }

        // Зависимости для Android
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            // SQL
            implementation(libs.android.driver)

            // Network
            implementation(libs.ktor.client.android)
        }

        // Зависимости для Desktop
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)


            // Network
            implementation(libs.ktor.client.cio)
            implementation(libs.ktor.client.java)

            // SQL
            implementation(libs.sqldelight.sqlite.driver)
        }

        // Зависимости для iOS
        iosMain.dependencies {
            implementation(libs.ui.util)
            implementation(libs.foundation)
            implementation(compose.ui)
            implementation(compose.foundation)
            implementation(compose.runtime)

            // Network
            implementation(libs.ktor.client.darwin)
            // SQL
            implementation(libs.native.driver)
        }
    }
}

android {
    namespace = "com.erikproject.messanger"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.erikproject.messanger"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

compose.desktop {
    application {
        mainClass = "com.erikproject.messanger.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.erikproject.messanger"
            packageVersion = "1.0.0"
        }
    }
}