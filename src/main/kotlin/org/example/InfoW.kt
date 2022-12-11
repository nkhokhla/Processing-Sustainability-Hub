package org.example

import WebV
import com.jogamp.nativewindow.WindowClosingProtocol
import com.jogamp.newt.Window
import javafx.scene.Scene
import javafx.scene.layout.VBox
import javafx.scene.web.WebView
import javafx.stage.Stage
import processing.awt.PGraphicsJava2D
import processing.awt.PSurfaceAWT.SmoothCanvas
import processing.core.PApplet
import javax.swing.JFrame
import javax.swing.WindowConstants

class InfoW : PApplet() {
    init {
        runSketch(arrayOf(this.javaClass.simpleName), this)
    }

    override fun settings() {
        size(100, 100, RENDERER)

    }

    override fun setup() {
        setDefaultClosePolicy(this, false)
        surface.setTitle("Get Information");
    }

    override fun draw() {

    }

    override fun exit() {
        dispose()
        Processing.winI = null
    }

    fun setDefaultClosePolicy(pa: PApplet, keepOpen: Boolean) {
        val surf = pa.surface.native
        val canvas = pa.graphics
        if (canvas.isGL) {
            val w = surf as Window
            for (wl in w.windowListeners) if (wl.toString()
                    .startsWith("processing.opengl.PSurfaceJOGL")
            ) w.removeWindowListener(wl)
            w.defaultCloseOperation =
                if (keepOpen) WindowClosingProtocol.WindowClosingMode.DO_NOTHING_ON_CLOSE else WindowClosingProtocol.WindowClosingMode.DISPOSE_ON_CLOSE
        } else if (canvas is PGraphicsJava2D) {
            val f = (surf as SmoothCanvas).frame as JFrame
            for (wl in f.windowListeners) if (wl.toString()
                    .startsWith("processing.awt.PSurfaceAWT")
            ) f.removeWindowListener(wl)
            f.defaultCloseOperation =
                if (keepOpen) WindowConstants.DO_NOTHING_ON_CLOSE else WindowConstants.DISPOSE_ON_CLOSE
        }
    }

    fun makeVisible() {
        surface.setVisible(true)
    }

    companion object {
        const val RENDERER = P3D
    }
}