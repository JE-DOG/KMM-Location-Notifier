package ru.khinkal.locationNotifier.feature.createGoal.presentation.content.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kmm_location_notifier.composeapp.generated.resources.Res
import kmm_location_notifier.composeapp.generated.resources.create_goal_distance_input_label
import kmm_location_notifier.composeapp.generated.resources.create_goal_location_latitude_input_label
import kmm_location_notifier.composeapp.generated.resources.create_goal_location_longitude_input_label
import kmm_location_notifier.composeapp.generated.resources.create_goal_name_input_label
import kmm_location_notifier.composeapp.generated.resources.create_goal_start_broadcast
import org.jetbrains.compose.resources.stringResource
import ru.khinkal.locationNotifier.feature.createGoal.presentation.vm.model.CreateGoalAction
import ru.khinkal.locationNotifier.feature.createGoal.presentation.vm.model.CreateGoalState
import ru.khinkal.locationNotifier.shared.theme.BaseTypography

@Composable
fun CreateGoalMainContent(
    state: CreateGoalState,
    sendAction: (CreateGoalAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(
                horizontal = 16.dp,
                vertical = 10.dp,
            )
            .imePadding(),
    ) {
        InputBlock(
            modifier = Modifier
                .verticalScroll(rememberScrollState()),
            state = state,
            sendAction = sendAction,
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            onClick = {
                val action = CreateGoalAction.StartBroadcast
                sendAction(action)
            }
        ) {
            Text(
                text = stringResource(Res.string.create_goal_start_broadcast),
                style = BaseTypography.Medium_14_500,
            )
        }
    }
}

@Composable
private fun InputBlock(
    state: CreateGoalState,
    sendAction: (CreateGoalAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        InputField(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.name,
            label = stringResource(Res.string.create_goal_name_input_label),
            onValueChange = { value ->
                val action = CreateGoalAction.SetProperty.SetName(value)
                sendAction(action)
            }
        )

        InputField(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.meters?.toString().orEmpty(),
            label = stringResource(Res.string.create_goal_distance_input_label),
            onValueChange = { value ->
                val meters = value.toIntOrNull()
                val action = CreateGoalAction.SetProperty.SetMeters(meters)
                sendAction(action)
            }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            InputField(
                modifier = Modifier
                    .weight(1f),
                value = state.longitude?.toString().orEmpty(),
                label = stringResource(Res.string.create_goal_location_longitude_input_label),
                onValueChange = { value ->
                    val longitude = value.toDoubleOrNull()
                    val action = CreateGoalAction.SetProperty.SetLongitude(longitude)
                    sendAction(action)
                }
            )

            Spacer(Modifier.width(5.dp))

            InputField(
                modifier = Modifier
                    .weight(1f),
                value = state.latitude?.toString().orEmpty(),
                label = stringResource(Res.string.create_goal_location_latitude_input_label),
                onValueChange = { value ->
                    val latitude = value.toDoubleOrNull()
                    val action = CreateGoalAction.SetProperty.SetLatitude(latitude)
                    sendAction(action)
                }
            )
        }
    }
}

@Composable
private fun InputField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
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
