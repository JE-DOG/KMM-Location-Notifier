package ru.khinkal.locationNotifier.core.location.utill

import androidx.navigation.NavController

fun<T> NavController.observeResult(key: String,initialValue: T) = currentBackStackEntry
    ?.savedStateHandle
    ?.getStateFlow(key,initialValue)

fun<T> NavController.returnResult(key: String,result: T) = previousBackStackEntry
    ?.savedStateHandle
    ?.set(key, result)

fun<T> NavController.removeResult(key: String) = currentBackStackEntry
    ?.savedStateHandle
    ?.remove<T>(key)
