package ru.khinkal.locationNotifier.convention_plugins.base.ext.android

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinAndroidTarget
import ru.khinkal.locationNotifier.convention_plugins.base.ext.multiplatform.kotlinMultiplatformExt

private typealias AndroidExtensions = CommonExtension

private val Project.androidExtension: AndroidExtensions
    get() = extensions.findByType(ApplicationExtension::class)
        ?: extensions.findByType(LibraryExtension::class)
        ?: error(
            "\"Project.androidExtension\" value may be called only from android application" +
                    " or android library gradle script"
        )

fun Project.androidConfig(block: AndroidExtensions.() -> Unit): Unit = block(androidExtension)

fun Project.multiplatformAndroidConfig(block: KotlinAndroidTarget.() -> Unit) {
    kotlinMultiplatformExt.androidTarget(configure = block)
}
