package kmp.core.path

import kmp.core.deps.SystemDeps
import okio.Path

expect fun createPathHelper(
    systemDeps: SystemDeps,
): PathManager

interface PathManager {

    val dataPath: Path

    fun databasePath(name: String): Path
}
