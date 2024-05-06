plugins {
    id("project.android.library")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.github.network.api"
}

dependencies {
    implementation(project(":core:network:entities"))
    implementation(project(":core:request"))
    // Serialization
    implementation(libs.kotlin.serialization)
}
