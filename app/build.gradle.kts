plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.example.tipukutta"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        targetSdk = 34


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        applicationId = "com.example.tipukutta"
        versionCode = 1
        versionName = "1.0"
//        resValue("string", "app_name", "\"SAMADHANA\"")
//        buildConfigField("String", "AppName", "\"SAMADHANA\"")
//        buildConfigField("String", "APP_ID", "\"community\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            isDebuggable = false
            buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.typicode.com\"")
        }
        debug {
            applicationIdSuffix = ".debug"
            isDebuggable = true
            buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.typicode.com\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.10.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.retrofit2:converter-gson:2.10.0")
    //dagger hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    //view model
    val lifecycle_version = "2.7.0"
    val arch_version = "2.2.0"
    // ViewModel
    // Kotlin
    val activity_version = "1.8.2"
    implementation("androidx.activity:activity-ktx:$activity_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel:$lifecycle_version")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata:$lifecycle_version")
    // Annotation processor
    annotationProcessor("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")
    //room dependency
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$room_version")
    // optional - Paging 3 Integration
    implementation("androidx.room:room-paging:$room_version")
    // To use Kotlin Symbol Processing (KSP)
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1-Beta")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1-Beta")

    val paging_version = "3.2.1"
    implementation("androidx.paging:paging-runtime:$paging_version")
}