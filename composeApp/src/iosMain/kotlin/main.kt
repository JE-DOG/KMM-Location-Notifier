import androidx.compose.ui.window.ComposeUIViewController
import ru.khinkal.locationNotifier.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
