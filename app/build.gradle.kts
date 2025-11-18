plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.e_waste_app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.e_waste_app"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation("com.squareup.picasso:picasso:2.71828")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")

    // Downgraded activity library to match AGP 8.8.1 and compileSdk 35
    implementation("androidx.activity:activity:1.10.1")
}
