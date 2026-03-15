plugins {
    kotlin("jvm") version "2.3.10"
    kotlin("plugin.serialization") version "2.3.10"
    `maven-publish`
}

group = "com.ackagent"
version = providers.gradleProperty("version").orNull ?: "0.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api("com.squareup.retrofit2:retrofit:2.11.0")
    api("com.squareup.okhttp3:okhttp:4.12.0")
    api("com.squareup.okhttp3:logging-interceptor:4.12.0")
    api("com.squareup.retrofit2:converter-scalars:2.11.0")
    api("com.squareup.retrofit2:converter-kotlinx-serialization:2.11.0")
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.10.0")
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            artifactId = "api-kotlin"
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/ackagent/api")
            credentials {
                username = System.getenv("GITHUB_ACTOR") ?: ""
                password = System.getenv("GITHUB_TOKEN") ?: ""
            }
        }
        maven {
            name = "Pages"
            url = uri(layout.buildDirectory.dir("pages-maven"))
        }
    }
}
