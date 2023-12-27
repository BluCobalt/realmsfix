import groovy.json.JsonOutput
import groovy.json.JsonSlurper

plugins {
    id("fabric-loom") version "1.4-SNAPSHOT" apply false
    id("legacy-looming") version "1.4-SNAPSHOT" apply false
    id("com.github.hierynomus.license-base") version "0.16.1"
    id("base")
}

subprojects {
    if (project.name == ":versions")
    {}

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

            // "if the project name is base, use java 17 (because base is always the newest)"
            // "if the project name is 1.17.1 or newer, use java 17"
            // "if it does not match any of the above, use java 8"

            sourceCompatibility = if (project.name == "versions" || project.name == "base" || ((project.name.replace(".", "").toInt() >= 1171)) && (project.name != "1.7.10")) "17" else "1.8"
            targetCompatibility = if (project.name == "versions" || project.name == "base" || ((project.name.replace(".", "").toInt() >= 1171)) && (project.name != "1.7.10")) "17" else "1.8"
            //                                          ^^^- that versions block is because gradle includes the "versions" folder as a project for some reason
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