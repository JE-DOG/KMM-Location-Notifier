package ru.khinkal.locationNotifier.di.deps

import kmp.core.deps.SystemDeps
import kmp.core.path.PathManager
import ru.khinkal.locationNotifier.feature.main.presentation.di.deps.MainDeps
import ru.khinkal.locationNotifier.feature.settings.presentation.di.deps.SettingsDeps

class DepsProvider(
    override val pathManager: PathManager,
    override val systemDeps: SystemDeps,
) : MainDeps,
    SettingsDeps
