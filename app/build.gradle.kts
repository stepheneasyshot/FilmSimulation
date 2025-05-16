plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.stephen.filmsimulation"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.stephen.filmsimulation"
        minSdk = 33
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.3"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        val aaosSign = "stephen"
        register("aaos") {
            keyAlias = aaosSign
            keyPassword = aaosSign
            storeFile = file("./keystores/platform.jks")
            storePassword = aaosSign
        }
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("aaos")
            isMinifyEnabled = false
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            signingConfig = signingConfigs.getByName("aaos")
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    val appName = "FilmSimulation"
    android.applicationVariants.configureEach {
        val buildType = this.buildType.name
        outputs.all {
            if (this is com.android.build.gradle.internal.api.ApkVariantOutputImpl) {
                this.outputFileName = "${appName}_${defaultConfig.versionName}_${buildType}.apk"
            }
        }
    }
}

dependencies {
    implementation(fileTree("libs").include( "*.aar", "*.jar"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.stephenCarDev.redfinCommonHelper)
    implementation(libs.cymchad.baseRecyclerViewAdapterHelper)
    implementation(libs.glide)
    annotationProcessor(libs.glide.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}