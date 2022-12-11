import javafx.application.Application
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.concurrent.Worker
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.control.ScrollPane
import javafx.scene.layout.VBox
import javafx.scene.web.WebView
import javafx.stage.Stage

//  w  w  w.j av a2s  . c  o m
class WebV : Application() {
    override fun start(stage: Stage) {
        stage.title = "JavaFX WebView Example"
        val webView = WebView()
        webView.engine.load("http://google.com")
        val vBox = VBox(webView)
        val scene = Scene(vBox, 960.0, 600.0)
        stage.scene = scene
        stage.show()

    }

}