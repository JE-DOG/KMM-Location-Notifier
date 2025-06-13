package ru.khinkal.locationNotifier.convention_plugins.base.ext.project

import org.gradle.api.Project
import java.util.Properties

private val Project.localPropertiesFile get() = project.rootProject.file("local.properties")

fun Project.getLocalProperty(name: String): String? {
    return if (localPropertiesFile.exists()) {
        val properties = Properties()
        localPropertiesFile.inputStream().buffered().use { input ->
            properties.load(input)
        }
        properties.getProperty(name)
    } else {
        localPropertiesFile.createNewFile()
        null
    }
        ?.replace("\"","")
}
