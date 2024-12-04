package ru.khinkal.locationNotifier.convention_plugins.base.ext.multiplatform

import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun Project.kotlinMultiplatformConfig(block: KotlinMultiplatformExtension.() -> Unit) {
    extensions.findByType(KotlinMultiplatformExtension::class)
        ?.block()
        ?: error("Kotlin multiplatform was not been added")
}

val Project.kotlinMultiplatformExt: KotlinMultiplatformExtension get() {
    return extensions.findByType(KotlinMultiplatformExtension::class)
        ?: error("Kotlin multiplatform was not been added")
}