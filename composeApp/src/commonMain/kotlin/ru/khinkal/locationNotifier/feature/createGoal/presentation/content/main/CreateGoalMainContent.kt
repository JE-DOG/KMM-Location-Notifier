package ru.khinkal.locationNotifier.feature.createGoal.presentation.content.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kmm_location_notifier.composeapp.generated.resources.Res
import kmm_location_notifier.composeapp.generated.resources.create_goal_location_input_label
import kmm_location_notifier.composeapp.generated.resources.create_goal_meters_distance_input_label
import kmm_location_notifier.composeapp.generated.resources.create_goal_name_input_label
import org.jetbrains.compose.resources.stringResource
import ru.khinkal.locationNotifier.core.ext.compose.clickableWithoutRipple
import ru.khinkal.locationNotifier.feature.createGoal.presentation.vm.model.CreateGoalAction
import ru.khinkal.locationNotifier.feature.createGoal.presentation.vm.model.CreateGoalState
import ru.khinkal.locationNotifier.shared.theme.BaseTypography

@Composable
fun CreateGoalMainContent(
    state: CreateGoalState,
    sendAction: (CreateGoalAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    InputBlock(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(
                horizontal = 16.dp,
                vertical = 10.dp,
            ),
        state = state,
        sendAction = sendAction,
    )
}

@Composable
private fun InputBlock(
    state: CreateGoalState,
    sendAction: (CreateGoalAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val baseGeoPoint = state.baseGeoPoint

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        InputField(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.name,
            onValueChange = { value ->
                val action = CreateGoalAction.SetProperty.SetName(value)
                sendAction(action)
            },
            label = stringResource(Res.string.create_goal_name_input_label),
        )

        InputField(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.meters?.toString().orEmpty(),
            onValueChange = { value ->
                val meters = value.toIntOrNull()
                val action = CreateGoalAction.SetProperty.SetMeters(meters)
                sendAction(action)
            },
            label = stringResource(Res.string.create_goal_meters_distance_input_label),
        )

        val locationText = if (baseGeoPoint != null) {
            "${baseGeoPoint.latitude}, ${baseGeoPoint.longitude}"
        } else {
            ""
        }
        InputField(
            modifier = Modifier
                .fillMaxWidth()
                .clickableWithoutRipple {
                    val action = CreateGoalAction.OnSetBaseGeoPointClicked
                    sendAction(action)
                },
            enabled = false,
            value = locationText,
            onValueChange = {},
            label = stringResource(Res.string.create_goal_location_input_label),
        )
    }
}

@Composable
private fun InputField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
) {
    val textFieldDefaultColors = TextFieldDefaults.colors()

    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        colors = TextFieldDefaults.colors(
            disabledLabelColor = textFieldDefaultColors.unfocusedLabelColor,
            disabledTextColor = textFieldDefaultColors.unfocusedTextColor,
            disabledContainerColor = textFieldDefaultColors.unfocusedContainerColor,
            disabledIndicatorColor = textFieldDefaultColors.unfocusedIndicatorColor,
        ),
        label = {
            if (label.isNotEmpty()) {
                Text(
                    text = label,
                    style = BaseTypography.Normal_14_400,
                )
            }
        }
    )
}
