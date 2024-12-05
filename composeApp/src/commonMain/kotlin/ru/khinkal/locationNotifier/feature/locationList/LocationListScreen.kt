package ru.khinkal.locationNotifier.feature.locationList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import ru.khinkal.locationNotifier.feature.locationList.components.LocationListContent
import ru.khinkal.locationNotifier.feature.locationList.vm.LocationListState
import ru.khinkal.locationNotifier.feature.setGeoPoint.navigation.SetGeoPointScreen
import ru.khinkal.locationNotifier.feature.settings.navigation.SettingsScreen

@Composable
fun LocationListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    var state by remember {
        mutableStateOf(LocationListState(false))
    }

    LocationListContent(
        modifier = modifier,
        state = state,
        onSetGeoPointButtonClick = { navController.navigate(SetGeoPointScreen) },
        onSettingsButtonClick = { navController.navigate(SettingsScreen) },
        onChangeLocationButtonClick = { state = state.copy(isAdd = !state.isAdd) },
    )
}