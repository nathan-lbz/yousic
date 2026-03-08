plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.yousiccode"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.yousiccode"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    // --- Réseau & API ---
    // Retrofit : Le moteur pour parler à l'API
    implementation("com.squareup.retrofit2:retrofit:2.11.0")

    // Converter-Gson : Pour transformer le JSON en objets Kotlin automatiquement
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // OkHttp : Pour voir ce qui se passe dans tes requêtes (optionnel mais recommandé)
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // --- Image (Crucial pour un réseau social musical !) ---
    // Coil : Pour charger les pochettes d'albums depuis une URL
    implementation("io.coil-kt:coil-compose:2.6.0")



}