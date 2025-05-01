package ru.khinkal.locationNotifier.feature.main.presentation.content.locations

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint

@Composable
fun LocationItem(
    goalGeoPoint: GoalGeoPoint,
    shape: Shape,
    onItemClick: (GoalGeoPoint) -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier
            .height(56.dp)
            .fillMaxWidth()
            .clip(shape)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable {
                onItemClick(goalGeoPoint)
            }
            .padding(horizontal = 16.dp, vertical = 5.dp)
            .wrapContentSize(Alignment.CenterStart),
        text = goalGeoPoint.name,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.primary,
    )
}

fun LazyListScope.locations(
    goalGeoPoints: List<GoalGeoPoint>,
    onItemClick: (GoalGeoPoint) -> Unit,
    modifier: Modifier = Modifier,
) {
    items(
        count = goalGeoPoints.size,
        key = { index ->
            val geoPoint = goalGeoPoints[index]
            geoPoint.id
        },
    ) { index ->
        val geoPoint = goalGeoPoints[index]
        val shape = getLocationItemShape(
            lastIndex = goalGeoPoints.lastIndex,
            index = index,
        )

        LocationItem(
            modifier = modifier,
            goalGeoPoint = geoPoint,
            shape = shape,
            onItemClick = onItemClick,
        )
    }
}

@Composable
private fun getLocationItemShape(
    lastIndex: Int,
    index: Int,
): Shape {
    if (lastIndex == 0) return MaterialTheme.shapes.medium

    return when (index) {
        0 -> MaterialTheme.shapes.medium.copy(
            bottomStart = CornerSize(0.dp), bottomEnd = CornerSize(0.dp),
        )

        lastIndex -> MaterialTheme.shapes.medium.copy(
            topStart = CornerSize(0.dp), topEnd = CornerSize(0.dp),
        )

        else -> RectangleShape
    }
}
