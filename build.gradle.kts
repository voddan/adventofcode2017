import org.gradle.script.lang.kotlin.extra
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.js.translate.context.Namer.kotlin

group = "com.epam.voddan"
version = "3rd-day"


buildscript {
    var kotlin_version: String by extra
    kotlin_version = "1.2.0"

    repositories {
        mavenCentral()
    }
    
    dependencies {
        classpath(kotlinModule("gradle-plugin", kotlin_version))
    }
}

apply {
    plugin("kotlin")
}


val kotlin_version: String by extra

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    compile(kotlinModule("stdlib-jdk8", kotlin_version))
    compile("com.marcinmoskala:DiscreteMathToolkit:1.0.3")

    testCompile(kotlinModule("test", kotlin_version))
    testCompile(kotlinModule("reflect", kotlin_version))  // to run separate tests
    testCompile("org.jetbrains.spek:spek-api:1.1.5")
    testRuntime("org.jetbrains.spek:spek-junit-platform-engine:1.1.5")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}

the<JavaPluginConvention>().sourceSets {
    "main" {
        java {
            srcDirs("src/main")
        }
    }
    "test" {
        java {
            srcDirs("src/test")
        }
    }
}
