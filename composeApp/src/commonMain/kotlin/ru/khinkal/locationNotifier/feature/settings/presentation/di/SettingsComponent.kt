package ru.khinkal.locationNotifier.feature.settings.presentation.di

import ru.khinkal.locationNotifier.feature.settings.data.di.SettingsDataModule
import ru.khinkal.locationNotifier.feature.settings.domain.SettingsManager
import ru.khinkal.locationNotifier.feature.settings.presentation.di.deps.SettingsDeps
import ru.khinkal.locationNotifier.feature.settings.presentation.di.deps.SettingsDepsFactory

class SettingsComponent(
    private val deps: SettingsDeps = SettingsDepsFactory.INSTANCE.create(),
) {

    private val settingsDataModule by lazy { SettingsDataModule(deps.pathManager) }

    val settingsManager: SettingsManager by lazy {
        settingsDataModule.provideSettingsRepository()
    }
}
