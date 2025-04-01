plugins {
    id("fabric-loom") version "1.7-SNAPSHOT" apply false
    id("legacy-looming") version "1.7-SNAPSHOT" apply false
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
            println(project.name)

            val target_version = when (project.name)
            {
                "1.7.10" -> JavaVersion.VERSION_1_8
                "1.8.9" -> JavaVersion.VERSION_1_8
                "1.9.4" -> JavaVersion.VERSION_1_8
                "1.10.2" -> JavaVersion.VERSION_1_8
                "1.11.2" -> JavaVersion.VERSION_1_8
                "1.12.2" -> JavaVersion.VERSION_1_8
                "1.13.2" -> JavaVersion.VERSION_1_8
                "1.14.4" -> JavaVersion.VERSION_1_8
                "1.15.2" -> JavaVersion.VERSION_1_8
                "1.16.5" -> JavaVersion.VERSION_1_8
                "1.17.1" -> JavaVersion.VERSION_17
                "1.18.2" -> JavaVersion.VERSION_17
                "1.19.4" -> JavaVersion.VERSION_17
                "1.20.4" -> JavaVersion.VERSION_17
                "1.21"   -> JavaVersion.VERSION_21
                "1.21.2"   -> JavaVersion.VERSION_21
                else -> JavaVersion.VERSION_1_8
            }.toString()
            sourceCompatibility = target_version
            targetCompatibility = target_version
        }

        withType(ProcessResources::class) {
            inputs.property("version", version)

            from(project.the<SourceSetContainer>()["main"].resources.srcDirs) {
                include("fabric.mod.json")
                expand("version" to version)
            }
        }

        named<Jar>("jar") {
            archiveFileName.set("realmsfix-${project.name}-${version}.jar")
        }

        withType<Copy> {
            duplicatesStrategy = DuplicatesStrategy.WARN
        }

        base {
            archivesName.set("realmsfix+${version}")
        }



        repositories {
            maven {
                // use the legacy URL for 1.13 and earlier, as well as base,
                //  new URL for 1.14 and later
                val split = project.name.split(".")
                url = if (split.size < 2 || split[1].toInt() <= 13) 
                        uri("https://repo.legacyfabric.net/maven/") 
                      else 
                        uri("https://maven.fabricmc.net/")
            }
        }

        license {
            header = rootProject.file("HEADER")
            exclude("**/*.json")
        }


        // suppress warnings (i'm lazy)
        withType(Javadoc::class) {
            options {
                (this as CoreJavadocOptions).addStringOption("Xdoclint:none", "-quiet")
            }
        }
    }
}

tasks.register<Jar>("mergedJar") {
    archiveBaseName.set("realmsfix")
    destinationDirectory.set(file("$buildDir/mergedJars"))

    dependsOn(subprojects.map { it.tasks.named("build") })

    // Get all JAR files from subprojects
    val jars = subprojects.map {
        it.tasks.named("remapJar").get().outputs.files.asPath
    }

    jars.map { jar ->
        from(zipTree(jar)) {
            eachFile {

            }
        }
        duplicatesStrategy = DuplicatesStrategy.WARN
    }
}
