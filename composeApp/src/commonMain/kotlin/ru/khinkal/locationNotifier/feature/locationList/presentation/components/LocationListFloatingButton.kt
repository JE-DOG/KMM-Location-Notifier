package ru.khinkal.locationNotifier.feature.locationList.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import kmm_location_notifier.composeapp.generated.resources.Res
import kmm_location_notifier.composeapp.generated.resources.ic_add_location
import org.jetbrains.compose.resources.painterResource

@Composable
fun LocationListFloatingButton(
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Icon(
        modifier = modifier
            .size(70.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable(
                role = Role.Button,
                onClick = onAddClick,
            )
            .wrapContentSize(Alignment.Center)
            .size(30.dp),
        painter = painterResource(Res.drawable.ic_add_location),
        tint = MaterialTheme.colorScheme.primary,
        contentDescription = null,
    )
}
