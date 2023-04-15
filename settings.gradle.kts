pluginManagement {
    repositories {
        maven {
            url = uri("https://repo.legacyfabric.net/repository/maven/")
        }
        gradlePluginPortal()
    }
}

rootProject.name = "realmsfix"
include("1.7.10")
include("1.13.2")
include("1.14.4")
include("1.16.5")
////include(":versions:1.19.4-dev")
////include(":versions:1.18.2-dev")
//include(":versions:1.17.1-dev")
//include(":versions:1.16.5-dev")
//include(":versions:1.15.2-dev")
//include(":versions:1.14.4-dev")
//include(":versions:1.13.2-dev")
//include(":versions:1.12.2-dev")
//include(":versions:1.11.2-dev")
//include(":versions:1.10.2-dev")
//include(":versions:1.9.4-dev")
//include(":versions:1.8.9-dev")
//include(":versions:1.7.10-dev")