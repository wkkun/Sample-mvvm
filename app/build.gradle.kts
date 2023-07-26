plugins {
    id("convention.android.application")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.quickdev"
    defaultConfig {
        applicationId = "com.example.quickdev"
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        signingConfig = signingConfigs.getByName("debug")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    lint{
        abortOnError =false
//        baseline = file("lint-baseline.xml")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}


dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(project(":BaseFunction"))

    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

}