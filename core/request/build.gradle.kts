plugins {
    id("project.jvm.library")
    kotlin("plugin.serialization")
}
dependencies {
    implementation(libs.kotlin.serialization)
}
