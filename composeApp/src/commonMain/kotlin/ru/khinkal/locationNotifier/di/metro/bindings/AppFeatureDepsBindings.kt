package ru.khinkal.locationNotifier.di.metro.bindings

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn
import kmp.core.deps.SystemDeps
import kmp.core.path.PathManager
import kotlinx.coroutines.CoroutineScope
import ru.khinkal.locationNotifier.feature.createGoal.presentation.di.deps.CreateGoalDeps
import ru.khinkal.locationNotifier.feature.createGoal.presentation.di.deps.CreateGoalDepsFactory
import ru.khinkal.locationNotifier.feature.main.data.storage.room.AppDataBase
import ru.khinkal.locationNotifier.feature.main.presentation.di.deps.MainDeps
import ru.khinkal.locationNotifier.feature.main.presentation.di.deps.MainDepsFactory

@BindingContainer
class AppFeatureDepsBindings {

    @Provides
    fun provideMainDeps(
        pathManager: PathManager,
        systemDeps: SystemDeps,
        coroutineScope: CoroutineScope,
        appDataBase: AppDataBase,
    ): MainDeps {
        return object : MainDeps {
            override val pathManager: PathManager = pathManager
            override val systemDeps: SystemDeps = systemDeps
            override val coroutineScope: CoroutineScope = coroutineScope
            override val appDataBase: AppDataBase = appDataBase
        }
    }

    @SingleIn(AppScope::class)
    @Provides
    fun provideMainDepsFactory(
        deps: Lazy<MainDeps>,
    ): MainDepsFactory {
        return MainDepsFactory { deps.value }
    }

    @Provides
    fun provideCreateGoalDeps(
        appDataBase: AppDataBase,
    ): CreateGoalDeps {
        return object : CreateGoalDeps {
            override val appDataBase: AppDataBase = appDataBase
        }
    }

    @SingleIn(AppScope::class)
    @Provides
    fun provideCreateGoalDepsFactory(
        deps: Lazy<CreateGoalDeps>,
    ): CreateGoalDepsFactory {
        return CreateGoalDepsFactory { deps.value }
    }
}
