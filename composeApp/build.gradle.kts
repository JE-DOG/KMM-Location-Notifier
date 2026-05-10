import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import ru.khinkal.locationNotifier.convention_plugins.base.ext.project.getLocalProperty

plugins {
    with(libs) {
        with(plugins) {
            alias(multiplatform)
            alias(compose.compiler)
            alias(compose)
            alias(android.application)
            alias(buildConfig)
            alias(kotlinx.serialization)
            alias(room)
            alias(ksp)
            alias(metro)

            with(convention) {
                alias(android.base)
            }
        }
    }
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    )
        .forEach {
            it.binaries.framework {
                baseName = "composeApp"
                isStatic = true
            }
        }

    sourceSets {
        commonMain.dependencies {
            with(compose) {
                implementation(libs.compose.navigation3.ui)
                implementation(runtime)
                implementation(foundation)
                implementation(material3)

                implementation(components.resources)
            }

            with(libs) {
                with(kotlinx) {
                    implementation(serialization.json)
                    implementation(coroutines.core)
                }
                implementation(metro.runtime)
                implementation(libs.lifecycle.runtime.compose)
                implementation(libs.lifecycle.viewmodel.compose)
                implementation(libs.lifecycle.viewmodel.nav3)
                with(androidx) {
                    implementation(room.runtime)
                    implementation(sqlite.bundled)
                }

                implementation(datastore.preferences)
                implementation(permissions)
            }
        }
    }
}

android {
    namespace = "ru.khinkal.locationNotifier"

    defaultConfig {
        targetSdk = libs.versions.android.sdk.target.get().toInt()

        applicationId = "ru.khinkal.locationNotifier"
        versionCode = 67
        versionName = "6.7.69"
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
        }

        release {
            isMinifyEnabled = true
            isShrinkResources = true
//            signingConfig = signingConfigs.getByName("release")
        }
    }

    lint {
        // Without it crush on release build
        disable.add("NullSafeMutableLiveData")
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}

//https://developer.android.com/develop/ui/compose/testing#setup
dependencies {
    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosX64", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)

    with(libs) {
        with(androidx) {
            implementation(activity.compose)
            implementation(runtime.android)
            implementation(lifecycle)
            implementation(ktx)
        }

        implementation(gms.location)
        implementation(android.map)
    }

    implementation(compose.uiTooling)
    implementation(compose.preview)
}

buildConfig {
    // BuildConfig configuration here.
    // https://github.com/gmazzo/gradle-buildconfig-plugin#usage-in-kts

    val mapStyleUrl = getLocalProperty("MAP_STYLE_URL")
    buildConfigField(
        type = "String",
        name = "MAP_STYLE_URL",
        value = requireNotNull(mapStyleUrl),
    )
}
