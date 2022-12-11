package org.example

import controlP5.ControlFont
import controlP5.ControlP5
import controlP5.Toggle
import processing.core.PApplet
import processing.core.PFont


//changed code from https://codesloth.home.blog/2019/01/27/arduino-processing-gui-input/

class Processing : PApplet() {
    var cp5: ControlP5? = null

    // sets the time duration the button appears to be ON
    var buttonDuration = intArrayOf(2000, 2000, 2000) //ms
    var buttonStartTime = intArrayOf(0, 0, 0, 0) //ms
    var b1c = 0
    var b2c = 0
    var b3c = 0
    var b4c = 0
    // stores all the button handles in an array to iterate

    var buttonRef = arrayOfNulls<Toggle>(5)


    override fun settings() {
        size(400, 550, P3D)
    }

    override fun setup() {
        surface.setTitle("Sustainable Environment Hub");
        val button_width = 200
        val button_height = 70


        val pfont: PFont = createFont("Arial", 20f, true) // use true/false for smooth/no-smooth

        val font = ControlFont(pfont)

        cp5 = ControlP5(this) // creates an object


        buttonRef = arrayOfNulls<Toggle>(5)

        buttonRef[0] = cp5!!.addToggle("button_1")
        buttonRef[0]?.setPosition(100f, 50f) //x and y coordinates of upper left corner of button
            ?.setSize(button_width, button_height) //(width, height)
            ?.setValue(false)
            ?.setCaptionLabel("Information")
            ?.captionLabel
            ?.setFont(font)
            ?.alignX(CENTER)
            ?.alignY(CENTER)


        buttonRef[1] = cp5!!.addToggle("button_2")
        buttonRef[1]?.setPosition(100f, 150f) //x and y coordinates of upper left corner of button
            ?.setSize(button_width, button_height) //(width, height)
            ?.setValue(false)
            ?.setCaptionLabel("CO2 Calculator")
            ?.captionLabel
            ?.setFont(font)
            ?.alignX(CENTER)
            ?.alignY(CENTER)

        buttonRef[2] = cp5!!.addToggle("button_3")
        buttonRef[2]?.setPosition(100f, 250f) //x and y coordinates of upper left corner of button
            ?.setSize(button_width, button_height) //(width, height)
            ?.setValue(false)
            ?.setCaptionLabel("Quiz")
            ?.captionLabel
            ?.setFont(font)
            ?.alignX(CENTER)
            ?.alignY(CENTER)

        buttonRef[3] = cp5!!.addToggle("button_4")
        buttonRef[3]?.setPosition(100f, 350f) //x and y coordinates of upper left corner of button
            ?.setSize(button_width, button_height) //(width, height)
            ?.setValue(false)
            ?.setCaptionLabel("E-postcard")
            ?.captionLabel
            ?.setFont(font)
            ?.alignX(CENTER)
            ?.alignY(CENTER)


    }

    override fun draw() {
        for (i in 0..2) {
            if (buttonRef[i]!!.state) {
                if (millis() - buttonStartTime[i] > buttonDuration[i]) {
                    buttonRef[i]!!.setValue(false)
                }
            }
        }
    }

    // the functions that follow must follow button names!
    fun button_1() {
        print(1)
        b1c++
          if (b1c >1 && b1c % 2 == 0){
            winI = InfoW()
        }
        buttonStartTime[0] = millis()
    }

    fun button_2() {
        b2c++
        if (b2c >1 && b2c % 2 == 0){
            winCo = CoW()
        }
        buttonStartTime[1] = millis()
    }

    fun button_3() {
        b3c++
        if (b3c >1 && b3c % 2 == 0){
            winQ = QuizW()
        }
        buttonStartTime[2] = millis()
    }

    fun button_4() {
        b4c++
        if (b4c >1 && b4c % 2 == 0){
            winCa = CardW()
        }
        buttonStartTime[2] = millis()
    }


    companion object {
        var winI: InfoW? = null
        var winCo: CoW? = null
        var winQ: QuizW? = null
        var winCa: CardW? = null
    }


}