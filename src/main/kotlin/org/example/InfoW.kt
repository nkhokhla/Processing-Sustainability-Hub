package org.example

import com.jogamp.nativewindow.WindowClosingProtocol
import com.jogamp.newt.Window
import controlP5.ControlFont
import controlP5.ControlP5
import processing.awt.PGraphicsJava2D
import processing.awt.PSurfaceAWT.SmoothCanvas
import processing.core.PApplet
import processing.core.PFont
import javax.swing.JFrame
import javax.swing.WindowConstants

class InfoW : PApplet() {
    //variables
    var cp5: ControlP5? = null
    var pfont: PFont? =null
    var font: ControlFont? =null
    init {
        //made to close only current window
        runSketch(arrayOf(this.javaClass.simpleName), this)
    }

    override fun settings() {
        size(500, 400, RENDERER)

    }

    override fun setup() {
        //creating font
        pfont = createFont("Arial", 20f, false) // use true/false for smooth/no-smooth
        font = ControlFont(pfont)
        setDefaultClosePolicy(this, false)
        surface.setTitle("Get Information")
        background(204f,204f,204f)
        //adding ui elements
        cp5 = ControlP5(this)
        cp5!!.addBang("Submit").setPosition(150f, height - 90f).setSize(200, 70)?.setCaptionLabel("Website")
            ?.captionLabel
            ?.setFont(font)
            ?.alignX(CENTER)
            ?.alignY(CENTER)
        textFont(pfont)

    }

    override fun draw() {
        fill(10f,10f,10f)
        text("This project was created by Nazar Khokhla \n It's a useful combination of tools\n designed to combine different ways of raising\n the interest in sustainable environment.\n For any questions feel free to contact me at\n nazar.khokhla@gmail.com\n\n\n Also check out this list of all things sustainable:", 20f, 50f)
    }
    fun Submit() {
    link("https://github.com/bizz84/Sustainable-Earth")
    }

    override fun exit() {
        //made to close only current window
        dispose()
        Processing.winI = null
    }

    fun setDefaultClosePolicy(pa: PApplet, keepOpen: Boolean) {
        //made to close only current window
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
        //made to close only current window
        surface.setVisible(true)
    }

    companion object {
        //made to close only current window
        const val RENDERER = P3D
    }
}