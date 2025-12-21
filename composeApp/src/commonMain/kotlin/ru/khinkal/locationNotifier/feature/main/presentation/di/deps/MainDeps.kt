package ru.khinkal.locationNotifier.feature.main.presentation.di.deps

import kmp.core.deps.SystemDeps
import kmp.core.path.PathManager
import kotlinx.coroutines.CoroutineScope
import ru.khinkal.locationNotifier.feature.main.data.storage.room.AppDataBase

interface MainDeps {

    val pathManager: PathManager
    val systemDeps: SystemDeps
    val coroutineScope: CoroutineScope
    val appDataBase: AppDataBase
}
