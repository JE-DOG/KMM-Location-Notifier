package ru.khinkal.locationNotifier.feature.setGeoPoint.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kmm_location_notifier.composeapp.generated.resources.Res
import kmm_location_notifier.composeapp.generated.resources.set_geo_point_screen_title
import org.jetbrains.compose.resources.stringResource
import ru.khinkal.locationNotifier.shared.components.topBar.TransparentBaseTopBar

@Composable
fun SetGeoPointTopBar(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
) {
    TransparentBaseTopBar(
        modifier = modifier,
        title = stringResource(Res.string.set_geo_point_screen_title),
        onBackClicked = onBackClicked,
    )
}
