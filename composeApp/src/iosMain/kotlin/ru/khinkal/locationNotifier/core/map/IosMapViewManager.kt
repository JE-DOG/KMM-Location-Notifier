package ru.khinkal.locationNotifier.core.map

import ru.khinkal.locationNotifier.core.map.provider.IosMapViewProvider
import ru.khinkal.locationNotifier.core.map.provider.IosMapViewProviderFactory
import ru.khinkal.locationNotifier.core.map.components.MapController
import ru.khinkal.locationNotifier.core.map.components.MapViewHandler
import ru.khinkal.locationNotifier.core.map.components.MapViewMarkerLayer

class IosMapViewManager : MapViewManager() {

    private val provider: IosMapViewProvider by lazy {
        IosMapViewProviderFactory.INSTANCE.create()
    }

    val view get() = provider.viewController.view
    override val controller: MapController
        get() = provider.controller
    override val markerLayer: MapViewMarkerLayer
        get() = provider.markerLayer
    override val handler: MapViewHandler
        get() = provider.handler
}