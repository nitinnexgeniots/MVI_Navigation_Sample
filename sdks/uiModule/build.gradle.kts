@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlin)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.nitin.uimodule"
    compileSdk = 34

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"

    }
    // Allow references to generated code
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appCompat)
    implementation(libs.material)
    implementation(project(":core"))
    implementation(project(":sdks:network"))
    implementation(project(":common"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.testAndroidJunitExt)
    androidTestImplementation(libs.testAndroidEspressoCore)
    implementation(libs.bundles.common)
    implementation(libs.hilt)
    kapt(libs.hiltCompiler)
    implementation(libs.material)
    implementation(libs.composeMaterial3)
    implementation(libs.lifecycleViewModel)
    implementation(libs.composeNavigation)
    implementation(libs.composeNavigationHilt)
    implementation(libs.coil)
    implementation(libs.coilGif)



}