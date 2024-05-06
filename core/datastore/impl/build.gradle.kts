plugins {
    id("project.android.library")
    kotlin("plugin.serialization")
}
android {
    namespace = "com.github.datastore.impl"
}
dependencies {
    implementation(project(":core:datastore:api"))
    implementation(project(":core:utils"))
    implementation(libs.kotlin.serialization)
}
