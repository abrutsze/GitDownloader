plugins {
    id("project.android.library")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.github.network.entities"
}

dependencies {
    // Serialization
    implementation(libs.kotlin.serialization)
}
