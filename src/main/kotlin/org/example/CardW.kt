package org.example

import com.jogamp.nativewindow.WindowClosingProtocol
import com.jogamp.newt.Window
import controlP5.ControlFont
import controlP5.ControlP5
import processing.awt.PGraphicsJava2D
import processing.awt.PSurfaceAWT.SmoothCanvas
import processing.core.PApplet
import processing.core.PFont
import processing.core.PImage
import java.io.File
import javax.swing.JFrame
import javax.swing.WindowConstants


class CardW : PApplet() {
    var cp5: ControlP5? = null
    var pfont: PFont? = null
    var font: ControlFont? = null
    var imgs1 = arrayOfNulls<PImage>(9)
    var selected1 = 0

    init {
        runSketch(arrayOf(this.javaClass.simpleName), this)
    }

    override fun settings() {
        size(900, 600, RENDERER)
    }

    override fun setup() {
        surface.setTitle("Create Postcard")
//        selectFolder("Select a folder to process:", "folderSelected");
        imgs1[0] = loadImage(dataPath("test1.jpg"))
        imgs1[1] = loadImage(dataPath("test2.jpg"))
        imgs1[2] = loadImage(dataPath("test3.jpg"))
        imgs1[3] = loadImage(dataPath("test4.jpg"))
        imgs1[4] = loadImage(dataPath("test5.jpg"))
        imgs1[5] = loadImage(dataPath("test6.jpg"))
        imgs1[6] = loadImage(dataPath("test7.jpg"))
        imgs1[7] = loadImage(dataPath("test8.jpg"))
        imgs1[8] = loadImage(dataPath("test9.jpg"))
        setDefaultClosePolicy(this, false)
        pfont = createFont("Arial", 20f, false) // use true/false for smooth/no-smooth
        font = ControlFont(pfont)
        cp5 = ControlP5(this)
        cp5!!.addBang("g").setPosition(350f, 20f).setSize(200, 70)?.setCaptionLabel("Generate image")?.captionLabel
            ?.setFont(font)
            ?.alignX(CENTER)
            ?.alignY(CENTER)
        cp5!!.addBang("s").setPosition(350f, height - 90f).setSize(200, 70)?.setCaptionLabel("Save image")?.captionLabel
            ?.setFont(font)
            ?.alignX(CENTER)
            ?.alignY(CENTER)
    }

    override fun draw() {
        if (mousePressed&&mouseX>350f && mouseY>20f && mouseX<550f && mouseY<90f) {
            clear()
            background(204f, 204f, 204f)
            selected1 = random(imgs1.size.toFloat()).toInt()
            image(imgs1[selected1], 220f, 100f)
        }
        if (mousePressed&&mouseX>350f && mouseY>20f && mouseX<550f && mouseY<90f){
            imgs1[selected1]?.save("C:\\Users\\nazar\\OneDrive\\Desktop\\img.jpg")
        }

    }
    fun folderSelected(selection: File?) {
        if (selection == null) {
            println("Window was closed or the user hit cancel.")
        } else {
            println("User selected " + selection.getAbsolutePath())
        }
    }
    override fun exit() {
        dispose()
        Processing.winCa = null
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