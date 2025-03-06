import UIKit
import composeApp
import CoreLocation

@main
class AppDelegate: UIResponder, UIApplicationDelegate {
     var window: UIWindow?
    
    let dependencyInitializaer = DependencyInitializer()

     func application(
         _ application: UIApplication,
         didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
     ) -> Bool {
         dependencyInitializaer.initDeps()
         window = UIWindow(frame: UIScreen.main.bounds)
         if let window = window {
             let mainViewController = MainKt.MainViewController()
             window.rootViewController = mainViewController
             window.makeKeyAndVisible()
         }
         return true
     }
}
