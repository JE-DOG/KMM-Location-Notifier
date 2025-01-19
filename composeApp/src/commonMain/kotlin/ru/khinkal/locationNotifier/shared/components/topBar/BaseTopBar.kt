package ru.khinkal.locationNotifier.shared.components.topBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kmm_location_notifier.composeapp.generated.resources.Res
import kmm_location_notifier.composeapp.generated.resources.ic_back_arrow
import org.jetbrains.compose.resources.vectorResource
import ru.khinkal.locationNotifier.shared.theme.BaseTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onBackClicked: (() -> Unit)? = null,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                style = BaseTypography.Medium_18_500,
            )
        },
        navigationIcon = {
            if (onBackClicked != null) {
                IconButton(
                    onClick = onBackClicked,
                ) {
                    Icon(
                        imageVector = vectorResource(Res.drawable.ic_back_arrow),
                        contentDescription = null,
                    )
                }
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransparentBaseTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onBackClicked: (() -> Unit)? = null,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent,
        ),
        title = {
            Text(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = CircleShape,
                    )
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                text = title,
                style = BaseTypography.Medium_18_500,
            )
        },
        navigationIcon = {
            if (onBackClicked != null) {
                IconButton(
                    modifier = Modifier
                        .clip(CircleShape),
                    onClick = onBackClicked,
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.background,
                    ),
                ) {
                    Icon(
                        imageVector = vectorResource(Res.drawable.ic_back_arrow),
                        contentDescription = null,
                    )
                }
            }
        },
    )
}
