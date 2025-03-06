package ru.khinkal.locationNotifier.core.ext.location

val Number.seconds: Long  get() {
    return toInt() * 1000L
}