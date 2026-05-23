plugins {
    kotlin("jvm") version "2.3.0" // adicionado por incompatibilidade de versoes
}
group = "org.example"
version = "1.0-SNAPSHOT"
repositories {
    mavenCentral()
}
dependencies {
    testImplementation(kotlin("test"))
    implementation(kotlin("stdlib"))
}
tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}