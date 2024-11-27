import org.gradle.kotlin.dsl.kotlinOptions
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.android.dagger.hilt) apply true
    alias(libs.plugins.kapt)
    alias(libs.plugins.ksp)

}

android {
    namespace = "com.example.newarchstudy"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.newarchstudy"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    hilt {
        enableAggregatingTask = false
    }

    buildTypes {

        all {
            buildFeatures{
                buildConfig = true
            }

            buildConfigField("String", "BASE_URL", "\"https://api.currentsapi.services/v1/\"")
            buildConfigField("String", "API_KEY", "\"fnP9i4cdX3j2cCYhLA_SmsKTss04PDcBBlBLMVL3p9gDjrxN\"")

        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

        }

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
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

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "18" // Or your desired Java target version
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-XaddExports=jdk.compiler/com.sun.tools.javac.main=ALL-UNNAMED"
            )
        }
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.lifecycle.runtime.compose.android)
    implementation(libs.io.grpc.okhttp)
    implementation(libs.bumptech.glide)
    implementation(libs.retrofit2.gson)
    implementation(libs.retrofit2.retrofit)
    implementation(libs.android.dagger.hilt)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.android.dagger.hilt.compiler)
    implementation(libs.retrofit2.coroutine.adapter)
    implementation(libs.okhttp.logging.interceptor)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}