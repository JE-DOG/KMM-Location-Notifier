package ru.khinkal.locationNotifier.feature.setGeoPoint.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.khinkal.locationNotifier.feature.setGeoPoint.SetGeoPointScreen

fun NavGraphBuilder.setGeoPoint() {
    composable<SetGeoPointScreen> {
        SetGeoPointScreen()
    }
}