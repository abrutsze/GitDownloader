plugins {
    id("project.android.library")
}

android {
    namespace = "com.github.network.impl"
}

dependencies {

    implementation(project(":core:network:api"))
    implementation(project(":core:network:entities"))
    implementation(project(":core:request"))
    implementation(libs.bundles.ktor)
}
