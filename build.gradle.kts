plugins {
    kotlin("jvm") version "1.5.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.projecttl.net/repository/maven-snapshots/")
    maven { url = uri("https://papermc.io/repo/repository/maven-public/") }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.17.1-R0.1-SNAPSHOT")
    implementation("xyz.minejs:common:1.0.0-SNAPSHOT")
    implementation("xyz.minejs:bukkit:1.0.0-SNAPSHOT")
    implementation("com.eclipsesource.j2v8:j2v8:6.2.1")
    implementation("com.eclipsesource.j2v8:j2v8_win32_x86_64:4.6.0")
    implementation("com.eclipsesource.j2v8:j2v8_linux_x86_64:4.6.0")
    implementation("com.eclipsesource.j2v8:j2v8_macosx_x86_64:4.6.0")
    implementation(kotlin("stdlib"))
}