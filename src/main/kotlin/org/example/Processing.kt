package org.example

import controlP5.Bang
import controlP5.ControlFont
import controlP5.ControlP5
import processing.core.PApplet
import processing.core.PFont
import processing.data.Table


//changed code from https://codesloth.home.blog/2019/01/27/arduino-processing-gui-input/

class Processing : PApplet() {
    var cp5: ControlP5? = null
    var csvFile = dataPath("questions.csv")
    var b1c = 0
    var b2c = 0
    var b3c = 0
    var b4c = 0
    // stores all the button handles in an array to iterate

    var buttonRef = arrayOfNulls<Bang>(5)


    override fun settings() {
        size(400, 550, P3D)
    }

    override fun setup() {
        surface.setTitle("Sustainable Environment Hub");
        val button_width = 200
        val button_height = 70

 //creating Font
        val pfont: PFont = createFont("Arial", 20f, true) // use true/false for smooth/no-smooth

        val font = ControlFont(pfont)

        cp5 = ControlP5(this) // creates an object

        buttonRef = arrayOfNulls<Bang>(5)
//buttons
        cp5!!.addBang("button_1").setPosition(100f, 50f)
            .setSize(button_width, button_height)
            ?.setCaptionLabel("Information")
            ?.captionLabel//gets text to change its position and font
            ?.setFont(font)
            ?.alignX(CENTER)
            ?.alignY(CENTER)


        var b2= cp5!!.addBang("button_2")
        b2?.setPosition(100f, 150f) //x and y coordinates of upper left corner of button
            ?.setSize(button_width, button_height) //(width, height)
            ?.setCaptionLabel("CO2 Calculator")
            ?.captionLabel
            ?.setFont(font)
            ?.alignX(CENTER)
            ?.alignY(CENTER)

        buttonRef[2] = cp5!!.addBang("button_3")
        buttonRef[2]?.setPosition(100f, 250f) //x and y coordinates of upper left corner of button
            ?.setSize(button_width, button_height) //(width, height)
            ?.setCaptionLabel("Quiz")
            ?.captionLabel
            ?.setFont(font)
            ?.alignX(CENTER)
            ?.alignY(CENTER)

        buttonRef[3] = cp5!!.addBang("button_4")
        buttonRef[3]?.setPosition(100f, 350f) //x and y coordinates of upper left corner of button
            ?.setSize(button_width, button_height) //(width, height)
            ?.setCaptionLabel("E-postcard")
            ?.captionLabel
            ?.setFont(font)
            ?.alignX(CENTER)
            ?.alignY(CENTER)


    }

    override fun draw() {
        //getting data for quiz
        questions = loadTable(csvFile, "header")
        //made for hoover effect
        //checking if clicked on buttons
        if (mousePressed&&mouseX>100f && mouseY>50f && mouseX<300f && mouseY<120f&&b1c==0) {
            noLoop()//used to open only one window
            winI = InfoW()
            b1c=1//used to open only one window
            mousePressed=false//used to wait for next click
            loop()//used to open only one window
        }
        if (mousePressed&&mouseX>100f && mouseY>150f && mouseX<300f && mouseY<220f&&b2c==0) {
            noLoop()
            winCo = CoW()
            b2c=1
            mousePressed=false
            loop()
        }
        if (mousePressed&&mouseX>100f && mouseY>250f && mouseX<300f && mouseY<320f&&b3c==0) {
            noLoop()
            winQ = QuizW()
            b3c=1
            mousePressed=false
            loop()
        }
        if (mousePressed&&mouseX>100f && mouseY>350f && mouseX<300f && mouseY<420f&&b4c==0) {
            noLoop()
            winCa = CardW()
            b4c=1
            mousePressed=false
            loop()
        }

    }

    companion object {
        var questions: Table? =null
        var winI: InfoW? = null
        var winCo: CoW? = null
        var winQ: QuizW? = null
        var winCa: CardW? = null
    }


}