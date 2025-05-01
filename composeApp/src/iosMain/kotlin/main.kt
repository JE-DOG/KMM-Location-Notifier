import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import ru.khinkal.locationNotifier.App

// NOT DELETE
// Using from iosApp
fun MainViewController(): UIViewController = ComposeUIViewController { App() }
