plugins {
    `java-gradle-plugin`
    alias(libs.plugins.kotlin.jvm)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin.gradle)
    compileOnly(libs.android.gradlePlugin.api)
    implementation(gradleKotlinDsl())
}

gradlePlugin {
    plugins {
        create("BuildConfigPlugin") {
            id = "BuildConfigPlugin"
            implementationClass = "BuildConfigPlugin"
        }

        create("ReleasePlugin") {
            id = "ReleasePlugin"
            implementationClass = "ReleasePlugin"
        }
    }
}