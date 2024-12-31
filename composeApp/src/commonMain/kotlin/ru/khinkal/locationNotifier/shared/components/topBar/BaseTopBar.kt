package ru.khinkal.locationNotifier.shared.components.topBar

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
