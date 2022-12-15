package org.example

import com.jogamp.nativewindow.WindowClosingProtocol
import com.jogamp.newt.Window
import org.example.Processing.Companion.questions
import processing.awt.PGraphicsJava2D
import processing.awt.PSurfaceAWT.SmoothCanvas
import processing.core.PApplet
import processing.core.PFont
import java.util.*
import javax.swing.JFrame
import javax.swing.WindowConstants


class QuizW : PApplet() {
    //changed code from https://www.chegg.com/homework-help/questions-and-answers/hi-create-trivia-game-processing-https-processingorg-java-reads-questions-csv-accepts-answ-q32577212
    //questions from https://cbseacademic.nic.in/web_material/Manuals/Ecoclub.pdf
    //variables
    var f: PFont? = null//font
    var rowCount = questions!!.rowCount ///How many questions there are in the import
    var id = 0 //Individual question IDs
    var q = 1//other id

    var question = Array(rowCount + 1) {
        arrayOfNulls<String>(
            9
        )
    } //two dimensional array of questions, their answers, and correct/wrong responses.
    var times = Array(rowCount+1) { 0 }//array for storing time to display 3 secs after press
    var prId = 0//previous id for looping
    init {
        runSketch(arrayOf(this.javaClass.simpleName), this)
    }//opens this window

    override fun settings() {
        size(900, 600, RENDERER)//window size
    }

    override fun setup() {
        surface.setTitle("Quiz")//title
        f = createFont("Arial", 20f, false) // font details
        setDefaultClosePolicy(this, false)//made to close only current window
    }

    fun convertTable(n: Int) {//gets data to question array
        if (questions != null) {
            var row = questions!!.getRow(n)
            id = row.getInt("id")
            question[id][0] = row.getString("id")
            question[id][1] = row.getString("Question")
            question[id][2] = row.getString("A")
            question[id][3] = row.getString("B")
            question[id][4] = row.getString("C")
            question[id][5] = row.getString("D")
            question[id][6] = row.getString("Correct").toLowerCase()
            question[id][7] = row.getString("Correct Response")
            question[id][8] = row.getString("Wrong Response")
        }
    }

    fun displayNewQuestion(que: Array<Array<String?>>, questionID: Int) {
        textFont(f)// sets font
        fill(255)// colour
        clear()
        background(204f, 204f, 204f)
        //displaying texts and ellipses
        text(que[questionID][1], 20f, 50f)
        fill(220f, 20f, 60f)
        ellipse((width / 16 + 65).toFloat(), (height / 3).toFloat(), 95f, 95f)
        fill(34f, 139f, 34f)
        ellipse((width / 16 + 65).toFloat(), (height / 3 + 100).toFloat(), 95f, 95f)
        fill(70f, 130f, 180f)
        ellipse((width / 16 + 65).toFloat(), (height / 3 + 200).toFloat(), 95f, 95f)
        fill(255f, 215f, 0f)
        ellipse((width / 16 + 65).toFloat(), (height / 3 + 300).toFloat(), 95f, 95f)
        fill(255)
        textFont(f)
        text("A: ", width / 16f + 50, height / 3f + 10)
        text("B: ", width / 16f + 50, height / 3 + 110f)
        text("C: ", width / 16 + 50f, height / 3 + 210f)
        text("D: ", width / 16 + 50f, height / 3 + 310f)
        text(que[questionID][2], width / 16f + 150, height / 3f + 10)
        text(que[questionID][3], width / 16 + 150f, height / 3 + 110f)
        text(que[questionID][4], width / 16f + 150, height / 3 + 210f)
        text(que[questionID][5], width / 16 + 150f, height / 3 + 310f)
        //getting correctAnswer
        val correctAnswer = question[questionID][6]?.lowercase(Locale.getDefault())?.get(0)
        if (key == correctAnswer) {//checking for correctAnswer
            if (prId!=questionID) {//getting time
                noLoop()
                times[questionID] = millis()
                prId =questionID
                loop()
            }
            answerSubmitted(question[questionID][7],times[questionID])//displaying respone
        } else if (key == 'a' || key == 'b' || key == 'c' || key == 'd') {//same with wrong answer
            if (prId!=questionID) {
                noLoop()
                times[questionID] = millis()
                prId =questionID
                loop()
            }
            answerSubmitted(question[questionID][8],times[questionID])
            if (questionID == 2) {
                println(questionID)
            }
        }
    }

    override fun draw() {
        if (q <= rowCount) {//while there are more questions
            convertTable(q - 1)//get data
            displayNewQuestion(question, q)//display

        } else {
            clear()
            background(204f, 204f, 204f)
            text("Quiz finished", 20f, 50f)
        }

    }

    fun answerSubmitted(response: String?, pressTime:Int) {
        if (millis()< pressTime+3000) {//displays response for 3 secs
            clear()
            background(204f, 204f, 204f)
            text(response, 20f, 50f)
        } else {//then activates next question
            clear()
            background(204f, 204f, 204f)
            q++
            key = ' '//empties key variable
        }
    }


    override fun exit() {
        //made to close only current window
        dispose()
        Processing.winQ = null
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