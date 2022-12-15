package org.example

import controlP5.ControlFont
import controlP5.ControlP5
import controlP5.Toggle
import processing.core.PApplet
import processing.core.PFont
import processing.data.Table


//changed code from https://codesloth.home.blog/2019/01/27/arduino-processing-gui-input/

class Processing : PApplet() {
    //variables
    var cp5: ControlP5? = null
    var csvFile = dataPath("questions.csv")//questions for quiz
    //made to show hoover effect
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
        surface.setTitle("Sustainable Environment Hub")
        //button size
        val button_width = 200
        val button_height = 70

 //creating Font
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
            ?.captionLabel//gets label of the button to change font and position of text
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
        questions = loadTable(csvFile, "header")//loading data from csv file
    }
    fun button_1() {
        print(1)
        b1c++
          if (b1c >1 && b1c % 2 == 0){//made to get hoover effect
            winI = InfoW()
        }
    }

    fun button_2() {
        b2c++
        if (b2c >1 && b2c % 2 == 0){//made to get hoover effect
            winCo = CoW()
        }
    }

    fun button_3() {
        b3c++
        if (b3c >1 && b3c % 2 == 0){//made to get hoover effect
            winQ = QuizW()
        }
    }

    fun button_4() {
        b4c++
        if (b4c >1 && b4c % 2 == 0){//made to get hoover effect
            winCa = CardW()
        }
    }


    companion object {
        var questions: Table? =null//made to transfer questions to next window
        //other windows
        var winI: InfoW? = null
        var winCo: CoW? = null
        var winQ: QuizW? = null
        var winCa: CardW? = null
    }


}