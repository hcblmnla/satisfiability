plugins {
    kotlin("jvm") version "2.0.0"
}

group = "io.github.hcblmnla"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ksmt:ksmt-core:0.5.23")
    implementation("io.ksmt:ksmt-z3:0.5.23")
    implementation("io.ksmt:ksmt-bitwuzla:0.5.23")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}
