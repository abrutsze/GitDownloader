plugins {
    id("project.android.library")
    id("project.android.library.compose")
    id("project.android.feature")
}
android {
    namespace = "com.github.feature.detail"
}
dependencies {

    implementation(libs.compose.paging)
}
