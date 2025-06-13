import org.jetbrains.compose.ExperimentalComposeLibrary
import ru.khinkal.locationNotifier.convention_plugins.base.ext.project.getLocalPropertyRawValue

// TODO: LN-21 Clean and update dependencies(Tech-debt iteration 5)
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

            with(convention) {
                alias(android.base)
            }
        }
    }
}

kotlin {
    val iosPlatforms = listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    )
    iosPlatforms.forEach {
        it.binaries.framework {
            baseName = "composeApp"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            with(compose) {
                implementation(libs.compose.navigation)
                implementation(runtime)
                implementation(foundation)
                implementation(material3)

                with(components) {
                    implementation(resources)
                    implementation(uiToolingPreview)
                }
            }
            with(libs) {
                with(kotlinx) {
                    implementation(serialization.json)
                    implementation(datetime)
                    implementation(coroutines.core)
                }
                with(androidx) {
                    implementation(room.runtime)
                    implementation(sqlite.bundled)
                }

                implementation(coil)
                implementation(coil.network.ktor)
                implementation(ktor.core)
                implementation(composeIcons.featherIcons)
                implementation(datastore.preferences)
            }
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
            implementation(libs.kotlinx.coroutines.test)
        }

        androidMain.dependencies {
            with(libs) {
                with(android) {
                    implementation(map)
                    implementation(lifecycle)
                    implementation(ktx)
                }
                with(gms) {
                    implementation(location)
                }
                implementation(androidx.activityCompose)
                implementation(kotlinx.coroutines.android)
                implementation(ktor.client.okhttp)
            }
            implementation(compose.uiTooling)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}

android {
    namespace = "ru.khinkal.locationNotifier"

    defaultConfig {
        targetSdk = libs.versions.android.sdk.target.get().toInt()

        applicationId = "ru.khinkal.locationNotifier"
        versionCode = 1
        versionName = "0.0.1"
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}

//https://developer.android.com/develop/ui/compose/testing#setup
dependencies {
    implementation(libs.androidx.runtime.android)
    androidTestImplementation(libs.androidx.uitest.junit4)
    debugImplementation(libs.androidx.uitest.testManifest)
    //temporary fix: https://youtrack.jetbrains.com/issue/CMP-5864
    androidTestImplementation("androidx.test:monitor") {
        version { strictly("1.6.1") }
    }

    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosX64", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
}

buildConfig {
    // BuildConfig configuration here.
    // https://github.com/gmazzo/gradle-buildconfig-plugin#usage-in-kts

    val mapStyleUrl = getLocalPropertyRawValue("MAP_STYLE_URL")
    buildConfigField(
        type = "String",
        name = "MAP_STYLE_URL",
        value = requireNotNull(mapStyleUrl),
    )
}
