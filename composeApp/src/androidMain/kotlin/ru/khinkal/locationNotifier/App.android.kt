package ru.khinkal.locationNotifier

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestNeededPermissions()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { App() }
    }

    private fun requestNeededPermissions() {
        val permissions = buildList {
            add(android.Manifest.permission.ACCESS_FINE_LOCATION)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                add(android.Manifest.permission.POST_NOTIFICATIONS)
            }
            add(android.Manifest.permission.USE_FULL_SCREEN_INTENT)
        }
        requestPermissions(
            permissions.toTypedArray(),
            1,
        )
    }

    companion object {

        fun createIntent(context: Context): Intent =
            Intent(
                context,
                AppActivity::class.java,
            )
    }
}

@Preview
@Composable
fun AppPreview() {
    Text("Hello there")
}
