package ru.khinkal.locationNotifier.core.location.utill

val Number.seconds: Long  get() {
    return toInt() * 1000L
}