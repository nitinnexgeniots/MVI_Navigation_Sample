plugins {
    kotlin("kapt")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("org.jetbrains.kotlin.plugin.serialization")
}


android {
    namespace = "com.nitin.nitinlearn"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.nitin.nitinlearn"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        freeCompilerArgs = listOf(
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"

    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
// Allow references to generated code
    kapt {
        correctErrorTypes = true
    }


}



dependencies {
    implementation(project(":sdks:network"))
    implementation(project(":core"))
    implementation(project(":common"))
    implementation(project(":sdks:uiModule"))
    implementation(libs.hilt)
    implementation(libs.composeNavigationHilt)
    implementation(libs.appCompat)
    implementation(libs.volley)
    kapt(libs.hiltCompiler)
    implementation(libs.bundles.common)
    implementation(libs.coil)
    implementation(libs.coilGif)
    implementation(libs.bundles.common)
    androidTestImplementation(libs.composeUiJunit)
    debugImplementation(libs.composePreview)
    debugImplementation (libs.composeUiTestManifest)
    implementation(libs.lifecycleViewModel)
    androidTestImplementation(libs.testAndroidJunitExt)
    androidTestImplementation(libs.testAndroidEspressoCore)
    implementation(libs.systemUiController)
    implementation(libs.constraintlayout)
    implementation(libs.constraintlayout.compose)
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.media3:media3-common:1.2.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
}


