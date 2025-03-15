package ru.khinkal.locationNotifier.feature.settings.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SwitchItem(
    modifier: Modifier = Modifier,
    title: String,
    checked: Boolean,
    onValueChange: (Boolean) -> Unit,
    description: String? = null,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column (
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
            )

            if (description != null) {
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }

        Spacer(modifier = Modifier.width(15.dp))

        Switch(
            modifier = Modifier
                .align(Alignment.Top),
            checked = checked,
            onCheckedChange = onValueChange,
        )
    }
}
