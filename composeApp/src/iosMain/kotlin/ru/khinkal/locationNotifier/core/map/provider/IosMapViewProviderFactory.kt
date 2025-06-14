package ru.khinkal.locationNotifier.core.map.provider

import kotlin.properties.Delegates.notNull

interface IosMapViewProviderFactory {

    fun create(): IosMapViewProvider

    companion object {

        var INSTANCE: IosMapViewProviderFactory by notNull()
    }
}
