package ru.khinkal.locationNotifier.feature.setGeoPoint

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.json.Json
import ru.khinkal.locationNotifier.feature.setGeoPoint.components.SetGeoPointContent
import ru.khinkal.locationNotifier.navigation.NavController
import ru.khinkal.locationNotifier.shared.navigation.NavResultStore
import ru.khinkal.locationNotifier.shared.navigation.ResultKeys

@Composable
fun SetGeoPointScreen(
    navController: NavController,
    navResultStore: NavResultStore,
    modifier: Modifier = Modifier,
) {
    SetGeoPointContent(
        modifier = modifier,
        onBackClicked = navController::popBackStack,
        onConfirm = { baseGeoPoint ->
            val baseGeoPointJson = Json.encodeToString(baseGeoPoint)
            navResultStore.setResult(
                screenKey = ResultKeys.GEO_POINT_SELECTED,
                result = baseGeoPointJson,
            )
            navController.popBackStack()
        },
    )
}
