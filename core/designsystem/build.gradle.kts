plugins {
    id("project.android.library")
    id("project.android.library.compose")
}
android {
    namespace = "com.github.designsystem"
}
dependencies {

    implementation(libs.android.core.ktx)
    implementation(libs.appcompat)


}
