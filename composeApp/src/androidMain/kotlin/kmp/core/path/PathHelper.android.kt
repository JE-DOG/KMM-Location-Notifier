package kmp.core.path

import kmp.core.AndroidSystemDeps
import kmp.core.deps.SystemDeps
import kmp.core.asAndroid
import okio.Path
import okio.Path.Companion.toOkioPath

actual fun createPathHelper(systemDeps: SystemDeps): PathManager {
    return AndroidPathManager(systemDeps.asAndroid())
}

class AndroidPathManager(
    private val systemDeps: AndroidSystemDeps,
) : PathManager {

    private val applicationContext get() = systemDeps.applicationContext

    override val dataPath: Path
        get() = applicationContext.filesDir.toOkioPath()

    override fun databasePath(name: String): Path {
        return applicationContext
            .getDatabasePath(name)
            .toOkioPath()
    }
}
