plugins {
    kotlin("jvm") version "2.0.0"
}

group = "io.github.hcblmnla"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation(project(":2-SAT"))
    implementation("com.github.UnitTestBot.kosat:kosat:f1c9848ac2")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}
