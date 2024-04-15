plugins {
    kotlin("kapt")
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
   alias(libs.plugins.apolloGraphQL)
}

android {
    namespace = "com.nitin.network"
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
    // Allow references to generated code
    kapt {
        correctErrorTypes = true
    }

}
dependencies {
    implementation(project(":core"))
    implementation("androidx.core:core-ktx:1.12.0")
    testImplementation("junit:junit:4.13.2")
    implementation(libs.bundles.common)
    implementation(libs.appCompat)
    implementation(libs.material)
    implementation(libs.composeMaterial3)
    implementation(libs.hilt)
    kapt(libs.hiltCompiler)
    androidTestImplementation(libs.testAndroidJunitExt)
    androidTestImplementation(libs.testAndroidEspressoCore)
    implementation (libs.retrofit)
    implementation (libs.gsonConverter)
    implementation(libs.simpleXmlConverter)
    implementation(libs.kotlinSerializationConverter)
    implementation(libs.ok2Curl)
    implementation(libs.timber)
    implementation(libs.okHttpLoggingInterceptor)
    implementation(libs.okHttpSSE)
    api(libs.apolloGraphQL)
    implementation(libs.apolloGraphQLCache)
    implementation(libs.apolloGraphQLCacheDB)
    implementation(libs.apolloGraphQLApi)


}
apollo {
    service("Service"){
        packageName.set("com.nitin.network")
        generateFragmentImplementations.set(true)
        generateOperationOutput.set(true)
    }
}


