plugins {
    kotlin("kapt")
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.nitin.core"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"

    }

    buildFeatures {
        compose =true
    }
    // Allow references to generated code
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
   // implementation(libs.bundles.common)
    implementation(libs.timber)
    implementation(libs.hilt)
    implementation(libs.composeNavigationHilt)
    kapt(libs.hiltCompiler)
    implementation(libs.appCompat)
    implementation(libs.material)
    androidTestImplementation(libs.testAndroidJunitExt)
    androidTestImplementation(libs.testAndroidEspressoCore)
    implementation(libs.lifecycleViewModel)
    implementation(libs.composeNavigation)
    implementation("androidx.core:core-ktx:1.12.0")
    testImplementation("junit:junit:4.13.2")


}