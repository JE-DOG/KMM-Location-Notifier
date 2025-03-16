package ru.khinkal.locationNotifier.shared.components.topBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import kmm_location_notifier.composeapp.generated.resources.Res
import kmm_location_notifier.composeapp.generated.resources.ic_back_arrow
import org.jetbrains.compose.resources.vectorResource
import ru.khinkal.locationNotifier.shared.theme.AppTypography

private val TITLE_STYLE: TextStyle
    get() = AppTypography.Medium_20_500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
    actions: @Composable () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically),
                text = title,
                style = TITLE_STYLE,
                color = MaterialTheme.colorScheme.primary,
            )
        },
        navigationIcon = {
            if (onBackClick != null) {
                IconButton(
                    onClick = onBackClick,
                ) {
                    Icon(
                        imageVector = vectorResource(Res.drawable.ic_back_arrow),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        },
        actions = { actions() },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransparentBaseTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onBackClicked: (() -> Unit)? = null,
) {
    Box(modifier) {
        CenterAlignedTopAppBar(
            modifier = Modifier,
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Transparent,
                scrolledContainerColor = Color.Transparent,
            ),
            title = {
                Text(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = CircleShape,
                        )
                        .padding(horizontal = 10.dp, vertical = 6.dp),
                    text = title,
                    style = TITLE_STYLE,
                    color = MaterialTheme.colorScheme.primary,
                )
            },
            navigationIcon = {
                if (onBackClicked != null) {
                    IconButton(
                        modifier = Modifier
                            .clip(CircleShape),
                        onClick = onBackClicked,
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                        ),
                    ) {
                        Icon(
                            imageVector = vectorResource(Res.drawable.ic_back_arrow),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                        )
                    }
                }
            },
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.scrim)
                .statusBarsPadding(),
        )
    }
}
