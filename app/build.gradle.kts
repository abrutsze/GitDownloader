plugins {
    id("project.android.application")
    id("project.android.application.compose")
}

android {
    namespace = "com.github.user"

    defaultConfig {
        applicationId = "com.github.user"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        val debug by getting {
            versionNameSuffix = ".debug"
        }
        val release by getting {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            // To publish on the Play store a private signing key is required, but to allow anyone
            // who clones the code to sign and run the release variant, use the debug signing key.
            // TODO: Abstract the signing configuration to a separate file to avoid hardcoding this.
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":core:network:api"))
    implementation(project(":core:network:impl"))
    implementation(project(":core:dispatchers:impl"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:datastore:impl"))
    implementation(project(":core:database"))
    implementation(project(":navigation"))
    implementation(project(":feature:repository"))
    implementation(project(":feature:users"))
    implementation(project(":feature:download"))

    implementation(libs.bundles.androidx)
    implementation(libs.android.core.splash)

    // TEST
    testImplementation(libs.bundles.test)
    debugImplementation(libs.compose.debug.test.manifest)
}