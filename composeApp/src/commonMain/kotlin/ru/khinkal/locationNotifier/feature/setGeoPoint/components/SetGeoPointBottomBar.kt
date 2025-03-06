package ru.khinkal.locationNotifier.feature.setGeoPoint.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kmm_location_notifier.composeapp.generated.resources.Res
import kmm_location_notifier.composeapp.generated.resources.set_geo_point_confirm_button_text
import org.jetbrains.compose.resources.stringResource

@Composable
fun SetGeoPointBottomBar(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier
            .fillMaxWidth(),
        onClick = onClick,
    ) {
        Text(
            text = stringResource(Res.string.set_geo_point_confirm_button_text),
        )
    }
}
