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

    // It's good practice to enable viewBinding to avoid findViewById
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Core Android libraries
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")
    implementation("androidx.activity:activity:1.10.1")

    // Picasso for image loading
    implementation("com.squareup.picasso:picasso:2.8") // I've updated this to the latest version

    // RecyclerView for displaying lists
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // CardView for the item_card layout
    implementation("androidx.cardview:cardview:1.0.0")
}
