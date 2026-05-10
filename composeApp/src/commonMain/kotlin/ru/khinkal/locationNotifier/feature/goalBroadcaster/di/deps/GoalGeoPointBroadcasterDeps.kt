package ru.khinkal.locationNotifier.feature.goalBroadcaster.di.deps

import kmp.core.deps.SystemDeps
import kotlinx.coroutines.CoroutineScope

interface GoalGeoPointBroadcasterDeps {

    val systemDeps: SystemDeps
    val coroutineScope: CoroutineScope
}
