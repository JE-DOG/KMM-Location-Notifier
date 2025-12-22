package ru.khinkal.locationNotifier.di.deps

import kmp.core.deps.SystemDeps
import kmp.core.path.PathManager
import kotlinx.coroutines.CoroutineScope
import ru.khinkal.locationNotifier.feature.createGoal.presentation.di.deps.CreateGoalDeps
import ru.khinkal.locationNotifier.feature.main.data.storage.room.AppDataBase
import ru.khinkal.locationNotifier.feature.main.presentation.di.deps.MainDeps

class DepsProvider(
    override val pathManager: PathManager,
    override val systemDeps: SystemDeps,
    override val coroutineScope: CoroutineScope,
    override val appDataBase: AppDataBase,
) : MainDeps,
    CreateGoalDeps
