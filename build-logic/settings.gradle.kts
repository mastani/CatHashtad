rootProject.name = "build-logic"

pluginManagement {
    buildscript {
        repositories {
            google()
            mavenCentral()
        }
    }

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

include(":plugins")