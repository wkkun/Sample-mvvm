import com.sun.source.util.Plugin

plugins {
    `kotlin-dsl`
}


java {
    // Up to Java 11 APIs are available through desugaring
    // https://developer.android.com/studio/write/java11-minimal-support-table
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin{
    plugins {
        register("androidApplication") {
            id = "convention.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidLibrary") {
            id = "convention.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidFeature") {
            id = "convention.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
    }
}