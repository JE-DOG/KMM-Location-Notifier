package ru.khinkal.locationNotifier.feature.locationList.presentation.vm.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.navigation.NavController
import ru.khinkal.locationNotifier.feature.locationList.data.LocationRepositoryImpl
import ru.khinkal.locationNotifier.feature.locationList.data.storage.LocationStorageDataSource
import ru.khinkal.locationNotifier.feature.locationList.data.storage.LocationStorageDataSourceImpl
import ru.khinkal.locationNotifier.feature.locationList.data.storage.room.getDatabase
import ru.khinkal.locationNotifier.feature.locationList.domain.LocationRepository
import ru.khinkal.locationNotifier.feature.locationList.presentation.vm.LocationListViewModel
import kotlin.reflect.KClass

class LocationListViewModelFactory(
    private val navController: NavController,
) : ViewModelProvider.Factory {

    private val database by lazy { getDatabase() }
    private val storageDataSource: LocationStorageDataSource by lazy {
        LocationStorageDataSourceImpl(database.locationDao())
    }
    private val repository: LocationRepository by lazy {
        LocationRepositoryImpl(storageDataSource)
    }

    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        return LocationListViewModel(
            navController = navController,
            locationRepository = repository,
        ) as T
    }
}