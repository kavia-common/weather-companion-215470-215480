@file:Suppress("UnstableApiUsage")

plugins {
    // Android + Kotlin Android
    id("com.android.application") version "8.4.2"
    kotlin("android") version "1.9.24"
}

android {
    namespace = "org.example.app"

    compileSdk = 34

    defaultConfig {
        applicationId = "org.example.app"
        minSdk = 30
        targetSdk = 34

        versionCode = 1
        versionName = "0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables { useSupportLibrary = true }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            // default debug settings
        }
    }

    compileOptions {
        // Keep Java 17 for compatibility with settings.gradle.dcl defaults
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-Xjvm-default=all",
        )
    }

    buildFeatures {
        buildConfig = true
    }
    // By default, BASE_URL will be read by NetworkModule via system property,
    // but we also keep a fallback constant to help local runs when needed.
    defaultConfig {
        buildConfigField("String", "BASE_URL_DEFAULT", "\"http://10.0.2.2:3001/\"")
    }

    packaging {
        resources {
            excludes += setOf(
                "META-INF/AL2.0",
                "META-INF/LGPL2.1"
            )
        }
    }

    testOptions {
        // Allow no-tests to avoid CI failure on discovery differences
        unitTests.isReturnDefaultValues = true
        unitTests.isIncludeAndroidResources = false
    }
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    // Kotlin stdlib
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.24")

    // Core AndroidX
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")
    implementation("androidx.fragment:fragment-ktx:1.8.4")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.3")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")

    // Networking - Retrofit/Moshi/OkHttp
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.11.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")

    // Internal modules
    implementation(project(":utilities"))

    // Unit testing - JUnit Jupiter
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// Enable JUnit Platform for unit tests
tasks.withType<Test> {
    useJUnitPlatform()
}
