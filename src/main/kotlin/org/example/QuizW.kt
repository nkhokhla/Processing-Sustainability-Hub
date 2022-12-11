package org.example

import com.jogamp.nativewindow.WindowClosingProtocol
import com.jogamp.newt.Window
import processing.awt.PGraphicsJava2D
import processing.awt.PSurfaceAWT.SmoothCanvas
import processing.core.PApplet
import javax.swing.JFrame
import javax.swing.WindowConstants


class QuizW : PApplet() {
    init {
        runSketch(arrayOf(this.javaClass.simpleName), this)
    }

    override fun settings() {
        size(500, 200, RENDERER)
    }

    override fun setup() {
        surface.setTitle("Quiz");
        setDefaultClosePolicy(this, false)
    }

    override fun draw() {

    }

    override fun exit() {
        dispose()
        Processing.winQ = null
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