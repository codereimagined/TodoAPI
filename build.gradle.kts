plugins {
    kotlin("jvm") version "2.0.20"
}

group = "com.sergeypetrunin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    val kliteVersion = "1.6.9"
    implementation("com.github.codeborne.klite:klite-server:$kliteVersion")
    implementation("com.github.codeborne.klite:klite-json:$kliteVersion")
    implementation("com.github.codeborne.klite:klite-jdbc:$kliteVersion")
    implementation("org.postgresql:postgresql:42.6.2")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
