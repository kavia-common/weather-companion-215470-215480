androidApplication {
    // Core Android module coordinates
    namespace = "org.example.app"

    // SDK configuration (kept in declarative form; defaults also set in settings.gradle.dcl)
    compileSdk = 34
    minSdk = 30
    targetSdk = 34

    // Enable View Binding using supported top-level property
    viewBinding = true

    // PUBLIC_INTERFACE
    // BuildConfig fields declared at the top level map
    buildConfigFields = mapOf(
        "BASE_URL" to "\"http://10.0.2.2:3001/\""
    )

    // Dependencies remain unchanged
    dependencies {
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

        // Keep sample modules if still referenced elsewhere
        implementation(project(":utilities"))
    }
}
