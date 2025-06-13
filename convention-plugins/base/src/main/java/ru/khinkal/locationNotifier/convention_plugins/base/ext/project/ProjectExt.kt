package ru.khinkal.locationNotifier.convention_plugins.base.ext.project

import org.gradle.api.Project
import org.jetbrains.compose.internal.utils.getLocalProperty

fun Project.getLocalPropertyRawValue(name: String): String? {
    return getLocalProperty(name)?.replace("\"","")
}
