package org.example

import com.jogamp.nativewindow.WindowClosingProtocol
import com.jogamp.newt.Window
import controlP5.ControlFont
import controlP5.ControlP5
import controlP5.Textfield
import processing.awt.PGraphicsJava2D
import processing.awt.PSurfaceAWT.SmoothCanvas
import processing.core.PApplet
import processing.core.PFont
import javax.swing.JFrame
import javax.swing.WindowConstants


class CoW : PApplet() {
    // variables
    var cp5: ControlP5? = null
    var url1: String? = null
    var url2: String? = null
    var url3: String? = null
    var cal=0
    var el=0.0
    var co2 = 0.0
    var caloriesPerKm =32
    var hasBeenPressed = false
    var pfont: PFont? =null
    var font: ControlFont? =null

    init {
        //made to close only current window
        runSketch(arrayOf(this.javaClass.simpleName), this)
    }

    override fun settings() {
        size(900, 400, RENDERER)
    }

    override fun setup() {
        //creating font
        pfont = createFont("Arial", 20f, false) // use true/false for smooth/no-smooth
        font = ControlFont(pfont)
        surface.setTitle("CO2 Calculator")
        //adding ui elements
        cp5 = ControlP5(this)
        cp5!!.addTextfield("0").setPosition(20f, 50f).setSize(200, 70).setAutoClear(false).getCaptionLabel().setFont(font).setText("Kilometers a month cycling").toUpperCase(false)
        cp5!!.addTextfield("1").setPosition(20f, 150f).setSize(200, 70).setAutoClear(false).getCaptionLabel().setFont(font).setText("Monthly electricity consumption").toUpperCase(false)
        cp5!!.addTextfield("2").setPosition(20f, 250f).setSize(200, 70).setAutoClear(false).getCaptionLabel().setFont(font).setText("Kilometers a month driving").toUpperCase(false)
        cp5!!.addBang("Submit").setPosition(260f, 150f).setSize(200, 70)?.setCaptionLabel("Submit")
            ?.captionLabel
            ?.setFont(font)
            ?.alignX(CENTER)
            ?.alignY(CENTER)
        //made to close only current window
        setDefaultClosePolicy(this, false)
    }

    override fun draw() {
        //button action
        if (mousePressed&&mouseX>260f && mouseY>150f && mouseX<460f && mouseY<240f){
            clear()
            background(204f,204f,204f)
            textFont(pfont)
            fill(0f,0f,0f)
            text("Calories burned a year:$cal\nCO2 from electricity:$el kg\nCO2 from driving:$co2 kg\n",480f,50f)
        }

    }

    override fun exit() {
        //made to close only current window
        dispose()
        Processing.winCo = null
    }
    fun Submit() {
        //calculations
        print("the following text was submitted :")
        url1 = cp5!!.get(Textfield::class.java, "0").text
        cal= parseInt(url1)*caloriesPerKm*12
        url2 = cp5!!.get(Textfield::class.java, "1").text
        el=parseInt(url2)*12*0.185
        url3 = cp5!!.get(Textfield::class.java, "2").text
        co2 = parseInt(url3)*12*0.133
        print(" textInput 1 = $url1")
        print(" textInput 2 = $url2")
        print(" textInput 3 = $url3")
        println()
    }

    fun setDefaultClosePolicy(pa: PApplet, keepOpen: Boolean) {
        //made to close only current window(somewhere from internet)
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