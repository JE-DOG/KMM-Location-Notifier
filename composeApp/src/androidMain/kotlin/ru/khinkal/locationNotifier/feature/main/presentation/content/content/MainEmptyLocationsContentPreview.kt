package ru.khinkal.locationNotifier.feature.main.presentation.content.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import ru.khinkal.locationNotifier.shared.theme.AppTheme

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun MainEmptyLocationsContentPreview() {
    AppTheme {
        MainEmptyLocationsContent()
    }
}
