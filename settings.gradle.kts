pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "ProjectGitHubUser"
include(":app")
include(
    ":core:network:api",
    ":core:network:impl",
    ":core:network:entities",
    ":core:designsystem",
    ":core:dispatchers:api",
    ":core:dispatchers:impl",
    ":core:utils",
    ":core:datastore:api",
    ":core:datastore:impl",
    ":core:request",
    ":core:mvi",
    ":core:database"
)

include(":navigation")
include(":feature:repository")
include(":feature:users")
include(":feature:download")
