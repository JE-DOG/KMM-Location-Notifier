package kmp.core.path

import kmp.core.deps.SystemDeps
import kotlinx.cinterop.ExperimentalForeignApi
import okio.Path
import okio.Path.Companion.toPath
import platform.Foundation.NSApplicationSupportDirectory
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSSearchPathDirectory
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

actual fun createPathHelper(systemDeps: SystemDeps): PathManager {
    return IosPathManager()
}

class IosPathManager : PathManager {

    override val dataPath: Path
        get() = getDirectory(NSDocumentDirectory)

    override fun databasePath(name: String): Path {
        return getDirectory(NSApplicationSupportDirectory)
            .resolve(name)
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun getDirectory(directory: NSSearchPathDirectory): Path {
        val directoryUrl: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = directory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        return requireNotNull(directoryUrl).path!!.toPath()
    }
}
