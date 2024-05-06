plugins {
    id("project.android.library")
    id("project.android.library.compose")
    id("project.android.feature")
    id("kotlin-kapt")
}
android {
    namespace = "com.github.database"
}
dependencies {
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)
    implementation(libs.room.ktx)
}
