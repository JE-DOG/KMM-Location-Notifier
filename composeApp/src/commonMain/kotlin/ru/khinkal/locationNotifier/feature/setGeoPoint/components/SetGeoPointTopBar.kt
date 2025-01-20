package ru.khinkal.locationNotifier.feature.setGeoPoint.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.khinkal.locationNotifier.shared.components.topBar.TransparentBaseTopBar

@Composable
fun SetGeoPointTopBar(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
) {
    TransparentBaseTopBar(
        modifier = modifier,
        title = "Set geo point", // TODO-SET_GEO: Make resources for screen
        onBackClicked = onBackClicked,
    )
}