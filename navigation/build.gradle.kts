plugins {
    id("project.android.library")
    id("project.android.library.compose")
}

android {
    namespace = "com.github.navigation"
}

dependencies {
    implementation(project(":feature:repository"))
    implementation(project(":feature:users"))
    implementation(project(":feature:download"))
}
