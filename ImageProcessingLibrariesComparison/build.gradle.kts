import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.openimaj.org")
    }
}

dependencies {
    implementation("com.soywiz.korlibs.korim:korim:3.3.1")
    implementation("net.coobird:thumbnailator:0.4.18")
    implementation("com.sksamuel.scrimage:scrimage-core:4.0.32")
    implementation("org.openimaj:image-processing:1.3.10")
    implementation("org.openpnp:opencv:4.5.5-1")

    implementation("org.boofcv:boofcv-core:0.41")
    implementation("org.boofcv:boofcv-kotlin:0.41")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}
