import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    
    init(){
        DIKt.doInitKoin()
    }
    
    
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
