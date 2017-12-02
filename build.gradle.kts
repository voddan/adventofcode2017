import org.gradle.script.lang.kotlin.extra
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.epam.voddan"
version = "2days"


buildscript {
    var kotlin_version: String by extra
    kotlin_version = "1.2.0"

    repositories {
        mavenCentral()
    }
    
    dependencies {
        classpath(kotlinModule("gradle-plugin", kotlin_version))
//        classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.0")
    }
    
}

apply {
    plugin("kotlin")
//    plugin("org.junit.platform.gradle.plugin")
}


val kotlin_version: String by extra

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    compile(kotlinModule("stdlib-jdk8", kotlin_version))
//    testCompile("junit:junit:4.12")
    testCompile(kotlinModule("test", kotlin_version))
    testCompile(kotlinModule("test-junit", kotlin_version))
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
