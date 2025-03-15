package ru.khinkal.locationNotifier.feature.settings.content

import androidx.annotation.IntRange
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Label
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kmm_location_notifier.composeapp.generated.resources.Res
import kmm_location_notifier.composeapp.generated.resources.settings_location_update_seconds_interval_title
import kmm_location_notifier.composeapp.generated.resources.settings_vibration_title
import org.jetbrains.compose.resources.stringResource
import ru.khinkal.locationNotifier.feature.settings.components.SwitchItem
import ru.khinkal.locationNotifier.feature.settings.vm.model.SettingsAction
import ru.khinkal.locationNotifier.feature.settings.vm.model.SettingsUiState

@Composable
fun SettingsMainContent(
    state: SettingsUiState,
    sendAction: (SettingsAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(
                horizontal = 16.dp,
                vertical = 20.dp,
            ),
    ) {
        Vibration(
            vibrationIsEnabled = state.vibrationEnabled,
            onValueChange = { vibrationIsEnabled ->
                val action = SettingsAction.SetVibrationEnabled(vibrationIsEnabled)
                sendAction(action)
            },
        )

        HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))

        SetLocationUpdateSecondsInterval(
            locationUpdateSecondsInterval = state.locationUpdateSecondsInterval,
            onValueChange = { locationUpdateSecondsInterval ->
                val action = SettingsAction
                    .SetLocationUpdateSecondsInterval(locationUpdateSecondsInterval)
                sendAction(action)
            },
        )
    }
}

@Composable
private fun Vibration(
    vibrationIsEnabled: Boolean,
    onValueChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    SwitchItem(
        modifier = modifier,
        title = stringResource(Res.string.settings_vibration_title),
        checked = vibrationIsEnabled,
        onValueChange = onValueChange,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SetLocationUpdateSecondsInterval(
    @IntRange(from = 1, to = 60)
    locationUpdateSecondsInterval: Int,
    onValueChange: (Int) -> Unit,
) {
    val thumbInteractionSource = remember { MutableInteractionSource() }

    Text(
        text = stringResource(Res.string.settings_location_update_seconds_interval_title),
        style = MaterialTheme.typography.titleLarge,
    )

    Slider(
        value = locationUpdateSecondsInterval.toFloat(),
        onValueChange = { seconds ->
            onValueChange(seconds.toInt())
        },
        valueRange = 1f..60f,
        interactionSource = thumbInteractionSource,
        thumb = {
            val sliderText = locationUpdateSecondsInterval.toString()

            Label(
                label = {
                    PlainTooltip(
                        modifier = Modifier
                            .requiredSize(45.dp, 25.dp)
                            .wrapContentSize(),
                        containerColor = MaterialTheme.colorScheme.primary,
                    ) {
                        Text(
                            modifier = Modifier
                                .requiredSize(45.dp, 25.dp)
                                .wrapContentSize(),
                            text = sliderText,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                },
                interactionSource = thumbInteractionSource,
            ) {
                SliderDefaults.Thumb(
                    interactionSource = thumbInteractionSource,
                )
            }
        }
    )
}
