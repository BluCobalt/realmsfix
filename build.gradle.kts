plugins {
    id("fabric-loom") version "1.4-SNAPSHOT" apply false
    id("legacy-looming") version "1.4-SNAPSHOT" apply false
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

            // if the project is 1.17.1 or newer use java 17 else use java 8
            @Suppress("LocalVariableName") // because gradle has a shit ton of variables that match any combination of "target" and "version" you could think of
            val target_version = if (project.name == "base" || project.name == "1.7.10" || (project.name.replace(".", "").toInt() < 1171)) "1.8" else "17"
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
                url = uri("https://repo.legacyfabric.net/repository/maven/")
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