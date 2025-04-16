plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)

    alias(libs.plugins.kapt)
//    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
//    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.framework"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(project(":domain"))
    implementation(project(":data"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
//    androidTestImplementation(project(":domain"))
//    androidTestImplementation(project(":data"))
//    androidTestImplementation(project(":app"))
//    ksp(libs.room.compiler)

    // Hilt
    implementation(libs.hilt.android)
//    ksp(libs.hilt.compiler)

    implementation(libs.gson)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)





//    kapt(libs.room.compiler)  // Usa kapt en lugar de ksp para Room
    kapt(libs.moshi.kapt)

  kapt(libs.hilt.compiler)  // Usa kapt para Hilt


    // Network
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.gson)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}

kapt {
    correctErrorTypes = true
}