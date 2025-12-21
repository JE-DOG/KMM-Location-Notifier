package ru.khinkal.locationNotifier.feature.main.presentation.content.locations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import kmm_location_notifier.composeapp.generated.resources.Res
import kmm_location_notifier.composeapp.generated.resources.ic_more_vertical
import kmm_location_notifier.composeapp.generated.resources.main_screen_location_delete
import kmm_location_notifier.composeapp.generated.resources.main_screen_location_edit
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ru.khinkal.locationNotifier.feature.main.domain.model.GoalGeoPoint

@Composable
fun LocationItem(
    goalGeoPoint: GoalGeoPoint,
    shape: Shape,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isMoreMenuExpanded by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = modifier
            .height(56.dp)
            .fillMaxWidth(),
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = goalGeoPoint.name,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
            )

            IconButton(
                onClick = {
                    isMoreMenuExpanded = true
                }
            ) {
                Icon(
                    imageVector = vectorResource(Res.drawable.ic_more_vertical),
                    contentDescription = null,
                )

                DropdownMenu(
                    expanded = isMoreMenuExpanded,
                    onDismissRequest = { isMoreMenuExpanded = false },
                ) {
                    DropdownMenuItem(
                        onClick = {
                            isMoreMenuExpanded = false
                            onEditClick()
                        },
                        text = {
                            Text(stringResource(Res.string.main_screen_location_edit))
                        },
                    )

                    DropdownMenuItem(
                        onClick = {
                            isMoreMenuExpanded = false
                            onDeleteClick()
                        },
                        text = {
                            Text(stringResource(Res.string.main_screen_location_delete))
                        },
                    )
                }
            }
        }

    }
}

fun LazyListScope.locations(
    goalGeoPoints: List<GoalGeoPoint>,
    onItemClick: (GoalGeoPoint) -> Unit,
    onEditClick: (GoalGeoPoint) -> Unit,
    onDeleteClick: (GoalGeoPoint) -> Unit,
    modifier: Modifier = Modifier,
) {
    items(
        count = goalGeoPoints.size,
        key = { goalGeoPoints[it].id },
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
            onClick = { onItemClick(geoPoint) },
            onDeleteClick = { onDeleteClick(geoPoint) },
            onEditClick = { onEditClick(geoPoint) },
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
