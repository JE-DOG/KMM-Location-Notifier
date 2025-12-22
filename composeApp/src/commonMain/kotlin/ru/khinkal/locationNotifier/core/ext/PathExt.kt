package ru.khinkal.locationNotifier.core.ext

import okio.Path

val Path.absolutePath: String get() = toString()
