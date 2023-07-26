import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

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
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
//    }
}

kapt {
    correctErrorTypes = true
}
// Allow references to generated code


dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
    implementation(project(":BaseFunction"))

    //hilt
    implementation(libs.hilt)
    kapt(libs.hiltKapt)
}