package ru.khinkal.locationNotifier.convention_plugins.base.ext.project

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the

val Project.libs get() = the<LibrariesForLibs>()

val Project.javaVersion: JavaVersion
    get() = JavaVersion.toVersion(libs.versions.java.get().toInt())

