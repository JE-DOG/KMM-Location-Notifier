package ru.khinkal.locationNotifier.core.utill

import android.content.Context
import ru.khinkal.locationNotifier.R
import java.util.Locale

object DistanceFormatter {

    fun format(meters: Int, context: Context, isForChip: Boolean): String {
        return if (meters >= KILOMETERS_THRESHOLD) {
            val km = meters / 1000.0
            val formatted = String.format(
                locale = Locale.getDefault(),
                format = "%.1f",
                km,
            )

            context.getString(
                R.string.distance_description_kilometers,
                formatted,
            )
        } else {
            if (isForChip) {
                context.getString(R.string.distance_meters, meters)
            } else {
                context.getString(R.string.distance_description_meters, meters)
            }
        }
    }

    private const val KILOMETERS_THRESHOLD = 1000
}
