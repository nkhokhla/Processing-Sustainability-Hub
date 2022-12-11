import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.github.johnrengelman.shadow") version "7.1.2"
    application
    id("org.openjfx.javafxplugin") version "0.0.13"
    id("org.beryx.jlink") version "2.22.1"
    kotlin("jvm") version "1.6.20"
}

val compileKotlin: KotlinCompile by tasks
val compileJava: JavaCompile by tasks
compileJava.destinationDir = compileKotlin.destinationDir

repositories {
    mavenCentral()
}

javafx {
    modules = listOf("javafx.controls", "javafx.fxml", "javafx.web")
}

jlink{
    launcher {
        name = "hello"
    }
    imageZip.set(project.file("${project.buildDir}/image-zip/hello-image.zip"))
}

dependencies {
    implementation(fileTree("libs") { include("*.jar") })
    implementation(kotlin("stdlib-jdk8"))
}


application {
    mainClass.set("org.example.MainKt")
}
repositories {
    mavenCentral()
}

tasks {
    withType<JavaCompile> {
        options.release.set(17)
        options.encoding = "UTF-8"
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }
}