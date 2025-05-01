package ru.khinkal.locationNotifier.feature.main.presentation.di.deps

import kmp.core.deps.SystemDeps
import kmp.core.path.PathManager

interface MainDeps {

    val pathManager: PathManager
    val systemDeps: SystemDeps
}
