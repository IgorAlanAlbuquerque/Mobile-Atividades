plugins {
    alias(libs.plugins.android.application)
    id("org.jetbrains.kotlin.android" )
    id("com.google.gms.google-services" )
    id("org.jetbrains.compose" )
}
android {
    namespace = "com.igor.investidorapp"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.igor.investidorapp"
        minSdk = 26
        targetSdk = 35
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
                getDefaultProguardFile( "proguard-android-optimize.txt" ),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion .VERSION_1_8
        targetCompatibility = JavaVersion .VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
// Firebase
    implementation(platform( "com.google.firebase:firebase-bom:33.7.0" )) // Versão compatível com Kotlin 1.9.10
    implementation("com.google.firebase:firebase-analytics" )
// Jetpack Compose
    implementation(platform( "androidx.compose:compose-bom:2023.09.01" ))
    implementation("androidx.compose.ui:ui" )
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("com.google.firebase:firebase-database-ktx")
    implementation ("com.google.firebase:firebase-messaging-ktx:24.1.0")
    debugImplementation("androidx.compose.ui:ui-tooling")
// Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.7.2")
// Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.3")
    implementation("androidx.activity:activity-compose:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.09.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("androidx.compose.material:material-icons-extended")
}