package ru.khinkal.locationNotifier.feature.createGoal.presentation.di.deps

import ru.khinkal.locationNotifier.feature.main.data.storage.room.AppDataBase

interface CreateGoalDeps {

    val appDataBase: AppDataBase
}
