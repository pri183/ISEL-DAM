plugins {
    //kotlin("jvm") version "1.9.24"
    kotlin("jvm") version "2.3.0" //Adicionado por incompatibilidade de funcoes
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    //jvmToolchain(8)
    jvmToolchain(23)
}

application {
    mainClass.set("MainKt")
}