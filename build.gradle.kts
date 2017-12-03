import org.gradle.script.lang.kotlin.extra
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.epam.voddan"
version = "1st-day"


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
