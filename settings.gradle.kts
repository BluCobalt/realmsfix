pluginManagement {
    repositories {
        maven {
            url = uri("https://repo.legacyfabric.net/maven/")
        }
        gradlePluginPortal()
    }
}

rootProject.name = "realmsfix"

include(":base")


file("versions").listFiles()?.forEach {
    if (it.isDirectory) {
        include(":versions/${it.name}")
        settings.project(":versions/${it.name}").name = it.name
    }
}