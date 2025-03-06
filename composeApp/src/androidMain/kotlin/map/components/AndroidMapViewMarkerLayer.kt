package map.components

import io.openmobilemaps.mapscore.graphics.BitmapTextureHolder
import io.openmobilemaps.mapscore.map.view.MapView
import io.openmobilemaps.mapscore.shared.graphics.common.Vec2F
import io.openmobilemaps.mapscore.shared.graphics.shader.BlendMode
import io.openmobilemaps.mapscore.shared.map.layers.icon.IconFactory
import io.openmobilemaps.mapscore.shared.map.layers.icon.IconLayerInterface
import io.openmobilemaps.mapscore.shared.map.layers.icon.IconType
import map.ext.toCoord
import map.model.MapViewMarker
import ru.khinkal.locationNotifier.R

class AndroidMapViewMarkerLayer(
    private val mapView: MapView,
): MapViewMarkerLayer {

    private val context get() = mapView.context
    private val iconLayer = IconLayerInterface.create()

    init {
        addIconLayer()
    }

    private fun addIconLayer() {
        mapView.addLayer(iconLayer.asLayerInterface())
    }

    override fun addMarker(marker: MapViewMarker) {
        val drawable = context.getDrawable(R.drawable.ic_my_location) ?: return
        val texture = BitmapTextureHolder(drawable)
        val coord = marker.baseGeoPoint.toCoord()
        val markerIcon = IconFactory.createIconWithAnchor(
            identifier = marker.id,
            coordinate = coord,
            texture = texture,
            iconSize = Vec2F(
                x = (texture.getImageWidth() / 4).toFloat(),
                y = (texture.getImageHeight() / 4).toFloat(),
            ),
            scaleType = IconType.SCALE_INVARIANT,
            blendMode = BlendMode.NORMAL,
            iconAnchor = Vec2F(0.5f, 1.0f),
        )

        iconLayer.removeIdentifier(marker.id)
        iconLayer.add(markerIcon)
    }
}
