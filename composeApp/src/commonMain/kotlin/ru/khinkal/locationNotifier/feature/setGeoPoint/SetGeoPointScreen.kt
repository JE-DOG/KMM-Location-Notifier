package ru.khinkal.locationNotifier.feature.setGeoPoint

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.khinkal.locationNotifier.core.ext.location.returnResult
import ru.khinkal.locationNotifier.feature.setGeoPoint.components.SetGeoPointContent
import ru.khinkal.locationNotifier.shared.navigation.ResultKeys

@Composable
fun SetGeoPointScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    SetGeoPointContent(
        modifier = modifier,
        onBackClicked = navController::popBackStack,
        onConfirm = { baseGeoPoint ->
            val baseGeoPointJson = Json.encodeToString(baseGeoPoint)
            navController.returnResult(
                key = ResultKeys.GEO_POINT_SELECTED,
                result = baseGeoPointJson,
            )
            navController.popBackStack()
        },
    )
}
