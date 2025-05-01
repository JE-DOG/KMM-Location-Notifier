package kmp.core

import android.content.Context
import kmp.core.deps.SystemDeps

class AndroidSystemDeps(
    val applicationContext: Context,
) : SystemDeps

fun SystemDeps.asAndroid(): AndroidSystemDeps =
    this as? AndroidSystemDeps
        ?: error(
            "Can't cast SystemModule to AndroidSystemModule. " +
                    "Are you provide AndroidSystemModule correctly for android?"
        )
