plugins {
    id("fabric-loom") version "1.1-SNAPSHOT" apply false
    id("com.github.hierynomus.license-base") version "0.16.1"
    id("base")
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "base")
    apply(plugin = "com.github.hierynomus.license-base")

    configure<JavaPluginExtension> {
        withSourcesJar()
        withJavadocJar()
    }

    tasks {
        withType(JavaCompile::class) {
            options.encoding = "UTF-8"
            sourceCompatibility = "8"
            targetCompatibility = "8"
        }

        withType(ProcessResources::class) {
            inputs.property("version", version)

            from(project.the<SourceSetContainer>()["main"].resources.srcDirs) {
                include("fabric.mod.json")
                expand("version" to version)
            }
        }

        named<Jar>("jar") {
            archiveBaseName.set("realmsfix+${expandVersion(project.name)}")
        }


        withType<Copy> {
            duplicatesStrategy = DuplicatesStrategy.INCLUDE
        }

        base {
            archivesName.set("realmsfix+${expandVersion(project.name)}")
        }
    }

    repositories {
        maven {
            url = uri("https://repo.legacyfabric.net/repository/maven/")
        }
    }

    license {
        header = rootProject.file("HEADER")
        exclude("**/*.json")
    }

    val copyArtifacts = tasks.register<Copy>("copyArtifacts") {
        from("$buildDir/libs")
        into("${rootProject}/artifacts")
    }

    tasks.named("build") {
        dependsOn(copyArtifacts)
    }
}

fun expandVersion(version: String): String {
    var x = "dev"
    when (version) {
        "1.7.10" -> { x = "1.7.10-1.12.2" }
        "1.13.2" -> { x = "1.13.2" }
        "1.14.4" -> { x = "1.14.4-1.15.2" }
        "1.16.5" -> { x = "1.16.5-1.18.2" }
    }
    println(x)
    return x
}
